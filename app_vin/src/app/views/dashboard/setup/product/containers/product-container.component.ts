import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProductService } from "../../../../../providers/services/setup/product.service";
import { ConfirmDialogService } from "../../../../../shared/confirm-dialog/confirm-dialog.service";
import { ProductListComponent } from "../components";
import { ProductNewComponent } from '../components/form/product-new.component';
import { ProductEditComponent } from '../components/form/product-edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Product } from '../models/product';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { catchError, of, take } from 'rxjs';

@Component({
    selector: 'app-products-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        ProductListComponent,
        ProductNewComponent,
        ProductEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-products-list
            class="w-full"
            [products]="products"
            (eventNew)="eventNew($event)"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-products-list>

        <!-- Display error message -->
        <div *ngIf="error" class="error-message">
            <p>{{ error }}</p>
        </div>
    `,
})
export class ProductContainerComponent implements OnInit {
    public error: string = ''; // To store any error message
    public products: Product[] = [];
    public product: Product = new Product();

    constructor(
        private _productService: ProductService,
        private _confirmDialogService: ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit(): void {
        this.getProducts();
    }

    // Obtener todos los productos
    getProducts(): void {
        this._productService.getAll$().pipe(
            take(1),
            catchError((error) => {
                this.error = 'Failed to load products. Please try again later.';
                console.error(error); // Log the error for debugging purposes
                return of([]); // Return an empty array to prevent breaking the UI
            })
        ).subscribe((response) => {
            this.products = response;
        });
    }

    // Abrir modal para crear un nuevo producto
    public eventNew($event: boolean): void {
        if ($event) {
            const productForm = this._matDialog.open(ProductNewComponent);
            productForm.componentInstance.title = 'Nuevo Producto';
            productForm.afterClosed().subscribe((result: Product) => {
                if (result) {
                    this.saveProduct(result);
                }
            });
        }
    }

    // Guardar un nuevo producto
    saveProduct(data: Product): void {
        this._productService.add$(data).pipe(
            take(1),
            catchError((error) => {
                this.error = 'Failed to save product. Please try again later.';
                console.error(error);
                return of(null); // Prevent breaking the UI
            })
        ).subscribe((response) => {
            if (response) {
                this.getProducts();
            }
        });
    }

    // Abrir modal para editar un producto
    eventEdit(idProduct: number): void {
        this._productService.getById$(idProduct).pipe(
            take(1),
            catchError((error) => {
                this.error = 'Failed to load product for editing.';
                console.error(error);
                return of(null);
            })
        ).subscribe((response) => {
            if (response) {
                this.product = response;
                this.openModalEdit(this.product);
            }
        });
    }

    // Abrir el modal de edición
    openModalEdit(data: Product): void {
        if (data) {
            const productForm = this._matDialog.open(ProductEditComponent);
            productForm.componentInstance.title = `Editar <b>${data.nombre || data.id}</b>`;
            productForm.componentInstance.product = { ...data };
            productForm.afterClosed().subscribe((result: Product) => {
                if (result) {
                    this.editProduct(data.id, result);
                }
            });
        }
    }

    // Editar producto
    editProduct(idProduct: number, data: Product): void {
        this._productService.update$(idProduct, data).pipe(
            take(1),
            catchError((error) => {
                this.error = 'Failed to edit product. Please try again later.';
                console.error(error);
                return of(null); // Prevent breaking the UI
            })
        ).subscribe((response) => {
            if (response) {
                this.getProducts();
            }
        });
    }

    // Eliminar producto
    public eventDelete(idProduct: number): void {
        this._confirmDialogService.confirmDelete({
            message: '¿Seguro que deseas eliminar este producto?',
        }).then(() => {
            this._productService.delete$(idProduct).pipe(
                take(1),
                catchError((error) => {
                    this.error = 'Failed to delete product. Please try again later.';
                    console.error(error);
                    return of(null); // Prevent breaking the UI
                })
            ).subscribe((response) => {
                if (response) {
                    this.getProducts();
                }
            });
        }).catch(() => {
            // Manejo de cancelación de la eliminación
        });
    }
}
