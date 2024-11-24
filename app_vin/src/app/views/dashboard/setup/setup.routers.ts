import { Routes } from '@angular/router';
import {SetupComponent} from "./setup.component";

export default [
    {
        path     : '',
        component: SetupComponent,
        children: [
            {path: 'inventario', loadChildren: () => import('./inventario/inventario.routers')},
            {path: 'pedido', loadChildren: () => import('./pedido/pedido.routers')},
            {path: 'envio', loadChildren: () => import('./envio/envio.routers')},
            {path: 'product', loadChildren: () => import('./product/product.routers')},
            {path: 'category', loadChildren: () => import('./category/category.routers')},
            {path: 'client', loadChildren: () => import('./client/client.routers')},
            {path: 'role', loadChildren: () => import('./roles/roles.routers')},
            {path: 'users', loadChildren: () => import('./user/users-routers')},
            {path: 'user', loadChildren: () => import('./user/users-routers')},
            {path: 'tree', loadChildren: () => import('./tree/tree.routers')},
            {path: 'access', loadChildren: () => import('./access/access.routers')},
        ],
    },
] as Routes;