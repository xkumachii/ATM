import java.util.ArrayList;

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