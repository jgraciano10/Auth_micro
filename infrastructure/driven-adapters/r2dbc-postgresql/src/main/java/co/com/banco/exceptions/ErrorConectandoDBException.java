package co.com.banco.exceptions;

public class ErrorConectandoDBException extends RuntimeException {
    public ErrorConectandoDBException(String message) {
        super(message);
    }
}
