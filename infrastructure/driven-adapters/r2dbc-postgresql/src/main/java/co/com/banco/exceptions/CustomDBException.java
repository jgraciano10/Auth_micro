package co.com.banco.exceptions;

public class CustomDBException extends RuntimeException {
    public CustomDBException(String message) {
        super(message);
    }
}
