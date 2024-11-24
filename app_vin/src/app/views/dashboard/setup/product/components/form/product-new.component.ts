import { Component, Input, OnInit } from '@angular/core';
import {
    FormControl,
    FormGroup,
    Validators,
    ReactiveFormsModule,
} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
    selector: 'app-product-new',
    standalone: true,
    imports: [
        ReactiveFormsModule,
        MatIconModule,
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
    ],
    template: `
        <div class="flex flex-col max-w-240 md:min-w-160 max-h-screen -m-6">
            <!-- Header -->
            <div class="flex flex-0 items-center justify-between h-16 pr-3 sm:pr-5 pl-6 sm:pl-8 bg-primary text-on-primary">
                <div class="text-lg font-medium">{{ title }}</div>
                <button mat-icon-button (click)="cancelForm()" [tabIndex]="-1">
                    <mat-icon
                        class="text-current"
                        [svgIcon]="'heroicons_outline:x-mark'">
                    </mat-icon>
                </button>
            </div>

            <!-- Form -->
            <form class="flex flex-col flex-auto p-6 sm:p-8 overflow-y-auto" [formGroup]="productForm">
                <mat-form-field>
                    <mat-label>Nombre</mat-label>
                    <input matInput formControlName="nombre" />
                </mat-form-field>
                <mat-form-field>
                    <mat-label>Modelo</mat-label>
                    <input matInput formControlName="modelo" />
                </mat-form-field>
                <mat-form-field>
                    <mat-label>Código</mat-label>
                    <input matInput formControlName="codigo" />
                </mat-form-field>
                <mat-form-field>
                    <mat-label>Precio</mat-label>
                    <input matInput formControlName="precio" />
                </mat-form-field>

                <!-- Optional: Imagen (si usas Uint8Array, se puede adaptar) -->
                <mat-form-field>
                    <mat-label>Imagen (Base64 o URL)</mat-label>
                    <input matInput formControlName="imagen" />
                </mat-form-field>

                <!-- Actions -->
                <div class="flex flex-col sm:flex-row sm:items-center justify-between mt-4 sm:mt-6">
                    <div class="flex space-x-2 items-center mt-4 sm:mt-0 ml-auto">
                        <button mat-stroked-button [color]="'warn'" (click)="cancelForm()">Cancelar</button>
                        <button mat-stroked-button [color]="'primary'" (click)="saveForm()">Guardar</button>
                    </div>
                </div>
            </form>
        </div>
    `,
})
export class ProductNewComponent implements OnInit {
    @Input() title: string = 'Nuevo Producto';

    // Formulario reactivo
    productForm: FormGroup = new FormGroup({
        nombre: new FormControl('', [Validators.required]),
        modelo: new FormControl('', [Validators.required]),
        codigo: new FormControl('', [Validators.required]),
        precio: new FormControl('', [Validators.required, Validators.min(0)]),  // Validación para precio
        imagen: new FormControl(''),  // Imagen opcional
    });

    constructor(private _matDialog: MatDialogRef<ProductNewComponent>) {}

    ngOnInit(): void {}

    // Guardar producto
    public saveForm(): void {
        if (this.productForm.valid) {
            this._matDialog.close(this.productForm.value);
        }
    }

    // Cancelar y cerrar el formulario sin hacer cambios
    public cancelForm(): void {
        this._matDialog.close('');
    }
}
