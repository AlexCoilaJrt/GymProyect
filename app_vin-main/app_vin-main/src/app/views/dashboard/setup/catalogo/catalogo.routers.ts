import { Routes } from '@angular/router';
import { ProductoContainerComponent } from './containers/producto-container.component';
import { CategoriaContainerComponent } from './containers/categoria-container.component';

export default [
    {
        path: '',
        children: [
            { path: 'productos', component: ProductoContainerComponent },
            { path: 'categorias', component: CategoriaContainerComponent }
        ]
    }
] as Routes;
