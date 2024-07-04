import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ConfirmDialogService } from '../../../../../shared/confirm-dialog/confirm-dialog.service';
import { ProductoService } from '../../../../../providers/services/setup/producto.service';
import { CategoriaService } from '../../../../../providers/services/setup/categoria.service';
import { Producto } from '../models/producto';
import { Categoria } from '../models/categoria';
import { ProductoListComponent } from '../components/list/producto-list.component';
import { CategoriaListComponent } from '../components/list/categoria-list.component';
import { ProductoEditComponent } from '../components/form/producto-edit.component';
import { CategoriaEditComponent } from '../components/form/categoria-edit.component';

@Component({
    selector: 'app-catalogo-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        ProductoListComponent,
        CategoriaListComponent,
        ProductoEditComponent,
        CategoriaEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-productos-list
            class="w-full"
            [productos]="productos"
            (eventNew)="eventNewProducto()"
            (eventEdit)="eventEditProducto($event)"
            (eventDelete)="eventDeleteProducto($event)"
        ></app-productos-list>

        <app-categorias-list
            class="w-full mt-8"
            [categorias]="categorias"
            (eventNew)="eventNewCategoria()"
            (eventEdit)="eventEditCategoria($event)"
            (eventDelete)="eventDeleteCategoria($event)"
        ></app-categorias-list>
    `,
})
export class CatalogoContainerComponent implements OnInit {
    public productos: Producto[] = [];
    public categorias: Categoria[] = [];
    public producto: Producto = {} as Producto;
    public categoria: Categoria = {} as Categoria;

    constructor(
        private _productoService: ProductoService,
        private _categoriaService: CategoriaService,
        private _confirmDialogService: ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getProductos();
        this.getCategorias();
    }

    getProductos(): void {
        this._productoService.getAll$().subscribe(
            (response) => {
                this.productos = response;
            },
            (error) => {
                console.error(error);
            }
        );
    }

    getCategorias(): void {
        this._categoriaService.getAll$().subscribe(
            (response) => {
                this.categorias = response;
            },
            (error) => {
                console.error(error);
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
            this.producto = response;
            const productoForm = this._matDialog.open(ProductoEditComponent);
            productoForm.componentInstance.title = `Editar Producto`;
            productoForm.componentInstance.producto = this.producto;
            productoForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editProducto(this.producto.id, result);
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
        this._confirmDialogService.confirmDelete().then(() => {
            this._productoService.delete$(idProducto).subscribe((response) => {
                if (response) {
                    this.getProductos();
                }
            });
        }).catch(() => {});
    }

    // Métodos similares para Categorias
}
