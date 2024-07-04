// catalogo.routers.ts
import { Routes } from '@angular/router';
import { CatalogoContainerComponent } from './containers/catalogo-container.component';
import {CategoriaContainerComponent} from "./containers/categoria-container.component";
import {ProductoContainerComponent} from "./containers/producto-container.component";

export default [
    {
        path: '',
        component: CatalogoContainerComponent,
        children: [
            { path: 'productos', component: ProductoContainerComponent },
            { path: 'categorias', component: CategoriaContainerComponent
            }
        ]
    }
] as Routes;
