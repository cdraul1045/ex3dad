import { Routes } from '@angular/router';
import {ProveedorContainerComponent} from "./containers/proveedor-container.component";
import {ProveedorComponent} from "./proveedor.component";

export default [

  {
    path     : '',
    component: ProveedorComponent,
    children: [
      {
        path: '',
        component: ProveedorContainerComponent,
        data: {
          title: 'Proveedores'
        }
      },
    ],
  },
] as Routes;
