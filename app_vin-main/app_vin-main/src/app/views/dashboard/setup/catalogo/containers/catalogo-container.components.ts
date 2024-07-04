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

    constructor(
        private productoService: ProductoService,
        private categoriaService: CategoriaService,
        private confirmDialogService: ConfirmDialogService,
        private matDialog: MatDialog
    ) {}

    ngOnInit() {
        this.getProductos();
        this.getCategorias();
    }

    getProductos(): void {
        this.productoService.getAll$().subscribe(
            (response) => { this.productos = response; },
            (error) => { console.error(error); }
        );
    }

    getCategorias(): void {
        this.categoriaService.getAll$().subscribe(
            (response) => { this.categorias = response; },
            (error) => { console.error(error); }
        );
    }

    eventNewProducto(): void {
        const productoForm = this.matDialog.open(ProductoEditComponent);
        productoForm.componentInstance.title = 'Nuevo Producto';
        productoForm.afterClosed().subscribe((result: any) => {
            if (result) { this.saveProducto(result); }
        });
    }

    saveProducto(data: Producto): void {
        this.productoService.add$(data).subscribe(() => { this.getProductos(); });
    }

    eventEditProducto(idProducto: number): void {
        this.productoService.getById$(idProducto).subscribe((response) => {
            const productoForm = this.matDialog.open(ProductoEditComponent);
            productoForm.componentInstance.title = 'Editar Producto';
            productoForm.componentInstance.producto = response;
            productoForm.afterClosed().subscribe((result: any) => {
                if (result) { this.editProducto(idProducto, result); }
            });
        });
    }

    editProducto(idProducto: number, data: Producto): void {
        this.productoService.update$(idProducto, data).subscribe(() => { this.getProductos(); });
    }

    eventDeleteProducto(idProducto: number): void {
        this.confirmDialogService.confirmDelete().then(() => {
            this.productoService.delete$(idProducto).subscribe(() => { this.getProductos(); });
        }).catch(() => {});
    }

    eventNewCategoria(): void {
        const categoriaForm = this.matDialog.open(CategoriaEditComponent);
        categoriaForm.componentInstance.title = 'Nueva Categoria';
        categoriaForm.afterClosed().subscribe((result: any) => {
            if (result) { this.saveCategoria(result); }
        });
    }

    saveCategoria(data: Categoria): void {
        this.categoriaService.add$(data).subscribe(() => { this.getCategorias(); });
    }

    eventEditCategoria(idCategoria: number): void {
        this.categoriaService.getById$(idCategoria).subscribe((response) => {
            const categoriaForm = this.matDialog.open(CategoriaEditComponent);
            categoriaForm.componentInstance.title = 'Editar Categoria';
            categoriaForm.componentInstance.categoria = response;
            categoriaForm.afterClosed().subscribe((result: any) => {
                if (result) { this.editCategoria(idCategoria, result); }
            });
        });
    }

    editCategoria(idCategoria: number, data: Categoria): void {
        this.categoriaService.update$(idCategoria, data).subscribe(() => { this.getCategorias(); });
    }

    eventDeleteCategoria(idCategoria: number): void {
        this.confirmDialogService.confirmDelete().then(() => {
            this.categoriaService.delete$(idCategoria).subscribe(() => { this.getCategorias(); });
        }).catch(() => {});
    }
}
