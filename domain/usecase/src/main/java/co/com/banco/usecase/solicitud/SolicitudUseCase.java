package co.com.banco.usecase.solicitud;

import co.com.banco.model.solicitud.Solicitud;
import co.com.banco.model.solicitud.gateways.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SolicitudUseCase {

    private final SolicitudRepository repository;

    public Mono<String> createSolicitud(Solicitud solicitud){
        return repository.createSolicitud(solicitud);
    }
}
