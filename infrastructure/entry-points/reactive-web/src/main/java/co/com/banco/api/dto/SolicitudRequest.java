package co.com.banco.api.dto;

import co.com.banco.model.solicitud.Solicitud;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitudRequest {

    @NotBlank(message = "El documento no puede estar vacío")
    private String documentoIdentidad;

    @NotNull(message = "Se debe incluir la cantidad de plazo en meses")
    @Min(value = 0, message = "El plazo en meses no puede ser negativo")
    @Max(value= 24, message = "El plazo en meses no puede ser superior a 48")
    private Integer plazo;

    @NotNull(message = "Se debe incluir el monto del prestamo")
    @Min(value = 0, message = "El monto del prestamo no puede ser negativo")
    @Max(value= 350000000, message = "El monto del prestamo no puede ser superior a 350000000")
    private Integer monto;

    @NotNull(message = "Se debe incluir el tipo de prestamo")
    private Solicitud.TipoPrestamo   tipoPrestamo;


    private Solicitud.EstadoSolicitud estadoSolicitud = Solicitud.EstadoSolicitud.PENDIENTE_DE_REVISION;

}
