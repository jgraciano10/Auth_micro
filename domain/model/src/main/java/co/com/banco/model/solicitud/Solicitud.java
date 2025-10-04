package co.com.banco.model.solicitud;
import lombok.*;

import java.util.UUID;

@Data
public class Solicitud {

    public enum TipoPrestamo {
        LIBRE_INVERSION,
        COMPRA_CARTERA,
        VEHICULO,
        EDUCACION
    }
    public enum EstadoSolicitud{
        PENDIENTE_DE_REVISION,
        RECHAZADA,
        APROBADA,
        PRE_APROBADO
    }
    private UUID id;
    private String documentoIdentidad;
    private Integer plazo;
    private Integer monto;
    private TipoPrestamo tipoPrestamo;
    private EstadoSolicitud estadoSolicitud;


}
