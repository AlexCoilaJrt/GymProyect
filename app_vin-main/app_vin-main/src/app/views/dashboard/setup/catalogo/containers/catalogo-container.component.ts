import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ConfirmDialogService } from '../../../../../shared/confirm-dialog/confirm-dialog.service';
import { ProductoService } from '../../../../../providers/services/setup/producto.service';
import { CategoriaService } from '../../../../../providers/services/setup/categoria.service';
import { Producto } from '../models/producto';
import { Categoria } from '../models/categoria';
import { ProductoListComponent } from '../components/lists/producto-list.component';
import { CategoriaListComponent } from '../components/lists/categoria-list.component';
import { ProductoEditComponent } from '../components/form/producto-edit.component';
import { ProductoNewComponent } from '../components/form/producto-new.component';
import {CategoriaEditComponent} from "../components/form/categoria-edit.component";

@Component({
    selector: 'app-catalogo-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        ProductoListComponent,
        CategoriaListComponent,
        ProductoEditComponent,
        ProductoNewComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-producto-list
            class="w-full"
            [productos]="productos"
            (eventNew)="eventNewProducto()"
            (eventEdit)="eventEditProducto($event)"
            (eventDelete)="eventDeleteProducto($event)"
        ></app-producto-list>
        <app-categoria-list
            class="w-full mt-8"
            [categorias]="categorias"
            (eventNew)="eventNewCategoria()"
            (eventEdit)="eventEditCategoria($event)"
            (eventDelete)="eventDeleteCategoria($event)"
        ></app-categoria-list>
    `,
})
export class CatalogoContainerComponent implements OnInit {
    public productos: Producto[] = [];
    public categorias: Categoria[] = [];
    public producto: Producto = {} as Producto;
    public categoria: Categoria = {} as Categoria;

    constructor(
        private productoService: ProductoService,
        private categoriaService: CategoriaService,
        private confirmDialogService: ConfirmDialogService,
        private matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getProductos();
        this.getCategorias();
    }

    getProductos(): void {
        this.productoService.getAll$().subscribe(
            (response: Producto[]) => {
                this.productos = response;
            },
            (error: any) => {
                console.error(error);
            }
        );
    }

    getCategorias(): void {
        this.categoriaService.getAll$().subscribe(
            (response: Categoria[]) => {
                this.categorias = response;
            },
            (error: any) => {
                console.error(error);
            }
        );
    }

    eventNewProducto(): void {
        const productoForm = this.matDialog.open(ProductoNewComponent);
        productoForm.componentInstance.title = 'Nuevo Producto';
        productoForm.afterClosed().subscribe((result: any) => {
            if (result) {
                this.saveProducto(result);
            }
        });
    }

    saveProducto(data: Producto): void {
        this.productoService.add$(data).subscribe((response: Producto) => {
            if (response) {
                this.getProductos();
            }
        });
    }

    eventEditProducto(idProducto: number): void {
        this.productoService.getById$(idProducto).subscribe((response: Producto) => {
            const productoForm = this.matDialog.open(ProductoEditComponent);
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
        this.productoService.update$(idProducto, data).subscribe((response: Producto) => {
            if (response) {
                this.getProductos();
            }
        });
    }

    eventDeleteProducto(idProducto: number): void {
        this.confirmDialogService.confirmDelete().then(() => {
            this.productoService.delete$(idProducto).subscribe(() => {
                this.getProductos();
            });
        }).catch(() => {});
    }

    eventNewCategoria(): void {
        const categoriaForm = this.matDialog.open(CategoriaEditComponent);
        categoriaForm.componentInstance.title = 'Nueva Categoría';
        categoriaForm.afterClosed().subscribe((result: any) => {
            if (result) {
                this.saveCategoria(result);
            }
        });
    }

    saveCategoria(data: Categoria): void {
        this.categoriaService.add$(data).subscribe((response: Categoria) => {
            if (response) {
                this.getCategorias();
            }
        });
    }

    eventEditCategoria(idCategoria: number): void {
        this.categoriaService.getById$(idCategoria).subscribe((response: Categoria) => {
            const categoriaForm = this.matDialog.open(CategoriaEditComponent);
            categoriaForm.componentInstance.title = `Editar Categoría`;
            categoriaForm.componentInstance.categoria = response;
            categoriaForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editCategoria(idCategoria, result);
                }
            });
        });
    }

    editCategoria(idCategoria: number, data: Categoria): void {
        this.categoriaService.update$(idCategoria, data).subscribe((response: Categoria) => {
            if (response) {
                this.getCategorias();
            }
        });
    }

    eventDeleteCategoria(idCategoria: number): void {
        this.confirmDialogService.confirmDelete().then(() => {
            this.categoriaService.delete$(idCategoria).subscribe(() => {
                this.getCategorias();
            });
        }).catch(() => {});
    }
}
