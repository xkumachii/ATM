import java.util.ArrayList;
import java.util.Objects;

public class ATM {

    // accessible fields
    private int serialNumber;
    private String bankName;
    private String location;
    private double balance = 100;

    // inaccessible fields

    int numWithdrawalSuccesses;
    int numWithdrawalFailures;
    int numDepositSuccesses;
    int numDepositFailures;
    int numTransferSuccesses;
    int numTransferFailures;




    // getters and setters

    public void setATM(int serialNumber, String location) {
        this.serialNumber = serialNumber;
        this.location = location;
    }









    // methods and shtuff reeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee

    public void displayMenu() {
        System.out.println("===== ATM Transaction Menu =====");
        System.out.println("    1. Withdrawal)");
        System.out.println("    2. Deposit)");
        System.out.println("    3. Transfer");

    }

    @Override
    public String toString() {
        return "Serial Number: " + this.serialNumber +
                "\nBank Name: " + this.bankName +
                "\nLocation: " + this.location +
                "\nBalance: $" + this.balance;
    }

    // constructors

    public ATM(int serialNumber, String bankName, String location) {
        this.serialNumber = serialNumber;
        this.bankName = bankName;
        this.location = location;

        int numWithdrawalSuccesses = 0;
        int numWithdrawalFailures = 0;
        int numDepositSuccesses = 0;
        int numDepositFails = 0;
        int numTransferSuccesses = 0;
        int numTransferFailures = 0;
    }

    public ATM(String bankName) {
        this(0, bankName, "UNKNOWN");

        int numWithdrawalSuccesses = 0;
        int numWithdrawalFailures = 0;
        int numDepositSuccesses = 0;
        int numDepositFails = 0;
        int numTransferSuccesses = 0;
        int numTransferFailures = 0;
    }



}
