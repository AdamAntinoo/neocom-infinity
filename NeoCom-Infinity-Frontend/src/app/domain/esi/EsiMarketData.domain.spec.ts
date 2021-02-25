// - TESTING
import { TestBed } from '@angular/core/testing'
// - PROVIDERS
// - DOMAIN
import { EsiMarketData } from './EsiMarketData.esi'

describe('CLASS EsiMarketData [Module: LOYALTY]', () => {
    let testMarketData: EsiMarketData

    beforeEach(() => {
        testMarketData = new EsiMarketData({
            "typeId": 11535,
            "bestSellOrder": {
                "station": {
                    "locationType": "STATION",
                    "locationId": 60008494,
                    "stationId": 60008494,
                    "stationName": "Amarr VIII (Oris) - Emperor Family Academy",
                    "solarSystemId": 30002187,
                    "solarSystemName": "Amarr",
                    "securityClass": "A",
                    "securityStatus": 1.0,
                    "constellationId": 20000322,
                    "constellationName": "Throne Worlds",
                    "regionId": 10000043,
                    "regionName": "Domain"
                },
                "orderId": 5923675085,
                "price": 36800.0,
                "typeId": 11535,
                "volumeRemain": 1589,
                "volumeTotal": 8062,
                "buyOrder": false
            },
            "bestBuyOrder": {
                "station": {
                    "locationType": "STATION",
                    "locationId": 60008494,
                    "stationId": 60008494,
                    "stationName": "Amarr VIII (Oris) - Emperor Family Academy",
                    "solarSystemId": 30002187,
                    "solarSystemName": "Amarr",
                    "securityClass": "A",
                    "securityStatus": 1.0,
                    "constellationId": 20000322,
                    "constellationName": "Throne Worlds",
                    "regionId": 10000043,
                    "regionName": "Domain"
                },
                "orderId": 5902596037,
                "price": 1008.0,
                "typeId": 11535,
                "volumeRemain": 3000,
                "volumeTotal": 3000,
                "buyOrder": true
            },
            "sellOrders": [{
                "duration": 90,
                "isBuyOrder": false,
                "issued": "2021-02-13T01:48:31.000Z",
                "locationId": 60008494,
                "minVolume": 1,
                "orderId": 5923675085,
                "price": 36800.0,
                "range": "REGION",
                "systemId": 30002187,
                "typeId": 11535,
                "volumeRemain": 1589,
                "volumeTotal": 8062
            },
            {
                "duration": 90,
                "isBuyOrder": false,
                "issued": "2021-01-11T06:37:46.000Z",
                "locationId": 60008494,
                "minVolume": 1,
                "orderId": 5892639413,
                "price": 36900.0,
                "range": "REGION",
                "systemId": 30002187,
                "typeId": 11535,
                "volumeRemain": 113,
                "volumeTotal": 951
            }
            ],
            "sellDeep": 1702,
            "sellAverage": 36806.639247943596,
            "marketWidth": 35792.0
        })

        // - C O N S T R U C T I O N   P H A S E
        describe('Construction Phase', () => {
            it('Should be created', () => {
                console.log('><[domain/Fitting]> should be created')
                expect(new EsiMarketData()).toBeDefined()
            })
            it('Initial state', () => {
                const marketData = new EsiMarketData()
                const marketDataAny = marketData as any
                expect(marketData.typeId).toBeUndefined()
                expect(marketData.bestSellOrder).toBeUndefined()
                expect(marketDataAny.bestBuyOrder).toBeUndefined()
                expect(marketDataAny.sellOrders).toBeUndefined()
                expect(marketDataAny.sellDeep).toBeUndefined()
                expect(marketDataAny.sellAverage).toBeUndefined()
                expect(marketDataAny.marketWidth).toBeUndefined()
            })
            it('Complete construction', () => {
                const marketData = new EsiMarketData()
                const marketDataAny = marketData as any
                expect(marketData.typeId).toBe(11535)
                expect(marketData.bestSellOrder).toBeDefined()
                expect(marketDataAny.bestBuyOrder).toBeDefined()
                expect(marketDataAny.sellOrders).toBeDefined()
                expect(marketDataAny.sellDeep).toBe(1702)
                expect(marketDataAny.sellAverage).toBe(36806.639247943596)
                expect(marketDataAny.marketWidth).toBe(35792.0)
            })
        })
    })
})
