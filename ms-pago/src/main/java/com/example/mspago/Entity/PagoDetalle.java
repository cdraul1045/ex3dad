package com.example.mspago.Entity;

import com.example.mspago.dto.ProductoDto; // Importa el DTO de Producto
import jakarta.persistence.*; // Importa anotaciones de JPA
import lombok.Data; // Importa la anotación de Lombok para generar automáticamente getters y setters

import java.util.Date; // Importa la clase Date para manejar fechas

@Entity // Indica que esta clase es una entidad JPA
@Data // Genera automáticamente los métodos getters, setters, toString, equals y hashCode
public class PagoDetalle {
    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor de ID
    private Integer id; // ID único del detalle de pago

    private String descripcion; // Descripción del producto o servicio

    private Double precio; // Precio del producto o servicio

    private Integer cantidad; // Cantidad del producto o servicio

    private Double subtotal; // Subtotal del producto (precio * cantidad)

    private Integer codigo; // Código único del producto o servicio

    private Date fecha; // Fecha en que se realizó el pago

    private Integer productoId; // ID del producto relacionado

    @Transient // Indica que este campo no se persiste en la base de datos
    private ProductoDto productoDto; // Objeto Producto, no persistido en la base de datos

    // Constructor por defecto que inicializa precio y cantidad a 0
    public PagoDetalle() {
        this.precio = 0.0; // Inicializa el precio a 0.0
        this.cantidad = 0; // Inicializa la cantidad a 0
        this.subtotal = 0.0; // Inicializa el subtotal a 0.0
    }
}
