package com.example.mspago.Entity;

import com.example.mspago.dto.ClienteDto; // Importa el DTO de Cliente
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Para ignorar propiedades durante la serialización
import jakarta.persistence.*; // Importa anotaciones de JPA
import lombok.Data; // Importa la anotación de Lombok para generar automáticamente getters y setters

import java.time.LocalDateTime;
import java.util.List; // Importa la clase List para manejar listas

@Entity // Indica que esta clase es una entidad JPA
@Data // Genera automáticamente los métodos getters, setters, toString, equals y hashCode
public class Pago {
    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor de ID
    private Integer id; // ID único del pago

    private String nombre; // Nombre del pago

    private String descripcion; // Descripción del pago

    private Double monto; // Monto total del pago

    private String metodo; // Método de pago utilizado

    private Integer clienteId; // Referencia externa del cliente

    private String estado; // Estado del pago (ej.: PENDING, COMPLETED, FAILED)

    private Integer codigo; // Código único de la transacción

    private LocalDateTime fechaCreacion; // Fecha de creación del pago

    private LocalDateTime fechaActualizacion; // Fecha de última actualización

    // Constructor por defecto que inicializa monto a 0
    public Pago() {
        this.monto = 0.0; // Inicializa el monto a 0.0
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignora propiedades innecesarias durante la serialización
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Define una relación uno a muchos
    @JoinColumn(name = "pago_id") // Nombre de la columna en la tabla hija que hace referencia a esta entidad
    private List<PagoDetalle> pagoDetalle; // Lista de detalles de pago

    @Transient // Indica que este campo no se persiste en la base de datos
    private ClienteDto clienteDto; // Objeto Cliente, no persistido en la base de datos
}
