package co.com.banco.exceptions;

public class CustomeBadRequestException extends RuntimeException {
    public CustomeBadRequestException(String message) {
        super(message);
    }
}
