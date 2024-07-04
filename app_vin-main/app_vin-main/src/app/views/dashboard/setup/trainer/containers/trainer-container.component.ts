import { Trainer } from '../models/trainer';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TrainerNewComponent } from '../components/form/trainer-new.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TrainerEditComponent } from '../components/form/trainer-edit.component';
import { ConfirmDialogService } from '../../../../../shared/confirm-dialog/confirm-dialog.service';
import { TrainerListComponent } from '../components/lists/trainer-list.component';  // Ensure correct path
import { TrainerService } from '../../../../../providers/services/setup/trainer.service';  // Ensure correct path

@Component({
    selector: 'app-trainer-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        TrainerListComponent,
        TrainerNewComponent,
        TrainerEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-trainer-list
            class="w-full"
            [trainers]="trainers"
            (eventNew)="eventNew()"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-trainer-list>
    `,
})
export class TrainerContainerComponent implements OnInit {
    public error: string = '';
    public trainers: Trainer[] = [];
    public trainer: Trainer = {} as Trainer;

    constructor(
        private _trainerService: TrainerService,
        private _confirmDialogService: ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getTrainers();
    }

    getTrainers(): void {
        this._trainerService.getAll$().subscribe(
            (response) => {
                this.trainers = response;
            },
            (error) => {
                this.error = error;
            }
        );
    }

    public eventNew(): void {
        const trainerForm = this._matDialog.open(TrainerNewComponent);
        trainerForm.componentInstance.title = 'Nuevo Trainer';
        trainerForm.afterClosed().subscribe((result: any) => {
            if (result) {
                this.saveTrainer(result);
            }
        });
    }

    saveTrainer(data: Trainer): void {
        this._trainerService.add$(data).subscribe((response) => {
            if (response) {
                this.getTrainers();
            }
        });
    }

    public eventEdit(idTrainer: number): void {
        this._trainerService.getById$(idTrainer).subscribe((response) => {
            this.trainer = response || {} as Trainer;
            const trainerForm = this._matDialog.open(TrainerEditComponent);
            trainerForm.componentInstance.title = `Editar ${this.trainer.nombre}`;
            trainerForm.componentInstance.trainer = this.trainer;
            trainerForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editTrainer(this.trainer.id, result);
                }
            });
        });
    }

    editTrainer(idTrainer: number, data: Trainer): void {
        this._trainerService.update$(idTrainer, data).subscribe((response) => {
            if (response) {
                this.getTrainers();
            }
        });
    }

    public eventDelete(idTrainer: number) {
        this._confirmDialogService.confirmDelete({
            // title: 'Confirmación Personalizada',
            // message: ¿Quieres proceder con esta acción ${}?,
        }).then(() => {
            this._trainerService.delete$(idTrainer).subscribe(() => {
                this.getTrainers();
            });
        }).catch(() => {});
    }
}
