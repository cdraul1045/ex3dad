import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Category } from '../../models/category';

@Component({
    selector: 'app-category-edit',
    standalone: true,
    imports: [
        FormsModule,
        ReactiveFormsModule,
        MatIconModule,
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
        MatSlideToggleModule
    ],
    template: `
        <div class="flex flex-col max-w-240 md:min-w-160 max-h-screen -m-6">
            <!-- Header -->
            <div class="flex flex-0 items-center justify-between h-16 pr-3 sm:pr-5 pl-6 sm:pl-8 bg-primary text-on-primary">
                <div class="text-lg font-medium" [innerHTML]="title"></div>
                <button mat-icon-button (click)="cancelForm()" [tabIndex]="-1">
                    <mat-icon class="text-current" [svgIcon]="'heroicons_outline:x-mark'"></mat-icon>
                </button>
            </div>

            <!-- Compose form -->
            <form class="flex flex-col flex-auto p-6 sm:p-8 overflow-y-auto" [formGroup]="categoryForm">
                <mat-form-field>
                    <mat-label>Franquicia</mat-label>
                    <input matInput formControlName="franchise" />
                    <mat-error *ngIf="categoryForm.get('franchise')?.hasError('required')">
                        La franquicia es obligatoria.
                    </mat-error>
                </mat-form-field>

                <mat-form-field>
                    <mat-label>Tipo</mat-label>
                    <input matInput formControlName="type" />
                    <mat-error *ngIf="categoryForm.get('type')?.hasError('required')">
                        El tipo es obligatorio.
                    </mat-error>
                </mat-form-field>

                <mat-form-field>
                    <mat-label>Popularidad</mat-label>
                    <input matInput formControlName="popularity" type="number" />
                    <mat-error *ngIf="categoryForm.get('popularity')?.hasError('required')">
                        La popularidad es obligatoria.
                    </mat-error>
                    <mat-error *ngIf="categoryForm.get('popularity')?.hasError('min')">
                        La popularidad no puede ser negativa.
                    </mat-error>
                </mat-form-field>

                <!-- Actions -->
                <div class="flex flex-col sm:flex-row sm:items-center justify-between mt-4 sm:mt-6">
                    <div class="flex space-x-2 items-center mt-4 sm:mt-0 ml-auto">
                        <button mat-stroked-button [color]="'warn'" (click)="cancelForm()">Cancelar</button>
                        <button mat-stroked-button [color]="'primary'" (click)="saveForm()" [disabled]="categoryForm.invalid">Guardar</button>
                    </div>
                </div>
            </form>
        </div>
    `,
})
export class CategoryEditComponent implements OnInit {
    @Input() title: string = '';
    @Input() category: Category = new Category();

    categoryForm: FormGroup;

    constructor(
        private formBuilder: FormBuilder,
        private _matDialog: MatDialogRef<CategoryEditComponent>
    ) {}

    ngOnInit(): void {
        // Initialize the form with controls for franchise, type, and popularity
        this.categoryForm = new FormGroup({
            franchise: new FormControl(this.category.franchise || '', [Validators.required]),
            type: new FormControl(this.category.type || '', [Validators.required]),
            popularity: new FormControl(this.category.popularity || '', [Validators.required, Validators.min(0)]),
        });

        // If category is passed, patch the form with category values
        if (this.category && this.category.id) {
            this.categoryForm.patchValue({
                franchise: this.category.franchise,
                type: this.category.type,
                popularity: this.category.popularity
            });
        }
    }

    public saveForm(): void {
        if (this.categoryForm.valid) {
            this._matDialog.close(this.categoryForm.value); // Close the dialog with form data
        }
    }

    public cancelForm(): void {
        this._matDialog.close(''); // Close the dialog without sending any data
    }
}
