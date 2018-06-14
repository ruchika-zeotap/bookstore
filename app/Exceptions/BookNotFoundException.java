package Exceptions;

public class BookNotFoundException extends Exception {

    public BookNotFoundException() { super(); }

    public BookNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    public BookNotFoundException(String message) {
        super(message);
    }

}
