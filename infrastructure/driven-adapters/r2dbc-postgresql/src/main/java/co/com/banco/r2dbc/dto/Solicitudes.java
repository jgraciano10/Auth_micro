package co.com.banco.r2dbc.dto;

import co.com.banco.model.solicitud.Solicitud;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class Solicitudes {

   @Id
   private UUID id;

   @Column(name = "documento_identidad")
   private String documentoIdentidad;

   private Integer plazo;

   private Integer monto;

   @Column(name = "tipo_prestamo")
   @Enumerated(EnumType.STRING)
   private Solicitud.TipoPrestamo tipoPrestamo;

   @Enumerated(EnumType.STRING)
   private Solicitud.EstadoSolicitud estadoSolicitud;

}


