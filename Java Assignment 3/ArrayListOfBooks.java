import java.util.ArrayList;

public class ArrayListOfBooks {

    public static void main(String[] args) {

        ArrayList<Book> bookList = new ArrayList<>();

        // Adding normal books
        bookList.add(new Book("The Alchemist", "Paulo Coelho", "Fiction", 350));
        bookList.add(new Book("Clean Code", "Robert Martin", "Programming", 500));
        bookList.add(new Book("Wings of Fire", "A.P.J Abdul Kalam", "Biography", 300));

        // Using constructor that throws exception
        try {
            Book invalidBook = new Book("Invalid Book", "Unknown", "Fiction", -200, true);
            bookList.add(invalidBook);
        } catch (InvalidPriceException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }

        // Display all books
        System.out.println("\nList of Books:");
        double totalPrice = 0;

        for (Book book : bookList) {
            book.display();
            totalPrice += book.getPrice();
        }

        // Calculate Average Price
        double average = totalPrice / bookList.size();
        System.out.println("\nAverage Price of Books: " + average);

        // Print Fiction books using forEach()
        System.out.println("\nFiction Books:");

        bookList.forEach(book -> {
            if (book.getGenre().equalsIgnoreCase("Fiction")) {
                book.display();
            }
        });
    }
}