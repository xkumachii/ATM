import java.util.ArrayList;
import java.util.Objects;

public class ATM {

    // accessible fields
    private int serialNumber;
    private String bankName;
    private String location;
    private double balance = 100;

    Customer customer;

    // inaccessible fields

    int numWithdrawalSuccesses;
    int numWithdrawalFailures;
    int numDepositSuccesses;
    int numDepositFailures;
    int numTransferSuccesses;
    int numTransferFailures;

    // our list of customers!

    static ArrayList<Customer> customers = new ArrayList<>();



    // getters and setters

    public void setATM(int serialNumber, String location) {
        this.serialNumber = serialNumber;
        this.location = location;
    }









    // methods and shtuff reeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee

    // withdrawal() function stack

    public void withdrawal(String name, int ID, double money) {
        boolean valid = true; // assume valid, until proven false
    }

    // displayMenu()

    public void displayMenu() {
        System.out.println("===== ATM Transaction Menu =====");
        System.out.println("    1. Withdrawal");
        System.out.println("    2. Deposit");
        System.out.println("    3. Transfer");
    }

    // status() function stack

    private int addTransactions() {
        return  this.numWithdrawalSuccesses +
                this.numWithdrawalFailures +
                this.numDepositSuccesses +
                this.numDepositFailures +
                this.numTransferSuccesses +
                this.numTransferFailures;
    }

    public void status() {
        toString();

        int numTransactions = addTransactions();

        System.out.println("    " + numTransactions + " Transactions so far");
        System.out.println("        Withdrawal: ");
        System.out.println("        Deposit: ");
        System.out.println("        Transfer: ");
    }

    // addFund()

    public void addFund(int cash) {
        this.balance += cash;
    }


    @Override
    public String toString() {
        String formattedBalance = String.format("%.02f", this.balance);
        return "Serial Number: " + this.serialNumber +
                "\nBank Name: " + this.bankName +
                "\nLocation: " + this.location +
                "\nBalance: " + formattedBalance;
    }

    //constructor helper functions

    private void setStats() {
        this.numWithdrawalSuccesses = 0;
        this.numWithdrawalFailures = 0;
        this.numDepositSuccesses = 0;
        this.numDepositFailures = 0;
        this.numTransferSuccesses = 0;
        this.numTransferFailures = 0;
    }

    private void setCustomers() {
        customers.add(new Customer("Alice", 1234, 5000, "OtterUnion"));
        customers.add(new Customer("Tom", 2000, 200, "OtterUnion"));
        customers.add(new Customer("Monica", 3000, 50, "OtterUnion"));
        customers.add(new Customer("Michael", 7777, 0, "OtterUnion"));
        customers.add(new Customer("John", 8000, 500, "OtterUnion"));
        customers.add(new Customer("Jane", 2222, 500, "OtterUnion"));
        customers.add(new Customer("Robert", 2323, 200, "BOA"));
        customers.add(new Customer("Owen", 4455, 50, "BOA"));
        customers.add(new Customer("Chris", 8787, 10, "BOA"));
        customers.add(new Customer("Rebecca", 8080, 555.55, "BOA"));

    }

    // constructors

    public ATM(int serialNumber, String bankName, String location) {
        this.serialNumber = serialNumber;
        this.bankName = bankName;
        this.location = location;

        setStats();
        setCustomers();
    }

    public ATM(String bankName) {
        this(0, bankName, "UNKNOWN");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATM atm = (ATM) o;
        return serialNumber == atm.serialNumber &&
                Double.compare(atm.balance, balance) == 0 &&
                numWithdrawalSuccesses == atm.numWithdrawalSuccesses &&
                numWithdrawalFailures == atm.numWithdrawalFailures &&
                numDepositSuccesses == atm.numDepositSuccesses &&
                numDepositFailures == atm.numDepositFailures &&
                numTransferSuccesses == atm.numTransferSuccesses &&
                numTransferFailures == atm.numTransferFailures &&
                Objects.equals(bankName, atm.bankName) &&
                Objects.equals(location, atm.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, bankName, location, balance,
                numWithdrawalSuccesses, numWithdrawalFailures,
                numDepositSuccesses, numDepositFailures,
                numTransferSuccesses, numTransferFailures);
    }
}
