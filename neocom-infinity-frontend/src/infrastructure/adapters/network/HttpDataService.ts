import { Observable } from "rxjs";
import { TypedRequest } from "../../../neocom-domain/TypedRequest";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class HttpDataService {
    constructor(private httpService: HttpClient) { }

    public httpCall<T>(request: TypedRequest): Observable<T> {
        switch (request.method) {
            case 'GET': {
                console.log('GET->' + request.request)
                return this.httpService.get<T>(request.request, request.options)
            }
        }
    }
}
