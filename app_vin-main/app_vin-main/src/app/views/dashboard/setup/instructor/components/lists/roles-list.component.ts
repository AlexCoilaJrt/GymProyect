import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Instructor } from '../../models/instructor';

@Component({
    selector: 'app-instructors-list',
    imports: [CommonModule, MatButtonModule, MatIconModule],
    standalone: true,
    template: `
        <div class="w-full mx-auto p-6 bg-white rounded overflow-hidden shadow-lg">
            <div class="flex justify-between items-center mb-2 bg-slate-300 text-black p-4 rounded">
                <h2 class="text-2xl font-bold">
                    Lista de <span class="text-gray">Instructores</span>
                </h2>
                <button mat-flat-button class="btn-gradient-plomo" (click)="goNew()">
                <mat-icon>add</mat-icon>
                    <span class="ml-2">Nuevo Instructor</span>
                </button>
            </div>
            <div class="bg-white rounded overflow-hidden shadow-lg">
                <div class="p-2 overflow-scroll px-0">
                    <table class="w-full table-fixed">
                        <thead class="bg-gray-600 text-white">
                        <tr>
                            <th class="w-1/6 text-center px-5 border-r">#</th>
                            <th class="w-2/6 text-center px-5 border-r">Nombre</th>
                            <th class="w-2/6 text-center px-5 border-r">Especialidad</th>
                            <th class="w-2/6 text-center px-5 border-r">Edad</th>
                            <th class="w-2/6 text-center px-5 border-r">Teléfono</th>
                            <th class="w-1/6 text-center border-r">Estado</th>
                            <th class="w-2/6 text-center">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr *ngFor="let instructor of instructors; let i = index" class="hover:bg-gray-100">
                            <td class="w-1/6 p-2 text-center border-b">{{ i + 1 }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ instructor.nombre }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">{{ instructor.especialidad }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">{{ instructor.edad }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">{{ instructor.telefono }}</td>
                            <td class="w-1/6 p-2 text-center border-b text-sm">
                                <div class="relative grid items-center font-sans font-bold uppercase whitespace-nowrap select-none bg-green-500/20 text-green-600 py-1 px-2 text-xs rounded-md">
                                    <span>ACTIVO</span>
                                </div>
                            </td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">
                                <div class="flex justify-center space-x-3">
                                    <mat-icon class="text-amber-400 hover:text-amber-500 cursor-pointer" (click)="goEdit(instructor.id)">edit</mat-icon>
                                    <mat-icon class="text-rose-500 hover:text-rose-600 cursor-pointer" (click)="goDelete(instructor.id)">delete_sweep</mat-icon>
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
export class InstructorListComponent implements OnInit {
    @Input() instructors: Instructor[] = [];
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
