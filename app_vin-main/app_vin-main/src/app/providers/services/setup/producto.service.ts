import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { END_POINTS, EntityDataService, IResponse } from '../../utils';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({ providedIn: 'root' })
export class ProductoService extends EntityDataService<Producto> {
    constructor(protected override httpClient: HttpClient) {
        super(httpClient, END_POINTS.setup.producto); // Verifica que este endpoint existe
    }

    getAll$(): Observable<Producto[]> {
        return this.httpClient.get<Producto[]>(END_POINTS.setup.producto);
    }

    getById$(id: number): Observable<Producto> {
        return this.httpClient.get<Producto>(`${END_POINTS.setup.producto}/${id}`);
    }

    add$(data: Producto): Observable<Producto> {
        return this.httpClient.post<Producto>(END_POINTS.setup.producto, data);
    }

    update$(id: number, data: Producto): Observable<Producto> {
        return this.httpClient.put<Producto>(`${END_POINTS.setup.producto}/${id}`, data);
    }

    delete$(id: number): Observable<Producto> {
        return this.httpClient.delete<Producto>(`${END_POINTS.setup.producto}/${id}`);
    }
}
