package co.com.banco.exceptions;

public class SolicitanteNotExistException extends RuntimeException {
    public SolicitanteNotExistException(String message) {
        super(message);
    }
}
