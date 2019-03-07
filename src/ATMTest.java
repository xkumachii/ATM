import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {

    private ATM Machine1;
    private ATM Machine2;
    private ATM Machine3;
    private ATM Machine4;

    @BeforeEach
    void setUp() {
        Machine1 = new ATM("OtterUnion");
        Machine2 = new ATM(200, "BOA", "Library");
        Machine3 = new ATM (300, "BOA");
        Machine4 = new ATM();
    }

    @AfterEach
    void tearDown() {
        Machine1 = null;
        Machine2 = null;
        Machine3 = null;
        Machine4 = null;
    }

    //testing constructors

    @Test
    void ATM() {
        ATM temp1 = new ATM();

        assertEquals(0, temp1.getSerialNumber());
        assertEquals(100, temp1.getBalance());
        assertEquals("UNKNOWN", temp1.getLocation());
        assertEquals("UNKNOWN", temp1.getBankName());

        ATM temp2 = new ATM("OtterUnion");
        assertEquals(temp2.getSerialNumber(), 0);
        assertEquals(temp2.getBalance(), 100);
        assertEquals(temp2.getLocation(), "UNKNOWN");
        assertEquals(temp2.getBankName(), "OtterUnion");

        ATM temp3 = new ATM(300, "BOA");
        assertEquals(temp3.getSerialNumber(), 300);
        assertEquals(temp3.getBalance(), 100);
        assertEquals(temp3.getLocation(), "UNKNOWN");
        assertEquals(temp3.getBankName(), "BOA");

        ATM temp4 = new ATM(200, "BOA", "Library");
        assertEquals(temp4.getSerialNumber(), 200);
        assertEquals(temp4.getBalance(), 100);
        assertEquals(temp4.getLocation(), "Library");
        assertEquals(temp4.getBankName(), "BOA");

        temp1 = null;
        temp2 = null;
        temp3 = null;
        temp4 = null;
    }

    // setATM()

    @Test
    void setATM() {
        // testing default values
        assertEquals(0, Machine4.getSerialNumber());
        assertEquals(100, Machine4.getBalance());
        assertEquals("UNKNOWN", Machine4.getLocation());
        assertEquals("UNKNOWN", Machine4.getBankName());

        // testing single-argument
        Machine3.setATM("Student Center");
        assertEquals(300, Machine3.getSerialNumber());
        assertEquals(100, Machine3.getBalance());
        assertEquals("Student Center", Machine3.getLocation());
        assertEquals("BOA", Machine3.getBankName());

        // testing double-argument
        Machine1.setATM(100, "BIT");
        assertEquals(100, Machine1.getSerialNumber());
        assertEquals(100, Machine1.getBalance());
        assertEquals("BIT", Machine1.getLocation());
        assertEquals("OtterUnion", Machine1.getBankName());

        // testing triple-argument
        Machine4.setATM(400, "OtterUnion", "Promontory");
        assertEquals(400, Machine4.getSerialNumber());
        assertEquals(100, Machine4.getBalance());
        assertEquals("Promontory", Machine4.getLocation());
        assertEquals("OtterUnion", Machine4.getBankName());
    }

    // getCustomer()

    @Test
    void getCustomer() {
        // someone searches a nonexistent customer
        assertNull(Machine1.getCustomer("NULL"));

        // someone searches a valid customer
        assertNotEquals(null, Machine1.getCustomer("Alice"));
    }

    // isCustomer()

    @Test
    void isCustomer() {
        // nonexistent customer
        assertFalse(Machine1.isCustomer("Jar Jar"));

        // valid customer
        assertTrue(Machine1.isCustomer("Alice"));

    }

    // addFund()

    @Test
    void addFund() {
        // operator adds money
        Machine1.addFund(100);
        assertEquals(200, Machine1.getBalance());

        // operator does not feel like putting money (zero input)
        Machine2.addFund(0);
        assertEquals(100, Machine2.getBalance());

        // operator tries to steal money (negative input)
        Machine3.addFund(-10);
        // ensure nothing is stolen
        assertEquals(100, Machine3.getBalance());
    }

    // transfer()

    @Test
    void Transfer() {
        Customer Alice = Machine1.getCustomer("Alice");
        System.out.println(Alice);
        Customer Tom = Machine1.getCustomer("Tom");
        System.out.println(Tom);
        Customer Robert = Machine1.getCustomer("Robert");
        System.out.println(Robert);

        // alice gives money to Tom
        assertTrue(Machine1.transfer("Alice", 1234, 10, "Tom", 2000));
        assertEquals(4990, Alice.getBalance());
        assertEquals(210, Tom.getBalance());


        // alice gives negative money to Tom
        assertFalse(Machine1.transfer("Alice", 1234, -10, "Tom", 2000));
        assertEquals(4990, Alice.getBalance());
        assertEquals(210, Tom.getBalance());

        // alice gives more than what is able to be given
        assertFalse(Machine1.transfer("Alice", 1234, 4990.01, "Tom", 2000));
        assertEquals(4990, Alice.getBalance());
        assertEquals(210, Tom.getBalance());

        // alice gives to someone under boa -- Robert
        assertFalse(Machine1.transfer("Alice", 1234, 10, "Robert", 2323));
        assertEquals(4990, Alice.getBalance());
        assertEquals(200, Robert.getBalance());

        // alice gets her pin wrong
        assertFalse(Machine1.transfer("Alice", 1235, 10, "Tom", 2000));
        assertEquals(4990, Alice.getBalance());
        assertEquals(210, Tom.getBalance());

        // tom gets his pin wrong
        assertFalse(Machine1.transfer("Alice", 1234, 10, "Tom", 2001));
        assertEquals(4990, Alice.getBalance());
        assertEquals(210, Tom.getBalance());

        // alice and tom get their pins wrong
        assertFalse(Machine1.transfer("Alice", 1235, 4990.01, "Tom", 2001));
        assertEquals(4990, Alice.getBalance());
        assertEquals(210, Tom.getBalance());

        // boa customer gives to someone in a otterunion bank -- Robert -> Alice
        assertFalse(Machine2.transfer("Robert", 2323, 10, "Alice", 1234));
        assertEquals(4990, Alice.getBalance());
        assertEquals(200, Robert.getBalance());

        // nonexistent giver -- "Vader"
        assertFalse(Machine1.transfer("Vader", 666, 1000000, "Alice", 1234));
        assertEquals(4990, Alice.getBalance());

        // nonexistent receiver -- "Anakin"
        assertFalse(Machine1.transfer("Alice", 1234, 10, "Anakin", 1972));
        assertEquals(4990, Alice.getBalance());


        // nonexistent giver and receiver -- "Asuna" -> "Kirito"
        assertFalse(Machine1.transfer("Asuna", 420, 69, "Kirito", 6969));

        Alice = null;
        Tom = null;
        Robert = null;
    }

    // deposit()

    @Test
    void deposit() {
        Customer Alice = Machine1.getCustomer("Alice");
        System.out.println(Alice);
        Customer Robert = Machine1.getCustomer("Robert");
        System.out.println(Robert);
        // alice deposits money
        assertTrue(Machine1.deposit("Alice", 1234, 10));
        assertEquals(5000, Alice.getBalance());
        assertEquals(110, Machine1.getBalance());

        // alice deposits negative money
        assertFalse(Machine1.deposit("Alice", 1234, -10));
        assertEquals(5000, Alice.getBalance());
        assertEquals(110, Machine1.getBalance());

        // alice gets her pin wrong
        assertFalse(Machine1.deposit("Alice", 7777, 10));
        assertEquals(5000, Alice.getBalance());
        assertEquals(110, Machine1.getBalance());


        // robert deposits money
        assertTrue(Machine2.deposit("Robert", 2323, 10));
        assertEquals(210, Robert.getBalance());
        assertEquals(110, Machine2.getBalance());

        // nonexistent customer -- "Juan"
        assertFalse(Machine1.deposit("Juan", 9942, 10));

        Alice = null;
        Robert = null;
    }

    // withdrawal()

    void withdrawal() {
        Customer Alice = Machine1.getCustomer("Alice");
        System.out.println(Alice);

        // alice withdraws money
        assertTrue(Machine1.withdrawal("Alice", 1234, 10));
        assertEquals(4990, Alice.getBalance());
        assertEquals(100, Machine1.getBalance());

        // alice withdraws negative money
        assertFalse(Machine1.withdrawal("Alice", 1234, -10));
        assertEquals(4990, Alice.getBalance());
        assertEquals(100, Machine1.getBalance());

        // alice withdraws more than what the atm has
        assertTrue(Machine1.withdrawal("Alice", 1234, 100.01));
        assertEquals(4990, Alice.getBalance());
        assertEquals(100, Machine1.getBalance());

        // alice gets her pin wrong
        assertFalse(Machine1.withdrawal("Alice", 7777, 10));
        assertEquals(4990, Alice.getBalance());
        assertEquals(100, Machine1.getBalance());


        // nonexistent customer "Ken"

        assertFalse(Machine1.withdrawal("Ken", 4567, 10));
        assertEquals(100, Machine1.getBalance());

        Alice = null;
    }


}
