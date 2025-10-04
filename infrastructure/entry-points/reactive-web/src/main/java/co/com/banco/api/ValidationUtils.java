package co.com.banco.api;

import co.com.banco.exceptions.CustomDBException;
import co.com.banco.exceptions.CustomeBadRequestException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public class ValidationUtils {

    private ValidationUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> Mono<T> validateRequest(Validator validator, T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            List<String> errorMsg = violations.stream()
                    .map(err -> err.getPropertyPath() + ":" + err.getMessage())
                    .toList();
            return Mono.error(new ValidationException(String.join(", ", errorMsg)));
        }
        return Mono.just(dto);
    }


    public static <T> Mono<T> handleErrors(Mono<T> mono) {
        return mono
                .onErrorResume(ServerWebInputException.class, e -> {
                                if (e.getCause().toString().contains("not one of the values accepted for Enum class")) {
                                    return Mono.error(new CustomeBadRequestException("El tipo de prestamo debe ser uno de los siguientes: LIBRE_INVERSION, COMPRA_CARTERA, VEHICULO, EDUCACION"));
                                }
                        return Mono.error(new CustomeBadRequestException("Revisa campos de entrada"));
                })
                .onErrorResume(ValidationException.class, e -> Mono.error(new CustomeBadRequestException(e.getMessage())))
                .onErrorResume(CustomDBException.class, e -> Mono.error(new CustomeBadRequestException(e.getMessage())));
    }
}
