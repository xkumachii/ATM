import java.util.ArrayList;
import java.util.Objects;

//TODO: MAKE JUNIT TEST CASES REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE

public class ATM {

    // accessible fields
    private int serialNumber;
    private String bankName;
    private String location;
    private double balance = 100;

    Customer customer;

    // inaccessible fields

    private int numWithdrawalSuccesses;
    private int numWithdrawalFailures;
    private int numDepositSuccesses;
    private int numDepositFailures;
    private int numTransferSuccesses;
    private int numTransferFailures;


    // our list of customers!
    static boolean customersLoaded = false;
    static ArrayList<Customer> customers = new ArrayList<>();



    // getters and setters

    public void setATM(int serialNumber, String location) {
        this.serialNumber = serialNumber;
        this.location = location;
    }

    //todo: IMPLEMENT

    public Customer getCustomer(String name) {
        for (Customer i : customers) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }







    // public methods with helper functions

    // withdrawal() function

    public boolean withdrawal(String name, int ID, double money) {
        if (money > this.balance) { // customer wants to take more money than the ATM possesses.
            System.out.println("Fail – withdrawal");
            this.numWithdrawalFailures++;
            return false;
        }

        for (Customer i : customers) {
            if (i.getName().equals(name) && i.getID() == ID & i.getBank().equals(this.bankName)) {
                System.out.println("Succeed – withdrawal");
                i.setBalance(i.getBalance() + money);
                this.balance -= money;
                this.numWithdrawalSuccesses++;
                return true;
            }
        }

        // loop fully runs, and no valid customers are found.

        System.out.println("Fail – withdrawal");

        this.numWithdrawalFailures++;
        return false;
    }

    // deposit() function TODO: implement.

    public boolean deposit(String name, int ID, double money) {
        if (money > getCustomer(name).getBalance()) { // customer wants to put more money than they possesses.
            System.out.println("Fail – deposit");
            this.numDepositFailures++;
            return false;
        }

        for (Customer i : customers) {
            if (i.getName().equals(name) && i.getID() == ID & i.getBank().equals(this.bankName)) {
                System.out.println("Succeed – deposit");
                i.setBalance(i.getBalance() - money);
                this.balance += money;
                this.numDepositSuccesses++;
                return true;
            }
        }

        // loop fully runs, and no valid customers are found.

        System.out.println("Fail – deposit");

        numDepositFailures++;
        return false;
    }


    // transfer() function TODO: IMPLEMENT

    public boolean transfer(String giverName, int giverID, double money, String receiverName, int receiverID) {

        return false;
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

    private int getTotal(int success, int failure) {
        return success + failure;
    }

    private void formatPrint(String type, int success, int failure) {
        System.out.println(type + ": " + getTotal(success, failure)
                                + " (" + success + " success, " + failure + " fail)");
    }

    public void status() {
        int numTransactions = addTransactions();
        System.out.println(this.toString());
        System.out.println("    " + numTransactions + " Transactions so far");
        formatPrint("Withdrawal", this.numWithdrawalSuccesses, this.numWithdrawalFailures);
        formatPrint("Deposit", this.numDepositSuccesses, this.numDepositFailures);
        formatPrint("Transfer", this.numTransferSuccesses, this.numTransferFailures);
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
        if (!this.customersLoaded) {
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
            this.customersLoaded = true;
        }
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
