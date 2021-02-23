// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
// - TESTING
import { TestBed } from '@angular/core/testing'
import { async } from '@angular/core/testing'
import { HttpTestingController } from '@angular/common/http/testing'
// - APP
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
import { SupportHALResolver } from '@app/testing/SupportHALResolver.service'
import { HALResolver } from '@app/services/HALResolver.service'
import { HttpClient } from '@angular/common/http'
import { EsiTypeDto } from "./EsiType.dto"
import { IsolationService } from '@innovative/services/isolation.service'

describe('CLASS EsiTypeDto [Module: DOMAIN.DTO]', () => {
    let halResolver: SupportHALResolver

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
            ],
            declarations: [],
            providers: [
                { provide: IsolationService, useClass: SupportIsolationService },
                { provide: HttpClient, useClass: HttpTestingController },
                { provide: HALResolver, useClass: SupportHALResolver }
            ]
        }).compileComponents()
        halResolver = TestBed.inject<SupportHALResolver>(SupportHALResolver)
    })

    // - C O N S T R U C T I O N   C O N T R A C T
    describe('Construction Contract', () => {
        it('Should be created', () => {
            expect(new EsiTypeDto()).toBeTruthy()
        })
        it('Initial state', () => {
            const testType = new EsiTypeDto()
            expect(testType.marketData).toBeUndefined()
        })
        it('Complete construction', () => {
            const testState = new EsiTypeDto({
                marketData: {
                    "rel": "marketData",
                    "href": "http://localhost:5242/api/v1/universe/market/consolidated/byregion/10000043/11535"
                }
            })
            expect(testState.marketData).toBeDefined()
            expect(testState.marketData.getHref()).toBeDefined()
            expect(testState.marketData.getRelation()).toBe('marketData')
        })
    })
    // - C O D E   C O V E R A G E
    describe('Code Coverage Phase', () => {
        it('DTO Transformation: [transform]', () => {
            const type = new EsiTypeDto({
                marketData: {
                    "rel": "marketData",
                    "href": "http://localhost:5242/api/v1/universe/market/consolidated/byregion/10000043/11535"
                }
            })
            const esiType = type.transform(halResolver)
            expect(esiType).toBeDefined()
            expect(esiType.marketData.typeId).toBe(11535)
        })
    })
})
