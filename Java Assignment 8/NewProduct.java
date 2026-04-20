// NewProduct.java
// Concrete Product — modern product that directly implements Product

public class NewProduct implements Product {
    private String name;

    public NewProduct(String name) {
        this.name = name;
    }

    @Override
    public void displayDetails() {
        System.out.println("New Product: " + name);
    }
}
