import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';

@Component({
    selector: 'app-products-list',
    imports: [CommonModule, RouterOutlet, MatButtonModule, MatIconModule],
    standalone: true,
    template: `
        <div class="w-full mx-auto p-6 bg-white rounded overflow-hidden shadow-lg">
            <!-- Encabezado principal -->
            <div class="flex justify-between items-center mb-2 bg-slate-300 text-black p-4 rounded">
                <h2 class="text-2xl font-bold">
                    Lista de <span class="text-primary">Productos</span>
                </h2>
                <button mat-flat-button [color]="'primary'" (click)="goNew()">
                    <mat-icon [svgIcon]="'heroicons_outline:plus'"></mat-icon>
                    <span class="ml-2">Nuevo Producto</span>
                </button>
            </div>

            <div class="bg-white rounded overflow-hidden shadow-lg">
                <div class="p-2 overflow-scroll px-0">
                    <table class="w-full table-fixed">
                        <thead class="bg-primary-600 text-white">
                        <tr>
                            <th class="w-1/6 table-head text-center px-5 border-r">#</th>
                            <th class="w-1/6 table-header text-center px-5 border-r">Imagen</th>  <!-- Nueva columna -->
                            <th class="w-2/6 table-header text-center px-5 border-r">Nombre</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">Modelo</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">Código</th>
                            <th class="w-1/6 table-header text-center border-r">Estado</th>
                            <th class="w-2/6 table-header text-center">Acciones</th>
                        </tr>
                        </thead>
                        <tbody class="bg-white">
                        <tr *ngFor="let product of products; let i = index" class="hover:bg-gray-100">
                            <td class="w-1/6 p-2 text-center border-b">{{ i + 1 }}</td>
                            <td class="w-1/6 p-2 text-center border-b">
                                <img [src]="'data:image/jpeg;base64,' + product.imagen"
                                     alt="Imagen producto"
                                     class="w-12 h-12 object-cover mx-auto rounded"
                                     *ngIf="product.imagen"/>
                                <mat-icon *ngIf="!product.imagen" class="text-gray-400">image_not_available</mat-icon>
                            </td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ product.nombre }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ product.modelo }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ product.codigo }}</td>
                            <td class="w-1/6 p-2 text-center border-b text-sm">
                                <div class="relative grid items-center font-sans font-bold uppercase whitespace-nowrap select-none bg-green-500/20 text-green-600 py-1 px-2 text-xs rounded-md">
                                    <span>ACTIVO</span>
                                </div>
                            </td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">
                                <div class="flex justify-center space-x-3">
                                    <mat-icon class="text-amber-400 hover:text-amber-500 cursor-pointer" (click)="goEdit(product.id)">edit</mat-icon>
                                    <mat-icon class="text-rose-500 hover:text-rose-600 cursor-pointer" (click)="goDelete(product.id)">delete_sweep</mat-icon>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    `,
})
export class ProductListComponent implements OnInit {
    @Input() products: Product[] = [];
    @Output() eventNew = new EventEmitter<boolean>();
    @Output() eventEdit = new EventEmitter<number>();
    @Output() eventDelete = new EventEmitter<number>();

    constructor(private _matDialog: MatDialog) {}

    ngOnInit() {}

    public goNew(): void {
        this.eventNew.emit(true); // Emitir el evento para crear un nuevo producto
    }

    public goEdit(id: number): void {
        this.eventEdit.emit(id); // Emitir el evento con el ID del producto a editar
    }

    public goDelete(id: number): void {
        // Confirmar la eliminación del producto
        const confirmation = window.confirm('¿Estás seguro de que deseas eliminar este producto?');
        if (confirmation) {
            this.eventDelete.emit(id); // Emitir el evento con el ID del producto a eliminar
        }
    }
}
