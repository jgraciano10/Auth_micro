package co.com.banco.model.exceptions;

public class SolicitanteNotExistException extends RuntimeException {
    public SolicitanteNotExistException(String message) {
        super(message);
    }
}
