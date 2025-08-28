package co.com.banco.r2dbc;

import co.com.banco.exceptions.ErrorConectandoDBException;
import co.com.banco.exceptions.SolicitanteNotExistException;
import reactor.core.publisher.Mono;

public class ValidationDBUtils {
    private ValidationDBUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> Mono<T> getErrors(Mono<T> mono) {

        return mono
                .onErrorResume( ex -> {
                    if(ex.getMessage().contains("violates foreign key constraint \"fk_documento_identidad")){
                        return Mono.error(new SolicitanteNotExistException("No existe un solicitante asociado a ese numero de documento, por favor pimero añadir al participante"));
                    }
                    return Mono.error(new ErrorConectandoDBException(ex.getMessage()));
                });
    }
}
