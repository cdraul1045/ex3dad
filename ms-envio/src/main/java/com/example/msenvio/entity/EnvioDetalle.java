package com.example.msenvio.entity;

import com.example.msenvio.dto.ProductoDto; // Importa el DTO Producto
import jakarta.persistence.*; // Importa las anotaciones de JPA
import lombok.Data; // Importa la anotación Data de Lombok

@Entity // Indica que esta clase es una entidad JPA
@Data // Genera automáticamente métodos getters, setters, toString, equals y hashCode
public class EnvioDetalle {
    @Id // Marca el campo id como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el valor del ID automáticamente
    private Integer id;

    private Integer cantidad; // Cantidad de productos

    private Double precioUnitario; // Precio unitario del producto

    private String estado; // Estado del producto en el envío

    private String modelo; // Modelo del producto

    private Integer productoId; // ID del producto

    @Transient // Indica que este campo no debe ser persistido en la base de datos
    private ProductoDto productoDto; // Objeto Producto, no persistido en la base de datos

    // Constructor por defecto
    public EnvioDetalle() {
        this.precioUnitario = 0.0; // Inicializa el precio unitario a 0.0
        this.cantidad = 0; // Inicializa la cantidad a 0
    }

    // Getter para productoId
    public Integer getProductoId() {
        return productoId; // Retorna el ID del producto
    }
}
