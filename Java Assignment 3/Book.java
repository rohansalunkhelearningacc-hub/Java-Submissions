// Book Class
public class Book {

    private String title;
    private String author;
    private String genre;
    private double price;

    // Default Constructor
    public Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.genre = "Unknown";
        this.price = 0.0;
    }

    // Parameterized Constructor (Normal)
    public Book(String title, String author, String genre, double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    // Constructor that throws InvalidPriceException
    public Book(String title, String author, String genre, double price, boolean validate)
            throws InvalidPriceException {

        if (price < 0) {
            throw new InvalidPriceException("Price cannot be negative!");
        }

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    // Getters
    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    // Display Method
    public void display() {
        System.out.println("Title: " + title +
                ", Author: " + author +
                ", Genre: " + genre +
                ", Price: " + price);
    }
}