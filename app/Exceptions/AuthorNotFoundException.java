package Exceptions;

public class AuthorNotFoundException extends Exception {

    public AuthorNotFoundException() { super(); }

    public AuthorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
