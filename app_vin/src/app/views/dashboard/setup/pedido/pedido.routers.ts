import { Routes } from '@angular/router';
import {CategoryContainerComponent} from "./containers/pedido-container.component";
import {CategoryComponent} from "./pedido.component";

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
