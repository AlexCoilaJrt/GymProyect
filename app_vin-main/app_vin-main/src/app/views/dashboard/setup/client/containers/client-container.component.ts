import { Client } from '../models/client';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ClientNewComponent } from '../components/form/client-new.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClientEditComponent } from '../components/form/client-edit.component';
import { ConfirmDialogService } from '../../../../../shared/confirm-dialog/confirm-dialog.service';
import { ClientListComponent } from '../components';
import { ClientService } from '../../../../../providers/services/setup/client.service';

@Component({
    selector: 'app-clients-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        ClientListComponent,
        ClientNewComponent,
        ClientEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-clients-list
            class="w-full"
            [clients]="clients"
            (eventNew)="eventNew()"
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
        private _confirmDialogService: ConfirmDialogService,
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

    public eventNew(): void {
        const clienteForm = this._matDialog.open(ClientNewComponent);
        clienteForm.componentInstance.title = 'Nuevo Client';
        clienteForm.afterClosed().subscribe((result: any) => {
            if (result) {
                this.saveClient(result);
            }
        });
    }

    saveClient(data: Object): void {
        this._clientService.add$(data).subscribe((response) => {
            if (response) {
                this.getClients();
            }
        });
    }

    eventEdit(idClient: number): void {
        this._clientService.getById$(idClient).subscribe((response) => {
            this.client = response || {};
            const clienteForm = this._matDialog.open(ClientEditComponent);
            clienteForm.componentInstance.title = `Editar ${this.client.nombre || this.client.id}`;
            clienteForm.componentInstance.client = this.client;
            clienteForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editClient(this.client.id, result);
                }
            });
        });
    }

    editClient(idClient: number, data: Object): void {
        this._clientService.update$(idClient, data).subscribe((response) => {
            if (response) {
                this.getClients();
            }
        });
    }

    public eventDelete(idClient: number): void {
        this._confirmDialogService.confirmDelete({}).then(() => {
            this._clientService.delete$(idClient).subscribe(() => {
                this.getClients();
            });
        }).catch(() => {});
    }
}
