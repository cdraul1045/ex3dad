package com.example.msenvio.entity;

import com.example.msenvio.dto.ClienteDto; // Importa el DTO Cliente
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Para ignorar propiedades en JSON
import jakarta.persistence.*; // Importa las anotaciones de JPA
import lombok.Data; // Importa la anotación Data de Lombok

import java.time.LocalDateTime; // Para manejar fechas y horas
import java.util.List; // Para manejar listas

@Data // Genera automáticamente métodos getters, setters, toString, equals y hashCode
@Entity // Indica que esta clase es una entidad JPA
public class Envio {
    @Id // Marca el campo id como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el valor del ID automáticamente
    private Integer id;

    private String nombreDestinatario; // Nombre del destinatario
    private String nombreEmpresaEnvio; // Nombre de la empresa de envío
    private String destino; // Destino del envío
    private LocalDateTime fechaDeEnvio; // Fecha y hora de envío
    private LocalDateTime fechaDeEntrega; // Fecha y hora de entrega
    private Integer clienteId; // ID de referencia al cliente

    // Relación con EnvioDetalle
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignora ciertas propiedades para evitar problemas de serialización
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Relación uno a muchos con EnvioDetalle
    @JoinColumn(name = "envio_id") // Especifica la columna de unión en EnvioDetalle
    private List<EnvioDetalle> envioDetalle; // Lista de detalles de envío

    // Cliente se trata como un dato transitorio que no se guarda en la BD
    @Transient // Indica que este campo no debe ser persistido en la base de datos
    private ClienteDto clienteDto; // Información del cliente asociada al envío
}
