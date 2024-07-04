import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ToastrModule} from "ngx-toastr";
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
    selector   : 'app-root',
    templateUrl: './app.component.html',
    styleUrls  : ['./app.component.scss'],
    standalone : true,
    imports    : [RouterOutlet],
})
export class AppComponent implements OnInit{
    clienteForm: FormGroup;
    instructorForm: FormGroup;

    /**
     * Constructor
     */
    constructor(
        public   fb: FormBuilder
    ){

    }
    ngOnInit(): void {
     this.clienteForm = this.fb.group({
          nombre:['',Validators.required],
         apellido:['',Validators.required],
          genero:['',Validators.required],
          edad:['',Validators.required],
          telefono:['',Validators.required],
          correo:['',Validators.required],
          tipocliente:['',Validators.required],
     })
        this.instructorForm = this.fb.group({
            nombre:['',Validators.required],
            especialidad:['',Validators.required],
            edad:['',Validators.required],
            telefono:['',Validators.required],
        })
    }




}
