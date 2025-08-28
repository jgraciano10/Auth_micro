package co.com.banco.api.config;

import co.com.banco.api.core.SolicitudHandler;
import co.com.banco.usecase.solicitud.SolicitudUseCase;
import jakarta.validation.Validator;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
public class HandlerConfig {

    @Bean
    public SolicitudHandler solicitudHandler(
            SolicitudUseCase solicitudService,
            ObjectMapper mapper,
            Validator validator,
            TransactionalOperator transactionalOperator
    ){
        return new SolicitudHandler(solicitudService, mapper, validator, transactionalOperator);
    }

}
