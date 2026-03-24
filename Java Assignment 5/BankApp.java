import java.util.ArrayList;

public class BankApp {
    public static void main(String[] args) {

        ArrayList<Customer> customers = new ArrayList<>();

        // Create Customers
        Customer c1 = new Customer(1, "Rohan");
        Customer c2 = new Customer(2, "Amit");

        // Create Accounts
        SavingsAccount sa1 = new SavingsAccount(101, 5000, 4.5);
        LoanAccount la1 = new LoanAccount(201, 20000);

        SavingsAccount sa2 = new SavingsAccount(102, 8000, 5.0);
        LoanAccount la2 = new LoanAccount(202, 15000);

        // Add accounts to customers
        c1.addAccount(sa1);
        c1.addAccount(la1);

        c2.addAccount(sa2);
        c2.addAccount(la2);

        // Add customers to list
        customers.add(c1);
        customers.add(c2);

        // Perform some transactions
        sa1.deposit(1000);
        sa1.withdraw(2000);

        la1.deposit(5000);   // repay loan
        la1.withdraw(2000);  // increase loan

        // Display all customers info
        for (Customer c : customers) {
            c.displayCustomerDetails();
        }
    }
}