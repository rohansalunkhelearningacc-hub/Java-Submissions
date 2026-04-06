public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Savings Account Deposit: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Savings Account Withdraw: " + amount);
        } else {
            System.out.println("Insufficient balance in Savings Account");
        }
    }

    @Override
    public void display() {
        System.out.println("Savings Account -> Account No: " + accountNumber +
                ", Balance: " + balance +
                ", Interest Rate: " + interestRate);
    }
}