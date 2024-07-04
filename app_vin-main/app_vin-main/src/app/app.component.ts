import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';  // If you need Toastr, ensure it is properly imported and configured in your app.module

@Component({
    selector   : 'app-root',
    templateUrl: './app.component.html',
    styleUrls  : ['./app.component.scss'],
    standalone : true,
    imports    : [RouterOutlet, ToastrModule],  // Ensure ToastrModule is added if you are using it
})
export class AppComponent implements OnInit{
    clienteForm: FormGroup;
    instructorForm: FormGroup;
    categoriaForm: FormGroup;
    productoForm: FormGroup;

    /**
     * Constructor
     */
    constructor(
        public fb: FormBuilder
    ){}

    ngOnInit(): void {
        this.clienteForm = this.fb.group({
            nombre: ['', Validators.required],
            apellido: ['', Validators.required],
            genero: ['', Validators.required],
            edad: ['', Validators.required],
            telefono: ['', Validators.required],
            correo: ['', Validators.required],
            tipocliente: ['', Validators.required],
        });

        this.instructorForm = this.fb.group({
            nombre: ['', Validators.required],
            especialidad: ['', Validators.required],
            edad: ['', Validators.required],
            telefono: ['', Validators.required],
        });

        this.categoriaForm = this.fb.group({
            nombre: ['', Validators.required],
        });

        this.productoForm = this.fb.group({
            nombre: ['', Validators.required],
            categoriaId: ['', Validators.required],  // Assuming you store the categoria ID as a string; adjust as necessary
        });
    }
}
