import { Instructor } from '../models/instructor';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { InstructorNewComponent } from '../components/form/instructor-new.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InstructorEditComponent } from '../components/form/instructor-edit.component';
import { ConfirmDialogService } from '../../../../../shared/confirm-dialog/confirm-dialog.service';
import { InstructorListComponent } from '../components';
import { InstructorService } from '../../../../../providers/services/setup/instructor.service';

@Component({
    selector: 'app-instructors-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        InstructorListComponent,
        InstructorNewComponent,
        InstructorEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-instructors-list
            class="w-full"
            [instructors]="instructors"
            (eventNew)="eventNew()"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-instructors-list>
    `,
})
export class InstructorContainerComponent implements OnInit {
    public error: string = '';
    public instructors: Instructor[] = [];
    public instructor: Instructor = {} as Instructor;

    constructor(
        private _instructorService: InstructorService,
        private _confirmDialogService: ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getInstructors();
    }

    getInstructors(): void {
        this._instructorService.getAll$().subscribe(
            (response) => {
                this.instructors = response;
            },
            (error) => {
                this.error = error;
            }
        );
    }

    public eventNew(): void {
        const instructorForm = this._matDialog.open(InstructorNewComponent);
        instructorForm.componentInstance.title = 'Nuevo Instructor';
        instructorForm.afterClosed().subscribe((result: any) => {
            if (result) {
                this.saveInstructor(result);
            }
        });
    }

    saveInstructor(data: Instructor): void {
        this._instructorService.add$(data).subscribe((response) => {
            if (response) {
                this.getInstructors();
            }
        });
    }

    public eventEdit(idInstructor: number): void {
        this._instructorService.getById$(idInstructor).subscribe((response) => {
            this.instructor = response || {} as Instructor;
            const instructorForm = this._matDialog.open(InstructorEditComponent);
            instructorForm.componentInstance.title = `Editar ${this.instructor.nombre}`;
            instructorForm.componentInstance.instructor = this.instructor;
            instructorForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editInstructor(this.instructor.id, result);
                }
            });
        });
    }
    

    editInstructor(idInstructor: number, data: Instructor): void {
        this._instructorService.update$(idInstructor, data).subscribe((response) => {
            if (response) {
                this.getInstructors();
            }
        });
    }

    public eventDelete(idInstructor: number) {
        this._confirmDialogService.confirmDelete({
            // title: 'Confirmación Personalizada',
            // message: ¿Quieres proceder con esta acción ${}?,
        }).then(() => {
            this._instructorService.delete$(idInstructor).subscribe((response) => {
                this.instructors = response;
            });
            this.getInstructors();
        }).catch(() => {});
    }
}
