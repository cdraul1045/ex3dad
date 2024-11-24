import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CategoryService } from "../../../../../providers/services/setup/category.service";
import { ConfirmDialogService } from "../../../../../shared/confirm-dialog/confirm-dialog.service";
import { CategoryListComponent } from "../components";
import { CategoryNewComponent } from '../components/form/category-new.component';
import { CategoryEditComponent } from '../components/form/category-edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Category } from '../models/category';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { catchError, of, take } from 'rxjs';

@Component({
    selector: 'app-categories-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        CategoryListComponent,
        CategoryNewComponent,
        CategoryEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-categories-list
            class="w-full"
            [categories]="categories"
            (eventNew)="eventNew($event)"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-categories-list>

        <!-- Display error message -->
        <div *ngIf="error" class="error-message">
            <p>{{ error }}</p>
        </div>
    `,
})
export class CategoryContainerComponent implements OnInit {
    public error: string = ''; // To store any error message
    public categories: Category[] = [];

    constructor(
        private _categoryService: CategoryService,
        private _confirmDialogService: ConfirmDialogService,
        private _matDialog: MatDialog
    ) {}

    ngOnInit(): void {
        this.getCategories();
    }

    // Obtener todas las categorías
    getCategories(): void {
        this._categoryService.getAll$().pipe(
            take(1),
            catchError((error) => {
                console.error('Error al cargar categorías:', error);
                this.error = 'No se pudieron cargar las categorías. Intente nuevamente.';
                return of([]); // Retorna lista vacía en caso de error
            })
        ).subscribe((response) => {
            this.categories = response;
        });
    }

    // Abrir modal para crear una nueva categoría
    public eventNew($event: boolean): void {
        if ($event) {
            const categoryForm = this._matDialog.open(CategoryNewComponent);
            categoryForm.componentInstance.title = 'Nueva Categoría';

            categoryForm.afterClosed().subscribe((result: Category) => {
                if (result) {
                    this._categoryService.add$(result).pipe(
                        take(1),
                        catchError((error) => {
                            console.error('Error al guardar categoría:', error);
                            this.error = 'No se pudo guardar la categoría. Intente nuevamente.';
                            return of(null);
                        })
                    ).subscribe((response) => {
                        if (response) {
                            this.categories.push(response); // Agrega a la lista local
                        }
                    });
                }
            });
        }
    }

    // Abrir modal para editar una categoría
    eventEdit(idCategory: number): void {
        const categoryToEdit = this.categories.find(c => c.id === idCategory);
        if (!categoryToEdit) {
            this.error = 'Categoría no encontrada para edición.';
            return;
        }

        const categoryForm = this._matDialog.open(CategoryEditComponent);
        categoryForm.componentInstance.title = `Editar <b>${categoryToEdit.franchise || categoryToEdit.id}</b>`;
        categoryForm.componentInstance.category = { ...categoryToEdit }; // Copia para evitar mutación

        categoryForm.afterClosed().subscribe((result: Category) => {
            if (result) {
                this._categoryService.update$(idCategory, result).pipe(
                    take(1),
                    catchError((error) => {
                        console.error('Error al editar categoría:', error);
                        this.error = 'No se pudo editar la categoría. Intente nuevamente.';
                        return of(null);
                    })
                ).subscribe((response) => {
                    if (response) {
                        const index = this.categories.findIndex(c => c.id === idCategory);
                        if (index !== -1) {
                            this.categories[index] = response; // Actualiza el elemento localmente
                        }
                    }
                });
            }
        });
    }

    // Eliminar categoría
    public eventDelete(idCategory: number) {
        this._confirmDialogService.confirmDelete(
            {
                // title: 'Confirmación Personalizada',
                // message: `¿Quieres proceder con esta acción ${}?`,
            }
        ).then(() => {
            this._categoryService.delete$(idCategory).subscribe((response) => {
                if (response) {
                    // Actualizamos la lista local filtrando el elemento eliminado
                    this.categories = this.categories.filter(category => category.id !== idCategory);
                }
            });
        }).catch(() => {
            // Usuario canceló la acción
        });
    }
}
