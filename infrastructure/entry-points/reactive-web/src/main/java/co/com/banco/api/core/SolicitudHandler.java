package co.com.banco.api.core;

import co.com.banco.api.ValidationUtils;
import co.com.banco.api.dto.SolicitudRequest;
import co.com.banco.model.solicitud.Solicitud;
import co.com.banco.usecase.solicitud.SolicitudUseCase;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.util.UUID;


@RequiredArgsConstructor
public class SolicitudHandler {

    private  final SolicitudUseCase solicitudService;
    private final ObjectMapper mapper;
    private final Validator validator;
    private final TransactionalOperator transactionalOperator;

    public Mono<ServerResponse> handleCreate(SolicitudRequest request) {
        return validateRequest(request)
                .map(this::mapToEntity)
                .flatMap(this::persistSolicitante)
                .flatMap(result -> ServerResponse.status(HttpStatus.CREATED).bodyValue(result));

    }

    private Mono<SolicitudRequest> validateRequest(SolicitudRequest request) {
        return ValidationUtils.validateRequest(validator, request);
    }

    private Solicitud mapToEntity(SolicitudRequest request) {
        Solicitud solicitud = mapper.map(request, Solicitud.class);
        solicitud.setId(UUID.randomUUID());
        return solicitud;
    }

    private Mono<String> persistSolicitante(Solicitud solicitud) {
        return solicitudService.createSolicitud(solicitud)
                .as(transactionalOperator::transactional);
    }
}
