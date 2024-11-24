import { Routes } from '@angular/router';
import { CategoryContainerComponent } from "./containers/category-container.component";
import { CategoryComponent } from "./category.component";

export default [
    {
        path: '', // This will be the base path
        component: CategoryComponent, // Parent component
        children: [
            {
                path: '', // The empty path means the child route is loaded within the parent
                component: CategoryContainerComponent, // The child component
                data: {
                    title: 'Categoría', // Page title or other metadata
                }
            },
        ],
    },
] as Routes;
