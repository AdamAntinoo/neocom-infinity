// - CORE
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
// - HTTP PACKAGE
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { IsolationService } from './isolation.service';
// - SERVICES

@Injectable({
    providedIn: 'root'
})
export class HttpClientWrapperService {

    constructor(
        protected http: HttpClient,
        protected isolationService : IsolationService) { }

    // -  H T T P   W R A P P E R S
    /**
     * This method wraps the HTTP access to the backend. It should add any predefined headers, any request specific headers and will also deal with mock data.
     * Mock data comes now into two flavours. The first one will search for the request on the list of defined requests (if the mock is active). If found then it will check if the request should be sent to the file system ot it should be resolved by accessing the LocalStorage.
     * @param  request [description]
     * @return         [description]
     */
    public wrapHttpGETCall(request: string, requestHeaders?: HttpHeaders): Observable<any> {
        console.log("><[HttpClientWrapperService.wrapHttpGETCall]> request: " + request);
        let newheaders = this.wrapHttpSecureHeaders(requestHeaders);
        return this.http.get(request, { headers: newheaders });
    }
    public wrapHttpGETCallNoHeaders(request: string, requestHeaders?: HttpHeaders): Observable<any> {
        console.log("><[HttpClientWrapperService.wrapHttpGETCall]> request: " + request);
        let newheaders = new HttpHeaders()
        return this.http.get(request, { headers: newheaders });
    }
    public wrapHttpPUTCall(request: string, requestHeaders?: HttpHeaders): Observable<any> {
        console.log("><[HttpClientWrapperService.wrapHttpPUTCall]> request: " + request);
        let newheaders = this.wrapHttpSecureHeaders(requestHeaders);
        return this.http.put(request, { headers: newheaders });
    }
    public wrapHttpDELETECall(request: string, requestHeaders?: HttpHeaders): Observable<any> {
        console.log("><[HttpClientWrapperService.wrapHttpDELETECall]> request: " + request);
        let newheaders = this.wrapHttpSecureHeaders(requestHeaders);
        return this.http.delete(request, { headers: newheaders });
    }
    public wrapHttpPATCHCall(request: string, body: string, requestHeaders?: HttpHeaders): Observable<any> {
        console.log("><[HttpClientWrapperService.wrapHttpPATCHCall]> request: " + request);
        let newheaders = this.wrapHttpSecureHeaders(requestHeaders);
        return this.http.patch(request, body, { headers: newheaders });
    }
    public wrapHttpPOSTCall(request: string, body: string, requestHeaders?: HttpHeaders): Observable<any> {
        console.log("><[HttpClientWrapperService.wrapHttpPOSTCall]> request: " + request);
        let newheaders = this.wrapHttpSecureHeaders(requestHeaders);
        return this.http.post(request, body, { headers: newheaders })
    }
    /**
     * Reads a JSON formatted resource. There is no specific convertion to a types class and so can be done on the caller method.
     * @param request the location of the resource file to be read. The resource starts on the /assets/properties location.
     */
    public wrapHttpRESOURCECall(request: string): Observable<any> {
        console.log("><[HttpClientWrapperService.wrapHttpRESOURCECall]> request: " + request);
        return this.http.get(request);
    }

    /**
     * This is the common code to all secure calls. It will check if the call can use the mockup system and if that system has a mockup destionation for the request.
     * This call also should create a new set of headers to be used on the next call and should put inside the current authentication data.
     * This is the default version that does not add any headers. This method should be overrided by application specific implementations.
     *
     * @param [_requestHeaders]
     * @returns the new list of headers.
     */
    protected wrapHttpSecureHeaders(requestHeaders?: HttpHeaders): HttpHeaders {
        if (requestHeaders) return requestHeaders
        else return new HttpHeaders()
    }
}
