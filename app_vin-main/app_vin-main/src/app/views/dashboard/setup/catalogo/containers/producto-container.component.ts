import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ConfirmDialogService } from '../../../../../shared/confirm-dialog/confirm-dialog.service';
import { ProductoService } from '../../../../../providers/services/setup/producto.service';
import { Producto } from '../models/producto';
import { ProductoListComponent } from '../components/lists/producto-list.component';
import { ProductoEditComponent } from '../components/form/producto-edit.component';
import { ProductoNewComponent } from '../components/form/producto-new.component';

@Component({
    selector: 'app-producto-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        ProductoListComponent,
        ProductoEditComponent,
        ProductoNewComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-productos-list
            class="w-full"
            [productos]="productos"
            (eventNew)="eventNew()"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-productos-list>
    `,
})
export class ProductoContainerComponent implements OnInit {
    public productos: Producto[] = [];
    public producto: Producto;
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
            (response: Producto[]) => {
                this.productos = response;
            },
            (error: any) => {
                this.error = error;
            }
        );
    }

    public eventNew(): void {
        const productoForm = this._matDialog.open(ProductoNewComponent);
        productoForm.componentInstance.title = 'Nuevo Producto';
        productoForm.afterClosed().subscribe((result: any) => {
            if (result) {
                this.saveProducto(result);
            }
        });
    }

    saveProducto(data: Producto): void {
        this._productoService.add$(data).subscribe((response: Producto) => {
            if (response) {
                this.getProductos();
            }
        });
    }

    public eventEdit(idProducto: number): void {
        this._productoService.getById$(idProducto).subscribe((response: Producto) => {
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
        this._productoService.update$(idProducto, data).subscribe((response: Producto) => {
            if (response) {
                this.getProductos();
            }
        });
    }

    public eventDelete(idProducto: number): void {
        this._confirmDialogService.confirmDelete().then(() => {
            this._productoService.delete$(idProducto).subscribe(() => {
                this.getProductos();
            });
        }).catch(() => {});
    }
}
