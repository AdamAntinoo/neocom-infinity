import { NO_ERRORS_SCHEMA } from '@angular/core'
import { Observable } from 'rxjs'
import { HttpClient, HttpClientModule } from "@angular/common/http"
import { HttpClientTestingModule } from "@angular/common/http/testing"
import { TestBed } from "@angular/core/testing"

import { ESISecureDataServiceAdapter } from './ESISecureDataServiceAdapter'
import { ConfigurationAdapter } from '../configuration/ConfigurationAdapter'
import { HttpServiceMock } from 'src/test/service-mocks/HttpService.mock'
import { V1MiningOperationDto } from 'neocom-domain'

describe('ADAPTER ESISecureDataServiceAdapter [Module: Infrastrucrture/Adapters]', () => {
    let service: ESISecureDataServiceAdapter

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            providers: [
                { provide: ConfigurationAdapter, useClass: ConfigurationAdapter },
                { provide: HttpClient, useClass: HttpServiceMock },
            ]
        })
            .compileComponents()
        service = TestBed.inject(ESISecureDataServiceAdapter)
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
            const pilotId: number = 776665
            const sut: Observable<V1MiningOperationDto[]> = service.v1_apiEsiMiningOperationsData(pilotId)
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
