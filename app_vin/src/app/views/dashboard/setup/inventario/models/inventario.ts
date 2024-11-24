import { ProveedorDto } from './proveedor';  // Importa el modelo de Proveedor

export class Inventario {
    id: number;
    nombre?: string;           // Nombre del inventario
    descripcion?: string;      // Descripción del inventario
    stock: number;             // Cantidad de productos en stock
    modelo?: string;           // Modelo del producto
    codigo?: number;           // Código único del producto
    proveedorId: number;      // ID del proveedor
    proveedorDto?: ProveedorDto;  // Objeto proveedor relacionado
    inventarioDetalle: any[];  // Lista de detalles del inventario (puedes modificar esto según tu estructura)

    constructor() {
        this.stock = 0;  // Inicializa el stock a 0 por defecto
        this.inventarioDetalle = [];
    }
}
