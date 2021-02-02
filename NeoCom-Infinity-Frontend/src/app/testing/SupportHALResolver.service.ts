// - CORE
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
// - DOMAIN
import { HALResolver } from '@app/services/HALResolver.service';
import { HALNode } from '@domain/hal/HALNode.hal';
import { HALLink } from '@domain/hal/HALLink.hal';

@Injectable({
    providedIn: 'root'
})
export class SupportHALResolver extends HALResolver {
    constructor(protected httpClient: HttpClient) {
        super(httpClient);
    }
    public connectResolver(target: any): any {
        if (target instanceof HALNode)
            target.setResolver(null)
        return target
    }
    public resolve<T>(link: HALLink<T>): Observable<any> {
        console.log('>[HALResolver.resolve]>Link: ' + link)
        // Add mandatory headers to access backend
        return new Observable((observer) => {
            try {
                let data = this.decodeRequestPath('GET:' + link);
                if (null == data)
                    observer.next('');
                else
                    observer.next(data);
            } catch (error) {
                console.log("><[SupportHALResolver.resolve]> error: " + JSON.stringify(error));
                observer.next('');
            }
            observer.complete();
        })
    }
    protected wrapHttpSecureHeaders(requestHeaders?: HttpHeaders): HttpHeaders {
        let headers = new HttpHeaders()
            .set('Content-Type', 'application/json; charset=utf-8')
        if (null != requestHeaders) { // Copy in additional headers.
            for (let key of requestHeaders.keys()) {
                headers = headers.set(key, requestHeaders.get(key));
            }
        }
        return headers;
    }
    private decodeRequestPath(request: string): any {
        console.log("><[SupportHALResolver.decodeRequestPath]> request: " + request);
        let keyword = '-NOT-FOUND-';
        if (request.includes('GET')) {
            if (request.includes('universe/items'))
                return this.directAccessMockResource('eveitem.32880');
        }
    }
    private directAccessMockResource(dataIdentifier: string): any {
        console.log(">[SupportHALResolver.directAccessMockResource]> dataIdentifier: " + dataIdentifier);
        const MOCK_BASE_LOCATION = '../../../support/backend-simulation/packed-responses/'
        // Paths need to be defined on source. Constants are not valid
        let rawdata = require('../../../support/backend-simulation/packed-responses/' + dataIdentifier + '.json');
        console.log(">[SupportHALResolver.directAccessMockResource]> path: " +
            MOCK_BASE_LOCATION + dataIdentifier + '.json');
        return rawdata
    }
}
