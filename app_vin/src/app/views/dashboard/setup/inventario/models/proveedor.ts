export class ProveedorDto {
    id: number;             // ID único del proveedor
    nombre: string;         // Nombre del proveedor
    empresa: string;        // Nombre de la empresa
    direccion: string;      // Dirección del proveedor
    telefono: string;       // Teléfono del proveedor

    constructor(id: number, nombre: string, empresa: string, direccion: string, telefono: string) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
