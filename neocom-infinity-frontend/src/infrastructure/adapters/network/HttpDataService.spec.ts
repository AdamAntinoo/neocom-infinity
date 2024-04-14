import { NO_ERRORS_SCHEMA } from '@angular/core'
import { Observable } from 'rxjs'
import { HttpClient } from "@angular/common/http"
import { HttpClientTestingModule } from "@angular/common/http/testing"
import { TestBed } from "@angular/core/testing"

import { HttpDataService } from "./HttpDataService"
import { TypedRequest } from '../../../neocom-domain/TypedRequest'

class TestTypedRequest implements TypedRequest {
    method: string = 'GET'
    request: string
    options: object

    prepare(parameters: object): TypedRequest {
        this.request = '/request/' + parameters['param']
        return this
    }

}

describe('SERVICE HttpDataService [Module: Infra]', () => {
    let service: HttpDataService
    let getRequest: TestTypedRequest = new TestTypedRequest().prepare({ param: 123 })

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
                HttpClientTestingModule
            ],
            providers: [
                {
                    provide: HttpClient, useValue: {
                        get: (request: string, headers?: object) => {
                            console.log('method->' + 'GET')
                            console.log('request->' + request)
                            console.log('options->' + JSON.stringify(headers))
                            return Observable.create((observer) => {
                                observer.next({});
                                observer.complete();
                            });
                        },
                        delete: (request: string, headers?: object) => {
                            return Observable.create((observer) => {
                                observer.next({});
                                observer.complete();
                            });
                        },
                        post: (request: string, body?: string, headers?: object) => {
                            return Observable.create((observer) => {
                                observer.next({});
                                observer.complete();
                            });
                        },
                        put: (request: string, headers?: object) => {
                            return Observable.create((observer) => {
                                observer.next({});
                                observer.complete();
                            });
                        },
                        patch: (request: string, body?: string, headers?: object) => {
                            return Observable.create((observer) => {
                                observer.next({});
                                observer.complete();
                            });
                        }
                    }
                }
            ]
        })
            .compileComponents()
        service = TestBed.inject(HttpDataService)
    })
    it('Should be created', () => {
        console.log('><[Infra/HttpDataService]> should be created')
        expect(service).toBeDefined()
    })
    it('GET request with only request and no options', async () => {
        console.log('getRequest->' + JSON.stringify(getRequest))
        await service.httpCall(getRequest)
            .subscribe((response: any) => {
                expect(response).toBeDefined()
            })
    })
})
