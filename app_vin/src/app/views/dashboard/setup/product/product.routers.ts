import { Routes } from '@angular/router';
import { ProductContainerComponent } from "./containers/product-container.component";
import { ProductComponent } from "./product.component";

export default [
    {
        path: '', // Ruta base
        component: ProductComponent, // Componente padre
        children: [
            {
                path: '', // Ruta vacía para cargar el componente hijo dentro del padre
                component: ProductContainerComponent, // Componente hijo
                data: {
                    title: 'Producto', // Título de la página o metadatos
                }
            },
        ],
    },
] as Routes;
