public class VectorException extends Exception {

    public VectorException(String message) {
        super(message);
    }

    public void showError() {
        System.out.println("Vector Exception: " + getMessage());
    }
}