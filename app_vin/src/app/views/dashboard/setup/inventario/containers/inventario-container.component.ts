import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { InventarioService } from "../../../../../providers/services/setup/inventario.service"; // Servicio para inventarios
import { ConfirmDialogService } from "../../../../../shared/confirm-dialog/confirm-dialog.service";
import { InventarioListComponent } from "../components";
import { InventarioNewComponent } from '../components/form/inventario-new.component';
import { InventarioEditComponent } from '../components/form/inventario-edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Inventario } from '../models/inventario';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { catchError, of, take } from 'rxjs';

@Component({
    selector: 'app-inventarios-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        InventarioListComponent,
        InventarioNewComponent,
        InventarioEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-inventario-list
            class="w-full"
            [inventarios]="inventarios"
            (eventNew)="eventNew($event)"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-inventario-list>

        <!-- Display error message -->
        <div *ngIf="error" class="error-message">
            <p>{{ error }}</p>
        </div>
    `,
})
export class InventarioContainerComponent implements OnInit {
    public error: string = ''; // To store any error message
    public inventarios: Inventario[] = []; // Lista de inventarios

    constructor(
        private _inventarioService: InventarioService, // Servicio para gestionar inventarios
        private _confirmDialogService: ConfirmDialogService, // Servicio de confirmación
        private _matDialog: MatDialog // Diálogo de Material
    ) {}

    ngOnInit(): void {
        this.getInventarios();
    }

    // Obtener todos los inventarios
    getInventarios(): void {
        this._inventarioService.getAll$().pipe(
            take(1),
            catchError((error) => {
                console.error('Error al cargar inventarios:', error);
                this.error = 'No se pudieron cargar los inventarios. Intente nuevamente.';
                return of([]); // Retorna lista vacía en caso de error
            })
        ).subscribe((response) => {
            this.inventarios = response;
        });
    }

    // Abrir modal para crear un nuevo inventario
    public eventNew($event: boolean): void {
        if ($event) {
            const inventarioForm = this._matDialog.open(InventarioNewComponent);
            inventarioForm.componentInstance.title = 'Nuevo Inventario';

            inventarioForm.afterClosed().subscribe((result: Inventario) => {
                if (result) {
                    this._inventarioService.add$(result).pipe(
                        take(1),
                        catchError((error) => {
                            console.error('Error al guardar inventario:', error);
                            this.error = 'No se pudo guardar el inventario. Intente nuevamente.';
                            return of(null);
                        })
                    ).subscribe((response) => {
                        if (response) {
                            this.inventarios.push(response); // Agrega a la lista local
                        }
                    });
                }
            });
        }
    }

    // Abrir modal para editar un inventario
    eventEdit(codigoInventario: number): void {
        const inventarioToEdit = this.inventarios.find(i => i.codigo === codigoInventario);
        if (!inventarioToEdit) {
            this.error = 'Inventario no encontrado para edición.';
            return;
        }

        const inventarioForm = this._matDialog.open(InventarioEditComponent);
        inventarioForm.componentInstance.title = `Editar Inventario ${inventarioToEdit.nombre || inventarioToEdit.codigo}`;
        inventarioForm.componentInstance.inventario = { ...inventarioToEdit }; // Copia para evitar mutación

        inventarioForm.afterClosed().subscribe((result: Inventario) => {
            if (result) {
                this._inventarioService.update$(codigoInventario, result).pipe(
                    take(1),
                    catchError((error) => {
                        console.error('Error al editar inventario:', error);
                        this.error = 'No se pudo editar el inventario. Intente nuevamente.';
                        return of(null);
                    })
                ).subscribe((response) => {
                    if (response) {
                        const index = this.inventarios.findIndex(i => i.codigo === codigoInventario);
                        if (index !== -1) {
                            this.inventarios[index] = response; // Actualiza el elemento localmente
                        }
                    }
                });
            }
        });
    }

    // Eliminar inventario
    public eventDelete(codigoInventario: number) {
        this._confirmDialogService.confirmDelete(
            {
                // title: 'Confirmación de Eliminación',
                // message: `¿Está seguro que quiere eliminar el inventario ${codigoInventario}?`
            }
        ).then(() => {
            this._inventarioService.delete$(codigoInventario).subscribe((response) => {
                if (response) {
                    // Actualizamos la lista local filtrando el elemento eliminado
                    this.inventarios = this.inventarios.filter(inventario => inventario.codigo !== codigoInventario);
                }
            });
        }).catch(() => {
            // Usuario canceló la acción
        });
    }
}
