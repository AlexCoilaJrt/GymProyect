import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ConfirmDialogService } from '../../../../../shared/confirm-dialog/confirm-dialog.service';
import { CategoriaService } from '../../../../../providers/services/setup/categoria.service';
import { Categoria } from '../models/categoria';
import { CategoriaListComponent } from '../components/lists/categoria-list.component';
import { CategoriaEditComponent } from '../components/form/categoria-edit.component';

@Component({
    selector: 'app-categoria-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        CategoriaListComponent,
        CategoriaEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-categorias-list
            class="w-full mt-8"
            [categorias]="categorias"
            (eventNew)="eventNewCategoria()"
            (eventEdit)="eventEditCategoria($event)"
            (eventDelete)="eventDeleteCategoria($event)"
        ></app-categorias-list>
    `,
})
export class CategoriaContainerComponent implements OnInit {
    public categorias: Categoria[] = [];
    public error: string = '';

    constructor(
        private _categoriaService: CategoriaService,
        private _confirmDialogService: ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getCategorias();
    }

    getCategorias(): void {
        this._categoriaService.getAll$().subscribe(
            (response) => {
                this.categorias = response;
            },
            (error) => {
                this.error = error;
            }
        );
    }

    public eventNewCategoria(): void {
        const categoriaForm = this._matDialog.open(CategoriaEditComponent);
        categoriaForm.componentInstance.title = 'Nueva Categoría';
        categoriaForm.afterClosed().subscribe((result: any) => {
            if (result) {
                this.saveCategoria(result);
            }
        });
    }

    saveCategoria(data: Categoria): void {
        this._categoriaService.add$(data).subscribe((response) => {
            if (response) {
                this.getCategorias();
            }
        });
    }

    public eventEditCategoria(idCategoria: number): void {
        this._categoriaService.getById$(idCategoria).subscribe((response) => {
            const categoriaForm = this._matDialog.open(CategoriaEditComponent);
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
        this._categoriaService.update$(idCategoria, data).subscribe((response) => {
            if (response) {
                this.getCategorias();
            }
        });
    }

    public eventDeleteCategoria(idCategoria: number): void {
        this._confirmDialogService.confirmDelete({}).then(() => {
            this._categoriaService.delete$(idCategoria).subscribe(() => {
                this.getCategorias();
            });
        }).catch(() => {});
    }
}
