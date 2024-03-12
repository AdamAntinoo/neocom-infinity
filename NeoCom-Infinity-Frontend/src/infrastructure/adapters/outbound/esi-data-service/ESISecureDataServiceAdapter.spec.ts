import { NO_ERRORS_SCHEMA } from '@angular/core'
import { Observable } from 'rxjs'
import { HttpClient, HttpClientModule } from "@angular/common/http"
import { HttpClientTestingModule } from "@angular/common/http/testing"
import { TestBed } from "@angular/core/testing"

import { ESISecureDataServiceAdapter } from './ESISecureDataServiceAdapter'
import { ESIMiningOperation } from './domain/ESIMiningOperation'
import { ConfigurationAdapter } from '../../inbound/configuration/ConfigurationAdapter'

fdescribe('ADAPTER ESISecureDataServiceAdapter [Module: Infra]', () => {
    let service: ESISecureDataServiceAdapter
    // let getRequest: TestTypedRequest = new TestTypedRequest().prepare({ param: 123 })

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [HttpClientModule
                // HttpClientTestingModule
            ],
            providers: [
                { provide: ConfigurationAdapter, useClass: ConfigurationAdapter },
                { provide: HttpClient, useClass: HttpClient },
                // {
                //     provide: HttpClient, useValue: {
                //         get: (request: string, headers?: object) => {
                //             console.log('method->' + 'GET')
                //             console.log('request->' + request)
                //             console.log('options->' + JSON.stringify(headers))

                //             return Observable.create((observer) => {
                //                 observer.next([new ESIMiningOperation({
                //                     quantity: 324
                //                 })]);
                //                 observer.complete();
                //             });
                //         }
                //     }
                // }
            ]
        })
            .compileComponents()
        service = TestBed.inject(ESISecureDataServiceAdapter)
    })
    it('Should be created', () => {
        console.log('><[Infra/ESISecureDataServiceAdapter]> should be created')
        expect(service).toBeDefined()
    })
    it('when requested for mining operations', async () => {
        const pilotId: number = 321
        await service.v1_apiEsiMiningOperationsData(pilotId)
            .subscribe((response: ESIMiningOperation[]) => {
                console.log('response->' + JSON.stringify(response))
                expect(response).toBeDefined()
                expect(response.length).toBe(1)
                const sut: ESIMiningOperation = response[0]
                expect(sut).toBeDefined()
                expect(sut.quantity).toBe(324)
            },
                err => console.log('v1_apiEsiMiningOperationsData.exception->' + JSON.stringify(err)))
    })
})
