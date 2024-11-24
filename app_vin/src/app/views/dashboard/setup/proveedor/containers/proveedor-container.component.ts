import { Proveedor } from '../models/proveedor';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProveedorNewComponent } from '../components/form/proveedor-new.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProveedorEditComponent } from '../components/form/proveedor-edit.component';
import {ConfirmDialogService} from "../../../../../shared/confirm-dialog/confirm-dialog.service";
import {ClientListComponent} from "../components";
import {ClientService} from "../../../../../providers/services/setup/client.service";

@Component({
    selector: 'app-proveedores-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        ClientListComponent,
        ProveedorNewComponent,
        ProveedorEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-proveedores-list
            class="w-full"
            [proveedores]="proveedores"
            (eventNew)="eventNew($event)"
            (eventEdit)="eventEdit($event)"

            (eventDelete)="eventDelete($event)"
        ></app-proveedores-list>
    `,
})
export class ProveedorContainerComponent implements OnInit {
    public error: string = '';
    public clients: Proveedor[] = [];
    public proveedor = new Proveedor();

    constructor(
        private _proveedorService: ProveedorService,
        private _confirmDialogService:ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getProveedores();
    }

    getClients(): void {
        this._proveedorService.getAll$().subscribe(
            (response) => {
                this.proveedor = response;
            },
            (error) => {
                this.error = error;
            }
        );
    }

    public eventNew($event: boolean): void {
        if ($event) {
            const proveedorForm = this._matDialog.open(ProveedorNewComponent);
            proveedorForm.componentInstance.title = 'Nueva Categoría' || null;
            proveedorForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.saveProveedor(result);
                }
            });
        }
    }

    saveClient(data: Object): void {
        this._proveedorService.add$(data).subscribe((response) => {
        if (response) {
            this.getProveedores()
        }
        });
    }

    eventEdit(idProveedor: number): void {
        const listById = this._proveedorService
            .getById$(idProveedor)
            .subscribe(async (response) => {
                this.proveedor = (response) || {};
                this.openModalEdit(this.proveedor);
                listById.unsubscribe();
            });
    }

    openModalEdit(data: Proveedor) {
        console.log(data);
        if (data) {
            const proveedorForm = this._matDialog.open(ProveedorEditComponent);
            proveedorForm.componentInstance.title =`Editar <b>${data.name||data.id} </b>`;
            proveedorForm.componentInstance.proveedor = data;
            proveedorForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editProveedor( data.id,result);
                }
            });
        }
    }

    editProveedor( idProveedor: number,data: Object) {
        this._ProveedorService.update$(idProveedor,data).subscribe((response) => {
            if (response) {
                this.getProveedores()
            }
        });
    }


    public eventDelete(idProveedores: number) {
        this._confirmDialogService.confirmDelete(
            {
                // title: 'Confirmación Personalizada',
                // message: `¿Quieres proceder con esta acción ${}?`,
            }
        ).then(() => {
            this._proveedorService.delete$(idProveedores).subscribe((response) => {
                this.proveedores = response;
            });
            this.getProveedores();
        }).catch(() => {
        });

    }
}
