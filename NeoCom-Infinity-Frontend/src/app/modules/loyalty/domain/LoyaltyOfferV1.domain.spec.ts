// - TESTING
import { TestBed } from '@angular/core/testing'
// - PROVIDERS
// - DOMAIN
import { LoyaltyOfferV1 } from './LoyaltyOfferV1.domain'

describe('CLASS LoyaltyOfferV1 [Module: LOYALTY]', () => {
    let testOffer: LoyaltyOfferV1

    beforeEach(() => {
        testOffer = new LoyaltyOfferV1({
            "jsonClass": "LoyaltyOfferEntity",
            "offerId": 3433,
            "typeId": 9899,
            "type": {
                "rel": "type",
                "href": "http://localhost:5220/api/v1/universe/types/9899"
            },
            "typeName": "Ocular Filter - Basic",
            "corporationId": 1000179,
            "corporationName": "24th Imperial Crusade",
            "lpValue": 1244,
            "iskCost": 5250000,
            "lpCost": 5250,
            "quantity": 1,
            "marketRegionId": 10000043,
            "price": 1.178E7,
            "marketData": {
                "rel": "marketData",
                "href": "http://localhost:5220/api/v1/universe/market/consolidated/byregion/10000043/9899"
            },
            "marketHistory": {
                "rel": "marketHistory",
                "href": "https://esi.evetech.net/latest/markets/10000043/history/?datasource=tranquility&type_id=9899"
            }
        })
    })

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[domain/Fitting]> should be created')
            expect(new LoyaltyOfferV1()).toBeDefined()
        })
        it('Initial state', () => {
            const history = new LoyaltyOfferV1()
            expect(history.offerId).toBeUndefined()
            expect(history.typeId).toBeUndefined()
            expect(history.type).toBeUndefined()
            expect(history.typeName).toBeUndefined()
            expect(history.corporationId).toBeUndefined()
            expect(history.corporationName).toBeUndefined()
            expect(history.lpValue).toBeUndefined()
            expect(history.iskCost).toBeUndefined()
            expect(history.lpCost).toBeUndefined()
            expect(history.quantity).toBeUndefined()
            expect(history.marketRegionId).toBeUndefined()
            expect(history.price).toBeUndefined()
            expect(history.marketData).toBeUndefined()
            expect(history.marketHistory).toBeDefined()
            expect(history.marketHistory.length).toBe(0)
        })
        it('Complete construction', () => {
            expect(testOffer.offerId).toBe(3433)
            expect(testOffer.typeId).toBe(9899)
            expect(testOffer.type).toBeUndefined()
            expect(testOffer.typeName).toBe("Ocular Filter - Basic")
            expect(testOffer.corporationId).toBe(1000179)
            expect(testOffer.corporationName).toBe("24th Imperial Crusade")
            expect(testOffer.lpValue).toBe(1244)
            expect(testOffer.iskCost).toBe(5250000)
            expect(testOffer.lpCost).toBe(5250)
            expect(testOffer.quantity).toBe(1)
            expect(testOffer.marketRegionId).toBe(10000043)
            expect(testOffer.price).toBe(1.178E7)
            expect(testOffer.marketData).toBeUndefined()
            expect(testOffer.marketHistory).toBeDefined()
            expect(testOffer.marketHistory.length).toBe(0)
        })
    })
    // - G E T T E R   C O N T R A C T
    describe('Getter Contract', () => {
        it('getType.success: obtain the esi type instance', () => {
            expect(testOffer.getType()).toBeUndefined()
        })
    })
})
