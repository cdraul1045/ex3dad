import { Routes } from '@angular/router';
import {CategoryContainerComponent} from "./containers/envio-container.component";
import {CategoryComponent} from "./envio.component";

export default [

  {
    path     : '',
    component: CategoryComponent,
    children: [
      {
        path: '',
        component: CategoryContainerComponent,
        data: {
          title: 'Categoría'
        }
      },
    ],
  },
] as Routes;
