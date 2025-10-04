package co.com.banco.r2dbc;

import co.com.banco.exceptions.CustomDBException;
import co.com.banco.model.solicitud.Solicitud;
import co.com.banco.model.solicitud.gateways.SolicitudRepository;
import co.com.banco.r2dbc.dto.Solicitudes;
import co.com.banco.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Solicitud/* change for domain model */,
        Solicitudes/* change for adapter model */,
        UUID,
        SolicitudRepositoryExternal
> implements SolicitudRepository {
    public MyReactiveRepositoryAdapter(SolicitudRepositoryExternal repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Solicitud.class));
    }

    @Override
    public Mono<String> createSolicitud(Solicitud solicitud) {
        return save(solicitud)
                .thenReturn("Solicitud guardada con exito")
                .transform(ValidationDBUtils::getErrors)
                .onErrorResume(e ->
                {
                    System.out.println(e.getClass() );
                    return Mono.error(new CustomDBException(e.getMessage()));

                });

    }
}
