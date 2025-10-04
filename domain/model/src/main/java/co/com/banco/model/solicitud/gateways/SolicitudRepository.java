package co.com.banco.model.solicitud.gateways;

import co.com.banco.model.solicitud.Solicitud;
import reactor.core.publisher.Mono;

public interface SolicitudRepository {

    Mono<String> createSolicitud(Solicitud solicitud);
}
