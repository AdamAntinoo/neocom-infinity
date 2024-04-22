import { TestBed } from '@angular/core/testing';

import { V1MiningOperationsAdapterService } from './V1.mining-operations.adapter';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ESISecureDataServiceAdapter } from '../esi-data-service/ESISecureDataServiceAdapter';
import { BackendFactory } from '@adapter/factory/BackendFactory';
import { FactoryTarget } from '@angular/compiler';
import { Observable } from 'rxjs';
import { V3MiningOperation } from '@domain/industry/V3.MiningOperation.domain';
import { ConfigurationAdapter } from '../configuration/ConfigurationAdapter';
import { V1MiningOperationDto } from 'neocom-domain';

describe('ADAPTER V1MiningOperationsAdapterService [Module: Infrastructure]', () => {
    let service: ESISecureDataServiceAdapter
    let factory: BackendFactory
    let adapter: V1MiningOperationsAdapterService

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [HttpClientModule
            ],
            providers: [
                {
                    provide: HttpClient, useValue: {
                        get: (request: string, headers?: object) => {
                            console.log('method->' + 'GET')
                            console.log('request->' + request)
                            console.log('options->' + JSON.stringify(headers))

                            return Observable.create((observer) => {
                                observer.next([
                                    {
                                        "jsonClass": "MiningOperationDto",
                                        "resources": [
                                            {
                                                "jsonClass": "StackDto",
                                                "quantity": 10000,
                                                "typeLink": "/esi/v1/universe/types/17464"
                                            },
                                            {
                                                "jsonClass": "StackDto",
                                                "quantity": 12000,
                                                "typeLink": "/esi/v1/universe/types/1224"
                                            }
                                        ],
                                        "id": "2024-02-25/30003538",
                                        "date": "2024-02-25",
                                        "solarSystemLink": "/esi/v1/universe/spacelocation/30003538"
                                    },
                                    {
                                        "jsonClass": "MiningOperationDto",
                                        "resources": [
                                            {
                                                "jsonClass": "StackDto",
                                                "quantity": 210,
                                                "typeLink": "/esi/v1/universe/types/17453"
                                            }
                                        ],
                                        "id": "2024-02-23/30003541",
                                        "date": "2024-02-23",
                                        "solarSystemLink": "/esi/v1/universe/spacelocation/30003541"
                                    },
                                    {
                                        "jsonClass": "MiningOperationDto",
                                        "resources": [
                                            {
                                                "jsonClass": "StackDto",
                                                "quantity": 34243,
                                                "typeLink": "/esi/v1/universe/types/17464"
                                            },
                                            {
                                                "jsonClass": "StackDto",
                                                "quantity": 3073,
                                                "typeLink": "/esi/v1/universe/types/1224"
                                            },
                                            {
                                                "jsonClass": "StackDto",
                                                "quantity": 10288,
                                                "typeLink": "/esi/v1/universe/types/17459"
                                            }
                                        ],
                                        "id": "2024-02-23/30003538",
                                        "date": "2024-02-23",
                                        "solarSystemLink": "/esi/v1/universe/spacelocation/30003538"
                                    }
                                ])
                                observer.complete();
                            })
                        }
                    }
                },
                { provide: ConfigurationAdapter, useClass: ConfigurationAdapter },
                { provide: ESISecureDataServiceAdapter, useClass: ESISecureDataServiceAdapter },
                { provide: BackendFactory, useClass: BackendFactory }
            ]
        })
            .compileComponents()
        factory = TestBed.inject(BackendFactory)
        service = TestBed.inject(ESISecureDataServiceAdapter)

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
            await sut.subscribe((operations: V3MiningOperation[]) => {
                console.log('enter subscription')
                expect(operations).toBeDefined()
                expect(operations.length).toBe(3)
                const operation: V3MiningOperation = operations[0]
                console.log('oepration->' + operation)
                expect(operation).toBeDefined()
            })
        })
    })
})
