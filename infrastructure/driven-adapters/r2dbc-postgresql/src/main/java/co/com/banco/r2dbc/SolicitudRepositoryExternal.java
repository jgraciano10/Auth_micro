package co.com.banco.r2dbc;

import co.com.banco.r2dbc.dto.Solicitudes;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;


public interface SolicitudRepositoryExternal extends ReactiveCrudRepository<Solicitudes, UUID>, ReactiveQueryByExampleExecutor<Solicitudes> {

}
