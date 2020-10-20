// - CORE
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HALNode } from '@domain/hal/HALNode.hal';
import { Observable } from 'rxjs';
import { ResponseTransformer } from './support/ResponseTransformer';

@Injectable({
    providedIn: 'root'
})
export class HALResolver {
    constructor(protected httpClient: HttpClient) { }

    public connectResolver(target: any): any {
        if (target instanceof HALNode)
            target.setResolver(this)
        return target
    }
    public resolve(link: string): Observable<any> {
        return this.httpClient.get(link)
    }
}
