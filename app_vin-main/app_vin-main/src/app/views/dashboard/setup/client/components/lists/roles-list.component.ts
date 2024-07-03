import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Client } from '../../models/client';

@Component({
    selector: 'app-clients-list',
    imports: [CommonModule, MatButtonModule, MatIconModule],
    standalone: true,
    template: `
        <div class="w-full mx-auto p-6 bg-white rounded overflow-hidden shadow-lg">
            <div class="flex justify-between items-center mb-2 bg-slate-300 text-black p-4 rounded">
                <h2 class="text-2xl font-bold">
                    Lista de <span class="text-primary">Clientes</span>
                </h2>
                <button mat-flat-button color="primary" (click)="goNew()">
                    <mat-icon>add</mat-icon>
                    <span class="ml-2">Nuevo Cliente</span>
                </button>
            </div>
            <div class="bg-white rounded overflow-hidden shadow-lg">
                <div class="p-2 overflow-scroll px-0">
                    <table class="w-full table-fixed">
                        <thead class="bg-primary-600 text-white">
                        <tr>
                            <th class="w-1/6 text-center px-5 border-r">#</th>
                            <th class="w-2/6 text-center px-5 border-r">Nombre</th>
                            <th class="w-2/6 text-center px-5 border-r">Apellido</th>
                            <th class="w-2/6 text-center px-5 border-r">Género</th>
                            <th class="w-2/6 text-center px-5 border-r">Edad</th>
                            <th class="w-2/6 text-center px-5 border-r">Teléfono</th>
                            <th class="w-2/6 text-center px-5 border-r">Correo</th>
                            <th class="w-2/6 text-center px-5 border-r">Tipo Cliente</th>
                            <th class="w-1/6 text-center border-r">Estado</th>
                            <th class="w-2/6 text-center">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr *ngFor="let client of clients; let i = index" class="hover:bg-gray-100">
                            <td class="w-1/6 p-2 text-center border-b">{{ i + 1 }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ client.nombre }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">{{ client.apellido }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">{{ client.genero }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">{{ client.edad }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">{{ client.telefono }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">{{ client.correo }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">{{ client.tipocliente }}</td>
                            <td class="w-1/6 p-2 text-center border-b text-sm">
                                <div class="relative grid items-center font-sans font-bold uppercase whitespace-nowrap select-none bg-green-500/20 text-green-600 py-1 px-2 text-xs rounded-md">
                                    <span>ACTIVO</span>
                                </div>
                            </td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">
                                <div class="flex justify-center space-x-3">
                                    <mat-icon class="text-amber-400 hover:text-amber-500 cursor-pointer" (click)="goEdit(client.id)">edit</mat-icon>
                                    <mat-icon class="text-rose-500 hover:text-rose-600 cursor-pointer" (click)="goDelete(client.id)">delete_sweep</mat-icon>
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
export class ClientListComponent implements OnInit {
    @Input() clients: Client[] = [];
    @Output() eventNew = new EventEmitter<void>();
    @Output() eventEdit = new EventEmitter<number>();
    @Output() eventDelete = new EventEmitter<number>();

    ngOnInit() {}

    public goNew(): void {
        this.eventNew.emit();
    }

    public goEdit(id: number): void {
        this.eventEdit.emit(id);
    }

    public goDelete(id: number): void {
        this.eventDelete.emit(id);
    }
}
