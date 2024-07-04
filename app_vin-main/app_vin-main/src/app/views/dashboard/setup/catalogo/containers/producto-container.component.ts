import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProductoService } from '../../../../../providers/services/setup/producto.service';
import { Producto } from '../models/producto';
import { ProductoListComponent } from '../components/lists/producto-list.component';
import { ProductoEditComponent } from '../components/form/producto-edit.component';
import { ConfirmDialogService } from '../../../../../shared/confirm-dialog/confirm-dialog.service';

@Component({
    selector: 'app-producto-container',
    standalone: true,
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ProductoListComponent,
        ProductoEditComponent,
    ],
    template: `
        <app-producto-list
            [productos]="productos"
            (eventNew)="eventNewProducto()"
            (eventEdit)="eventEditProducto($event)"
            (eventDelete)="eventDeleteProducto($event)"
        ></app-producto-list>
    `,
})
export class ProductoContainerComponent implements OnInit {
    public productos: Producto[] = [];
    public error: string = '';

    constructor(
        private _productoService: ProductoService,
        private _confirmDialogService: ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getProductos();
    }

    getProductos(): void {
        this._productoService.getAll$().subscribe(
            (response) => {
                this.productos = response;
            },
            (error) => {
                this.error = error;
            }
        );
    }

    eventNewProducto(): void {
        const productoForm = this._matDialog.open(ProductoEditComponent);
        productoForm.componentInstance.title = 'Nuevo Producto';
        productoForm.afterClosed().subscribe((result: any) => {
            if (result) {
                this.saveProducto(result);
            }
        });
    }

    saveProducto(data: Producto): void {
        this._productoService.add$(data).subscribe((response) => {
            if (response) {
                this.getProductos();
            }
        });
    }

    eventEditProducto(idProducto: number): void {
        this._productoService.getById$(idProducto).subscribe((response) => {
            const productoForm = this._matDialog.open(ProductoEditComponent);
            productoForm.componentInstance.title = `Editar Producto`;
            productoForm.componentInstance.producto = response;
            productoForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editProducto(idProducto, result);
                }
            });
        });
    }

    editProducto(idProducto: number, data: Producto): void {
        this._productoService.update$(idProducto, data).subscribe((response) => {
            if (response) {
                this.getProductos();
            }
        });
    }

    eventDeleteProducto(idProducto: number): void {
        this._confirmDialogService.confirmDelete({}).then(() => {
            this._productoService.delete$(idProducto).subscribe(() => {
                this.getProductos();
            });
        }).catch(() => {});
    }
}
