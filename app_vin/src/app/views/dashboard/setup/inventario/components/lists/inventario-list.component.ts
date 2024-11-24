import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Inventario } from '../../models/inventario'; // Asegúrate de importar el modelo de Inventario
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';

@Component({
    selector: 'app-inventario-list',
    imports: [CommonModule, RouterOutlet, MatButtonModule, MatIconModule],
    standalone: true,
    template: `
        <div class="w-full mx-auto p-6 bg-white rounded overflow-hidden shadow-lg">
            <!-- Encabezado principal -->
            <div class="flex justify-between items-center mb-2 bg-slate-300 text-black p-4 rounded">
                <h2 class="text-2xl font-bold">
                    Lista de <span class="text-primary">Inventarios</span>
                </h2>
                <button mat-flat-button [color]="'primary'" (click)="goNew()">
                    <mat-icon [svgIcon]="'heroicons_outline:plus'"></mat-icon>
                    <span class="ml-2">Nuevo Inventario</span>
                </button>
            </div>
            <div class="bg-white rounded overflow-hidden shadow-lg">
                <div class="p-2 overflow-scroll px-0">
                    <table class="w-full table-fixed">
                        <thead class="bg-primary-600 text-white">
                        <tr>
                            <th class="w-1/6 table-head text-center px-5 border-r">#</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">Nombre</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">Descripción</th>
                            <th class="w-1/6 table-header text-center px-5 border-r">Stock</th>
                            <th class="w-1/6 table-header text-center">Acciones</th>
                        </tr>
                        </thead>
                        <tbody class="bg-white">
                        <tr *ngFor="let r of inventarios; let i = index" class="hover:bg-gray-100">
                            <td class="w-1/6 p-2 text-center border-b">{{ i + 1 }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ r.nombre }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ r.descripcion }}</td>
                            <td class="w-1/6 p-2 text-center border-b text-sm">{{ r.stock }}</td>
                            <td class="w-1/6 p-2 text-center border-b text-sm">
                                <div class="flex justify-center space-x-3">
                                    <mat-icon class="text-amber-400 hover:text-amber-500 cursor-pointer" (click)="goEdit(r.codigo)">edit</mat-icon>
                                    <mat-icon class="text-rose-500 hover:text-rose-600 cursor-pointer" (click)="goDelete(r.codigo)">delete_sweep</mat-icon>
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
export class InventarioListComponent implements OnInit {
    @Input() inventarios: Inventario[] = []; // Lista de inventarios
    @Output() eventNew = new EventEmitter<boolean>(); // Evento para crear un nuevo inventario
    @Output() eventEdit = new EventEmitter<number>(); // Evento para editar inventario
    @Output() eventDelete = new EventEmitter<number>(); // Evento para eliminar inventario

    constructor(private _matDialog: MatDialog) {}

    ngOnInit() {
        console.log('Inventarios cargados:', this.inventarios);
    }

    // Función para crear un nuevo inventario
    public goNew(): void {
        this.eventNew.emit(true);
    }

    // Función para editar un inventario
    public goEdit(codigo: number): void {
        console.log('Editando inventario:', codigo);
        this.eventEdit.emit(codigo);
    }

    // Función para eliminar un inventario
    public goDelete(codigo: number): void {
        if (!codigo) {
            console.error('Código inválido para eliminar:', codigo);
            return;
        }

        // Verificar si el inventario existe antes de emitir el evento de eliminación
        const inventarioExists = this.inventarios.some(inv => inv.codigo === codigo);
        if (!inventarioExists) {
            console.error('El inventario no existe en la lista:', codigo);
            return;
        }

        console.log('Eliminando inventario:', codigo);
        this.eventDelete.emit(codigo);
    }
}
