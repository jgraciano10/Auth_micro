package co.com.banco.api;

import co.com.banco.api.core.SolicitudHandler;
import co.com.banco.api.dto.SolicitudRequest;
import co.com.banco.exceptions.CustomeBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

private  final SolicitudHandler solicitudHandler;


    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(SolicitudRequest.class)
                .flatMap(solicitudHandler::handleCreate)
                .transform(ValidationUtils::handleErrors)
                .onErrorResume(CustomeBadRequestException.class, e ->
                        ServerResponse.badRequest().bodyValue(e.getMessage())
                ).onErrorResume(Exception.class, e ->
                        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage())
                );

    }

}
