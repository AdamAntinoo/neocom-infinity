import { TestBed } from '@angular/core/testing';

import { V1MiningOperationsAdapterService } from './V1.mining-operations.adapter';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { BackendFactory } from '@adapter/factory/BackendFactory';
import { Observable } from 'rxjs';
import { V3MiningOperation } from '@domain/industry/V3.MiningOperation.domain';
import { ConfigurationAdapter } from '../configuration/ConfigurationAdapter';
import { HttpServiceMock } from 'src/test/service-mocks/HttpService.mock';
import { UnsecuredProxy } from '../UnsecuredProxy/V1.UnsecuredProxy.adapter';
import { UnsecureProxyMock } from 'src/test/service-mocks/UnsecureProxy.mock';
import { V1SecuredProxyAdapter } from '../SecuredProxy/v1.SecuredProxy.adapter';

xdescribe('ADAPTER V1MiningOperationsAdapterService [Module: Infrastructure]', () => {
    let service: V1SecuredProxyAdapter
    let factory: BackendFactory
    let adapter: V1MiningOperationsAdapterService

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [HttpClientModule
            ],
            providers: [
                { provide: HttpClient, useClass: HttpServiceMock },
                { provide: ConfigurationAdapter, useClass: ConfigurationAdapter },
                { provide: V1SecuredProxyAdapter, useClass: V1SecuredProxyAdapter },
                { provide: BackendFactory, useClass: BackendFactory },
                { provide: UnsecuredProxy, useClass: UnsecureProxyMock }
            ]
        })
            .compileComponents()
        factory = TestBed.inject(BackendFactory)
        service = TestBed.inject(V1SecuredProxyAdapter)

        adapter = new V1MiningOperationsAdapterService(service, factory)
    });

    describe('Constructor contract phase', () => {
        it('should be created', () => {
            expect(service).toBeTruthy()
        })
    })
    describe('Service Delivery phase', () => {
        it('when the downloadMiningOperationsForCharacter is called we return a valid Promise', async () => {
            expect(adapter).toBeDefined()
            const pilotId: number = 93813310
            const sut: Observable<V3MiningOperation[]> = adapter.downloadMiningOperationsForCharacter(pilotId)
            expect(sut).toBeDefined()
            // console.log('data ready')
            await sut.subscribe((operations: V3MiningOperation[]) => {
                // console.log(JSON.stringify(operations))
                // console.log('enter subscription')
                expect(operations).toBeDefined()
                expect(operations.length).toBe(3)
                //     const operation: V3MiningOperation = operations[0]
                //     expect(operation).toBeDefined()
            })
        })
    })
})
