public class DummyATMDemo
{
    public static void main(String[] args)
    {
        ATM machine1 = new ATM("OtterUnion");
        ATM machine2 = new ATM(200, "BOA", "Library");
        Customer alice;
        System.out.println("===== Welcome to Demo Program =====");
        System.out.println(machine1); // tostring
        System.out.println("");
        System.out.println(machine2);
        System.out.println("\n===== Equality Checking =====");
        System.out.println(machine1.equals(machine2));
        System.out.println("");
        machine1.setATM(100, "BIT");
        machine1.addFund(400);// In this method, we assume that an ATM machine
        // administrator adds $400 more cash to the machine.
        System.out.println(machine1);
        System.out.println("");
        machine1.displayMenu();

    }
}