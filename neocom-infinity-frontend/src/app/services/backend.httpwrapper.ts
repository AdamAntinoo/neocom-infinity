// - CORE
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
// - HTTP PACKAGE
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpClientWrapperService } from '@innovative/services/httpclientwrapper.service';
import {  STORAGE_LINKS } from '@env/PlatformConstants';
import { IsolationService } from '@innovative/services/isolation.service';

@Injectable({
    providedIn: 'root'
})
export class BackendHttpWrapper extends HttpClientWrapperService {
    constructor(
        protected http: HttpClient,
        protected isolationService: IsolationService) {
        super(http, isolationService)
    }
    /**
     * This method implementation adds the common headers required by any call to the neocom springboot backend services.
     * Common headers to be added are the security headers and the application identification.
     *
     * @param [requestHeaders] the optional user added list of headers.
     * @returns the new list of headers.
     */
    protected wrapHttpSecureHeaders(_requestHeaders?: HttpHeaders): HttpHeaders {
        let headers = new HttpHeaders()
        headers = headers.set('Content-Type', 'application/json; charset=utf-8')
        headers = headers.set('accept', 'application/json')
        headers = headers.set('xApp-Name', environment.appName)
        headers = headers.set('xApp-Version', environment.appVersion)
        headers = headers.set('xApp-Platform', environment.platform)
        headers = headers.set('xApp-Signature', 'S0000.0020.0000')
        // Add authentication
        headers = headers.set('Authorization', 'Bearer ' + this.isolationService.getFromSession(STORAGE_LINKS.JWTTOKEN_KEY))

        if (null != _requestHeaders) { // Copy in additional headers.
            for (let key of _requestHeaders.keys()) {
                headers = headers.set(key, _requestHeaders.get(key))
            }
        }
        return headers
    }
}
