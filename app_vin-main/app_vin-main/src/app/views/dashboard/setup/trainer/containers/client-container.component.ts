import { Client } from '../models/client';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TrainerNewComponent } from '../components/form/trainer-new.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TrainerEditComponent } from '../components/form/trainer-edit.component';
import {ConfirmDialogService} from "../../../../../shared/confirm-dialog/confirm-dialog.service";
import {ClientListComponent} from "../components";
import {ClientService} from "../../../../../providers/services/setup/client.service";

@Component({
    selector: 'app-clients-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        ClientListComponent,
        TrainerNewComponent,
        TrainerEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-clients-list
            class="w-full"
            [clients]="clients"
            (eventNew)="eventNew($event)"
            (eventEdit)="eventEdit($event)"

            (eventDelete)="eventDelete($event)"
        ></app-clients-list>
    `,
})
export class ClientContainerComponent implements OnInit {
    public error: string = '';
    public clients: Client[] = [];
    public client = new Client();

    constructor(
        private _clientService: ClientService,
        private _confirmDialogService:ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getClients();
    }

    getClients(): void {
        this._clientService.getAll$().subscribe(
            (response) => {
                this.clients = response;
            },
            (error) => {
                this.error = error;
            }
        );
    }

    public eventNew($event: boolean): void {
        if ($event) {
            const clienteForm = this._matDialog.open(TrainerNewComponent);
            clienteForm.componentInstance.title = 'Nuevo Client' || null;
            clienteForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.saveClient(result);
                }
            });
        }
    }

    saveClient(data: Object): void {
        this._clientService.add$(data).subscribe((response) => {
        if (response) {
            this.getClients()
        }
        });
    }

    eventEdit(idClient: number): void {
        const listById = this._clientService
            .getById$(idClient)
            .subscribe(async (response) => {
                this.client = (response) || {};
                this.openModalEdit(this.client);
                listById.unsubscribe();
            });
    }

    openModalEdit(data: Client) {
        console.log(data);
        if (data) {
            const clienteForm = this._matDialog.open(TrainerEditComponent);
            clienteForm.componentInstance.title =`Editar <b>${data.nombre||data.id} </b>`;
            clienteForm.componentInstance.client = data;
            clienteForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editClient( data.id,result);
                }
            });
        }
    }

    editClient( idClient: number,data: Object) {
        this._clientService.update$(idClient,data).subscribe((response) => {
            if (response) {
                this.getClients()
            }
        });
    }


    public eventDelete(idClient: number) {
        this._confirmDialogService.confirmDelete(
            {
                // title: 'Confirmación Personalizada',
                // message: `¿Quieres proceder con esta acción ${}?`,
            }
        ).then(() => {
            this._clientService.delete$(idClient).subscribe((response) => {
                this.clients = response;
            });
            this.getClients();
        }).catch(() => {
        });

    }
}
