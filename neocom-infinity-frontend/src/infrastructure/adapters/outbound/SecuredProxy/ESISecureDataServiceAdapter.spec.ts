import { NO_ERRORS_SCHEMA } from '@angular/core'
import { Observable } from 'rxjs'
import { HttpClient } from "@angular/common/http"
import { TestBed } from "@angular/core/testing"

import { ConfigurationAdapter } from '../configuration/ConfigurationAdapter'
import { HttpServiceMock } from 'src/test/service-mocks/HttpService.mock'
import { V1MiningOperationDto } from 'neocom-domain'
import { V1SecuredProxyAdapter } from './v1.SecuredProxy.adapter'

describe('ADAPTER V1SecuredProxyAdapter [Module: Infrastrucrture/Adapters]', () => {
    let service: V1SecuredProxyAdapter

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            providers: [
                { provide: ConfigurationAdapter, useClass: ConfigurationAdapter },
                { provide: HttpClient, useClass: HttpServiceMock },
            ]
        })
            .compileComponents()
        service = TestBed.inject(V1SecuredProxyAdapter)
    })
    it('Should be created', () => {
        console.log('><[Infra/ESISecureDataServiceAdapter]> should be created')
        expect(service).toBeDefined()
    })
    it('check the injected are available', () => {
        const serviceAny: any = service as any
        expect(service['configuration']).toBeDefined()
        expect(service['httpService']).toBeDefined()
    })
    describe('Endpoint phase', () => {
        it('when the backend request then return an observable', async () => {
            const pilotId: number = 77665
            const sut: Observable<V1MiningOperationDto[]> = service.v3_apiNeocomMiningOperationsData(pilotId)
            expect(sut).toBeDefined()
            await sut.subscribe((operations: V1MiningOperationDto[]) => {
                expect(operations).toBeDefined()
                expect(operations.length).toBe(3)
                console.log('operation->' + JSON.stringify(operations[0]))
                expect(operations[0].jsonClass).toBe('MiningOperationDto')
                expect(operations[0].resources).toBeDefined()
                expect(operations[0].resources.length).toBe(2)
                expect(operations[0].resources[0].jsonClass).toBe('StackDto')
            })
        })
    })
})
