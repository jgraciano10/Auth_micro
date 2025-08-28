package co.com.banco.model.exceptions;

public class TipoPrestamoNotExistException extends RuntimeException {
    public TipoPrestamoNotExistException(String message) {
        super(message);
    }
}
