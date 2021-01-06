// - CORE
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
// - HTTP PACKAGE
import { HttpHeaders } from '@angular/common/http';
import { HttpClientWrapperService } from '@innovative/services/httpclientwrapper.service';

@Injectable({
    providedIn: 'root'
})
export class BackendHttpWrapper extends HttpClientWrapperService {
    /**
     * This method implementation adds the common headers required by any call to the neocom springboot backend services.
     * Common headers to be added are the security headers and the application identification.
     *
     * @param [requestHeaders] the optional user added list of headers.
     * @returns the new list of headers.
     */
    protected wrapHttpSecureHeaders(_requestHeaders?: HttpHeaders): HttpHeaders {
        let headers = new HttpHeaders()
            .set('Content-Type', 'application/json; charset=utf-8')
            .set('accept', 'application/json')
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
