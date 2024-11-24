package com.example.msinventario.Entity;

import com.example.msinventario.dto.ProductoDto; // Importa el DTO de Producto
import jakarta.persistence.*; // Importa anotaciones de JPA
import lombok.Data; // Importa la anotación de Lombok para generar automáticamente getters y setters

import java.util.Date; // Importa la clase Date para manejar fechas

@Entity // Indica que esta clase es una entidad JPA
@Data // Genera automáticamente los métodos getters, setters, toString, equals y hashCode
public class InventarioDetalle {
    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor de ID
    private Integer id; // ID único del detalle de inventario

    private String categoria; // Categoría del producto

    private Double precio; // Precio del producto

    private String estadoProducto; // Estado del producto (ej. disponible, agotado)

    private String modelo; // Modelo del producto

    private Integer codigo; // Código único del producto

    private Date fechaRecibido; // Fecha en que se recibió el producto

    private Integer productoId; // ID del producto relacionado

    @Transient // Indica que este campo no se persiste en la base de datos
    private ProductoDto productoDto; // Objeto Producto, no persistido en la base de datos

    // Constructor por defecto que inicializa precio a 0
    public InventarioDetalle() {
        this.precio = 0.0; // Inicializa el precio a 0.0
    }
}
