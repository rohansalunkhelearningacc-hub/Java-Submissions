public class LoanAccount extends Account {
    private double loanAmount;

    public LoanAccount(int accountNumber, double loanAmount) {
        super(accountNumber, 0);
        this.loanAmount = loanAmount;
    }

    @Override
    public void deposit(double amount) {
        loanAmount -= amount;
        System.out.println("Loan Repayment: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        loanAmount += amount;
        System.out.println("Loan Increased by: " + amount);
    }

    @Override
    public void display() {
        System.out.println("Loan Account -> Account No: " + accountNumber +
                ", Remaining Loan: " + loanAmount);
    }
}