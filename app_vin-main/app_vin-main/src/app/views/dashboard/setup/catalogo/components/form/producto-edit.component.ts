import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { Producto } from '../../models/producto';

@Component({
    selector: 'app-producto-edit',
    standalone: true,
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MatDialogModule,
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
    ],
    template: `
        <h1 mat-dialog-title>{{ title }}</h1>
        <div mat-dialog-content>
            <form [formGroup]="productoForm">
                <mat-form-field>
                    <mat-label>Nombre</mat-label>
                    <input matInput formControlName="nombre">
                </mat-form-field>
                <mat-form-field>
                    <mat-label>Categoría ID</mat-label>
                    <input matInput formControlName="categoriaId">
                </mat-form-field>
            </form>
        </div>
        <div mat-dialog-actions>
            <button mat-button (click)="onCancel()">Cancelar</button>
            <button mat-button (click)="onSave()">Guardar</button>
        </div>
    `
})
export class ProductoEditComponent implements OnInit {
    productoForm: FormGroup;
    @Input() title: string;
    @Input() producto: Producto;

    constructor(
        private fb: FormBuilder,
        private dialogRef: MatDialogRef<ProductoEditComponent>
    ) { }

    ngOnInit(): void {
        this.productoForm = this.fb.group({
            nombre: [this.producto?.nombre || '', Validators.required],
            categoriaId: [this.producto?.categoriaId || '', Validators.required]
        });
    }

    onSave(): void {
        if (this.productoForm.valid) {
            this.dialogRef.close(this.productoForm.value);
        }
    }

    onCancel(): void {
        this.dialogRef.close(null);
    }
}
