// - CORE
import { Injectable } from '@angular/core'
import { map } from 'rxjs/operators'
import { HttpClient } from '@angular/common/http'
import { HttpErrorResponse } from '@angular/common/http'
import { HttpHeaders } from '@angular/common/http'
import { HALNode } from '@domain/hal/HALNode.hal'
import { Observable } from 'rxjs'
import { environment } from '@env/environment'
import { HALLink } from '@domain/hal/HALLink.hal'
import { HALLinkArray } from '@domain/hal/HALLinkArray.hal'

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
    /**
     * Resolvs a Link reference. HAL links are URL strings that can be resolved with an HTTP GET. After resolution the returned data is transformed into an instance of the link type T and assigned internally to the HALLink so any next accesses will not use again the HTTP resolution but use the already available value.
     * @param link the link instance to be resolved. If already resolved then the methods exists with the data
     */
    public resolve<T>(link: HALLink<T>): Observable<any> {
        if (link.isResolved()) {
            console.log('>[HALResolver.resolve]>Link already resolved')
            return new Observable<T>((observer) => {
                observer.next(link.target)
                observer.complete()
            })
        } else {
            console.log('>[HALResolver.resolve]>Link: ' + link.getHref())
            // Add mandatory headers to access backend
            let newheaders = this.wrapHttpSecureHeaders()
            return this.httpClient.get(link.getHref(), { headers: newheaders })
                .pipe(map(inputs => {
                    return link.typeCast(inputs)
                }))
        }
    }
    public resolveArray<T>(link: HALLinkArray<T>): Observable<any> {
        if (link.isResolved()) {
            console.log('>[HALResolver.resolveArray]>Link already resolved')
            return new Observable<T[]>((observer) => {
                observer.next(link.target)
                observer.complete()
            })
        } else {
            console.log('>[HALResolver.resolveArray]>Link: ' + link.getHref())
            // Add mandatory headers to access backend
            let newheaders = this.wrapHttpSecureHeaders()
            return this.httpClient.get(link.getHref(), { headers: newheaders })
                .pipe(map(inputs => {
                    return link.typeCast(inputs as any[])
                }))
        }
    }
    protected wrapHttpSecureHeaders(_requestHeaders?: HttpHeaders): HttpHeaders {
        let headers = new HttpHeaders()
        headers = headers.set('Content-Type', 'application/json charset=utf-8')
        headers = headers.set('xApp-Name', environment.appName)
        headers = headers.set('xApp-Version', environment.appVersion)
        headers = headers.set('xApp-Platform', environment.platform)
        headers = headers.set('xApp-Signature', 'S0000.0020.0000')
        if (null != _requestHeaders) { // Copy in additional headers.
            for (let key of _requestHeaders.keys()) {
                headers = headers.set(key, _requestHeaders.get(key))
            }
        }
        return new HttpHeaders()
    }
}
