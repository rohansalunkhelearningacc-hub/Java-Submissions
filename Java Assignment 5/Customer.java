import java.util.ArrayList;

public class Customer {
    private int customerId;
    private String name;
    private ArrayList<Account> accounts;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    public void displayCustomerDetails() {
        System.out.println("\nCustomer ID: " + customerId + ", Name: " + name);
        System.out.println("Accounts:");

        for (Account acc : accounts) {
            acc.display();  // polymorphism
        }
    }
}