import { Routes } from '@angular/router';
import { TrainerContainerComponent } from './containers/trainer-container.component';
import { TrainerComponent } from './trainer.component';

export default [
    {
        path: '',
        component: TrainerContainerComponent,
        children: [
            {
                path: '',
                component: TrainerComponent,
                data: {
                    title: 'Trainers'
                }
            },
        ],
    },
] as Routes;
