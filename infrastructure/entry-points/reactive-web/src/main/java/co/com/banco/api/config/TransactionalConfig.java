package co.com.banco.api.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Configuration
public class TransactionalConfig {

    @Bean
    public TransactionalOperator readCommited(ConnectionFactory connectionFactory) {
        DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
        txDef.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        return TransactionalOperator.create(new R2dbcTransactionManager(connectionFactory), txDef);
    }

}