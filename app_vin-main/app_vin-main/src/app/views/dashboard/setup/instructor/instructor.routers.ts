import { Routes } from '@angular/router';
import { InstructorContainerComponent } from './containers/instructor-container.component';
import { InstructorComponent } from './instructor.component';

export default [
    {
        path: '',
        component: InstructorComponent,
        children: [
            {
                path: '',
                component: InstructorContainerComponent,
                data: {
                    title: 'Instructores'
                }
            }
        ]
    }
] as Routes;
