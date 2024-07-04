import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Categoria } from '../../models/categoria';

@Component({
    selector: 'app-categoria-list',
    imports: [CommonModule, MatButtonModule, MatIconModule],
    standalone: true,
    template: `
        <div class="w-full mx-auto p-6 bg-white rounded overflow-hidden shadow-lg">
            <div class="flex justify-between items-center mb-2 bg-slate-300 text-black p-4 rounded">
                <h2 class="text-2xl font-bold">
                    Lista de <span class="text-primary">Categorías</span>
                </h2>
                <button mat-flat-button color="primary" (click)="goNew()">
                    <mat-icon>add</mat-icon>
                    <span class="ml-2">Nueva Categoría</span>
                </button>
            </div>
            <div class="bg-white rounded overflow-hidden shadow-lg">
                <div class="p-2 overflow-scroll px-0">
                    <table class="w-full table-fixed">
                        <thead class="bg-primary-600 text-white">
                        <tr>
                            <th class="w-1/6 text-center px-5 border-r">#</th>
                            <th class="w-2/6 text-center px-5 border-r">Nombre</th>
                            <th class="w-2/6 text-center">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr *ngFor="let categoria of categorias; let i = index" class="hover:bg-gray-100">
                            <td class="w-1/6 p-2 text-center border-b">{{ i + 1 }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ categoria.nombre }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">
                                <div class="flex justify-center space-x-3">
                                    <mat-icon class="text-amber-400 hover:text-amber-500 cursor-pointer" (click)="goEdit(categoria.id)">edit</mat-icon>
                                    <mat-icon class="text-rose-500 hover:text-rose-600 cursor-pointer" (click)="goDelete(categoria.id)">delete_sweep</mat-icon>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    `,
})
export class CategoriaListComponent {
    @Input() categorias: Categoria[] = [];
    @Output() eventNew = new EventEmitter<void>();
    @Output() eventEdit = new EventEmitter<number>();
    @Output() eventDelete = new EventEmitter<number>();

    goNew(): void {
        this.eventNew.emit();
    }

    goEdit(id: number): void {
        this.eventEdit.emit(id);
    }

    goDelete(id: number): void {
        this.eventDelete.emit(id);
    }
}
