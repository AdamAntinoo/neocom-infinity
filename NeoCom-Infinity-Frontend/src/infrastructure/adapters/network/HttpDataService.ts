import { Observable } from "rxjs";
import { TypedRequest } from "./TypedRequest";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class HttpDataService {
    constructor(private httpService: HttpClient) { }

    protected httpCall<T>(request: TypedRequest): Observable<T> {
        switch (request.method) {
            case 'GET': {
                return this.httpService.get<T>(request.request, request.options)
            }
        }
    }
}
