import { Routes } from '@angular/router';
import { TrainerContainerComponent } from './containers/trainer-container.component';

export default [
    {
        path: '',
        component: TrainerContainerComponent,
        children: [
            {
                path: '',
                component: TrainerContainerComponent,
                data: {
                    title: 'Trainers'
                }
            },
        ],
    },
] as Routes;
