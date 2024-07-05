public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super("Book is not available");
    }
}
