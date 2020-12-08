// - CORE
import { Injectable } from '@angular/core';
// - HTTP PACKAGE
import { HttpHeaders } from '@angular/common/http';
import { HttpClientWrapperService } from '@innovative/services/httpclientwrapper.service';

@Injectable({
    providedIn: 'root'
})
export class ESIUniverseHttpWrapper extends HttpClientWrapperService {
    /**
     * This method implementation adds the common headers required by any call to the eve esi backend servers.
     * Common tbs to be received and added to the list are the 'etag' used to cache requests to the backend servers.
     *
     * @param [_requestHeaders]
     * @returns the new list of headers.
     */
    protected wrapHttpSecureHeaders(requestHeaders?: HttpHeaders): HttpHeaders {
        let headers = super.wrapHttpSecureHeaders(requestHeaders)
            .set('accept', 'application/json')
            .set('Accept-Language', 'en-us')
            .set('content-type', 'application/json; charset=UTF-8');
        if (null != requestHeaders) { // Copy in additional headers.
            for (let key of requestHeaders.keys()) {
                const headerValue: any = requestHeaders.get(key)
                if (null != headerValue) headers = headers.set(key, headerValue);
            }
        }
        return headers;
    }
}
