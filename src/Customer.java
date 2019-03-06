public class Customer {

    //fields

    private String name;
    private int ID;
    private double balance;
    private String bank;

    // constructor

    public Customer(String name, int ID, double balance, String bank) {
        this.name = name;
        this.ID = ID;
        this.balance = balance;
        this.bank = bank;
    }

    // setters and getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        String formattedBalance = String.format("%.02f", this.balance);
        return this.name + ": Balance $" + formattedBalance;
    }
}
