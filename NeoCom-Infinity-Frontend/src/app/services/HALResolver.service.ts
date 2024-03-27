// - CORE
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HALNode } from '@domain/hal/HALNode.hal';
import { Observable } from 'rxjs';
import { ResponseTransformer } from './support/ResponseTransformer';
import { environment } from '@env/environment';

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
        console.log('>[HALResolver.resolve]>Link: ' + link)
        // Add mandatory headers to access backend
        let newheaders = this.wrapHttpSecureHeaders();
        return this.httpClient.get(link, { headers: newheaders })
    }
    protected wrapHttpSecureHeaders(_requestHeaders?: HttpHeaders): HttpHeaders {
        let headers = new HttpHeaders()
            .set('Content-Type', 'application/json; charset=utf-8')
            .set('xApp-Name', environment.appName)
            .set('xApp-Version', environment.appVersion)
            .set('xApp-Platform', environment.platform)
            .set('xApp-Signature', 'S0000.0016.0001')
            .set('xApp-Signature', 'S0000.0019.0001');
        if (null != _requestHeaders) { // Copy in additional headers.
            for (let key of _requestHeaders.keys()) {
                headers = headers.set(key, _requestHeaders.get(key));
            }
        }
        return headers;
    }
}
