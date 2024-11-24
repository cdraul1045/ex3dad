import { Routes } from '@angular/router';
import { InventarioContainerComponent } from "./containers/inventario-container.component";
import { InventarioComponent } from "./inventario.component";

export default [

    {
        path     : '',
        component: InventarioComponent,
        children: [
            {
                path: '',
                component: InventarioContainerComponent,
                data: {
                    title: 'Inventario'
                }
            },
        ],
    },
] as Routes;
