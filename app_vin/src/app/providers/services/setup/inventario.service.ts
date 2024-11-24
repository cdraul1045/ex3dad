import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { END_POINTS, EntityDataService } from '../../utils'; // Asume que END_POINTS tiene la URL de inventario
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class InventarioService extends EntityDataService<any> {
    constructor(protected override httpClient: HttpClient) {
        super(httpClient, END_POINTS.setup.inventario); // Asegúrate de que END_POINTS.setup.inventario esté correctamente configurado
    }
}
