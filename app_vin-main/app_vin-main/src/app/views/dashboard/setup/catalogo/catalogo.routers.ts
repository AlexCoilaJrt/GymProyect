import { Routes } from '@angular/router';
import { CatalogoComponent } from './catalogo.component';
import { CatalogoContainerComponent } from './containers/catalogo-container.component';

export default [
    {
        path: '',
        component: CatalogoComponent,
        children: [
            {
                path: '',
                component: CatalogoContainerComponent,
                data: { title: 'Catalogo' }
            },
        ],
    },
] as Routes;
