import { TestBed } from '@angular/core/testing';

import { V1MiningOperationsAdapterService } from './v1-mining-operations-adapter.service';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ESISecureDataServiceAdapter } from '../esi-data-service/ESISecureDataServiceAdapter';
import { V1MiningOperation } from '@domain/industry/V1.MiningOperation.domain';
import { data } from 'cypress/types/jquery';

describe('ADAPTER V1MiningOperationsAdapterService [Module: Infrastructure]', () => {
    let service: V1MiningOperationsAdapterService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [HttpClientModule
                // HttpClientTestingModule
            ],
            providers: [
                { provide: ESISecureDataServiceAdapter, useClass: ESISecureDataServiceAdapter },
            ]
        })
            .compileComponents()
        service = TestBed.inject(V1MiningOperationsAdapterService);
    });

    describe('Constructor contract phase', () => {
        it('should be created', () => {
            expect(service).toBeTruthy()
        })
    })
    describe('Service Delivery phase', () => {
        it('when the downloadMiningOperationsForCharacter is called we return a valid Promise', () => {
            expect(service).toBeTruthy()
            const pilotId: number = 93813310
            const sut = service.downloadMiningOperationsForCharacter(pilotId)
            expect(sut).toBeDefined
            console.log(JSON.stringify(sut))
            sut.then((dataList: V1MiningOperation[]) => {
                dataList.forEach(element => {
                    console.log('element->' + element)

                });
            })
        })
    })
})
