import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { abcForms } from '../../../../../../../environments/generals';
import { Category } from '../../models/category';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';

@Component({
    selector: 'app-categories-list',
    imports: [CommonModule, RouterOutlet, MatButtonModule, MatIconModule],
    standalone: true,
    template: `
        <div class="w-full mx-auto p-6 bg-white rounded overflow-hidden shadow-lg">
            <!-- Encabezado principal -->
            <div class="flex justify-between items-center mb-2 bg-slate-300 text-black p-4 rounded">
                <h2 class="text-2xl font-bold">
                    Lista de <span class="text-primary">Categorias</span>
                </h2>
                <button mat-flat-button [color]="'primary'" (click)="goNew()">
                    <mat-icon [svgIcon]="'heroicons_outline:plus'"></mat-icon>
                    <span class="ml-2">Nueva Categoria</span>
                </button>
            </div>
            <div class="bg-white rounded overflow-hidden shadow-lg">
                <div class="p-2 overflow-scroll px-0">
                    <table class="w-full table-fixed">
                        <thead class="bg-primary-600 text-white">
                        <tr>
                            <th class="w-1/6 table-head text-center px-5 border-r">#</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">Franquicia</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">Tipo</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">Popularidad</th>
                            <th class="w-1/6 table-header text-center border-r">Estado</th>
                            <th class="w-2/6 table-header text-center">Acciones</th>
                        </tr>
                        </thead>
                        <tbody class="bg-white">
                        <tr *ngFor="let r of categories; let i = index" class="hover:bg-gray-100">
                            <td class="w-1/6 p-2 text-center border-b">{{ i + 1 }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ r.franchise }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ r.type }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ r.popularity }}</td>
                            <td class="w-1/6 p-2 text-center border-b text-sm">
                                <div class="relative grid items-center font-sans font-bold uppercase whitespace-nowrap select-none bg-green-500/20 text-green-600 py-1 px-2 text-xs rounded-md" style="opacity: 1">
                                    <span class="">ACTIVO</span>
                                </div>
                            </td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">
                                <div class="flex justify-center space-x-3">
                                    <mat-icon class="text-amber-400 hover:text-amber-500 cursor-pointer" (click)="goEdit(r.id)">edit</mat-icon>
                                    <mat-icon
                                        class="text-rose-500 hover:text-rose-600 cursor-pointer"
                                        (click)="goDelete(r.id)"
                                        [attr.data-category-id]="r.id">delete_sweep</mat-icon>
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
export class CategoryListComponent implements OnInit {
    abcForms: any;
    @Input() categories: Category[] = [];
    @Output() eventNew = new EventEmitter<boolean>();
    @Output() eventEdit = new EventEmitter<number>();
    @Output() eventDelete = new EventEmitter<number>();
    @Output() eventAssign = new EventEmitter<number>();

    constructor(private _matDialog: MatDialog) {}

    ngOnInit() {
        this.abcForms = abcForms;
        console.log('Categorías cargadas:', this.categories);
    }

    public goNew(): void {
        this.eventNew.emit(true);
    }

    public goEdit(id: number): void {
        console.log('Editando categoría:', id);
        this.eventEdit.emit(id);
    }

    public goDelete(id: number): void {
        if (!id) {
            console.error('ID inválido para eliminar:', id);
            return;
        }

        // Verificar si la categoría existe antes de emitir
        const categoryExists = this.categories.some(cat => cat.id === id);
        if (!categoryExists) {
            console.error('La categoría no existe en la lista:', id);
            return;
        }

        console.log('Eliminando categoría:', id);
        console.log('Categoría encontrada:', this.categories.find(cat => cat.id === id));
        this.eventDelete.emit(id);
    }

    public goAssign(id: number): void {
        this.eventAssign.emit(id);
    }

    // Método para debugging
    ngOnChanges() {
        console.log('Categorías actualizadas:', this.categories);
    }
}
