import { HttpService } from "@nestjs/axios"
import { ConfigService } from "@nestjs/config"
import { Test } from "@nestjs/testing"
import { Observable } from "rxjs"
import { ESIDataUniverseAdapter } from "./ESIData.universe.adapter"
import { GetUniverseTypesTypeIdOk } from "application/domain/esi-model/getUniverseTypesTypeIdOk"
import { GetUniverseGroupsGroupIdOk } from "application/domain/esi-model/getUniverseGroupsGroupIdOk"
import { GetUniverseCategoriesCategoryIdOk } from "application/domain/esi-model/getUniverseCategoriesCategoryIdOk"
import { FuzzWorkMarketData, MarketData } from "neocom-domain"

describe('SERVICE ESIDataUniverseAdapter [Module: Infrastructure.service]', () => {
    let httpService: HttpService
    let configure: ConfigService
    let esiUniverseService: ESIDataUniverseAdapter

    beforeEach(async () => {
        const moduleRef = await Test.createTestingModule({
            providers: [
                ConfigService,
                {
                    provide: HttpService, useValue: {
                        get: (request: string) => {
                            switch (request) {
                                case 'undefined/universe/types/17464': {
                                    return new Observable((observer) => {
                                        observer.next({
                                            data: {
                                                "capacity": 0,
                                                "description": "Massive Scordite was the stuff of legend in the early days of space exploration, due to a 10% higher mineral yield than standard scordite. Though it has long since been taken over in value by other ores it still has a special place in the hearts of veteran miners.\r\n\r\nScordite is amongst the most common ore types in the central regions of known universe. It has a fair bit of <a href=showinfo:34>Tritanium</a> and <a href=showinfo:35>Pyerite</a>. Good choice for those starting their mining careers.\r\n\r\nAvailable primarily in high security status star systems.",
                                                "group_id": 460,
                                                "icon_id": 1356,
                                                "market_group_id": 519,
                                                "mass": 1e+35,
                                                "name": "Massive Scordite",
                                                "packaged_volume": 0.15,
                                                "portion_size": 100,
                                                "published": true,
                                                "radius": 1,
                                                "type_id": 17464,
                                                "volume": 0.15
                                            }
                                        })
                                    })
                                }
                                case 'undefined/universe/groups/17464': {
                                    return new Observable((observer) => {
                                        observer.next({
                                            data: {
                                                "category_id": 25,
                                                "group_id": 460,
                                                "name": "Scordite",
                                                "published": true,
                                                "types": [
                                                    1228,
                                                    17463,
                                                    17464,
                                                    28427,
                                                    28428,
                                                    28429,
                                                    46687,
                                                    46703,
                                                    62520,
                                                    62521,
                                                    62522,
                                                    62523
                                                ]
                                            }
                                        })
                                    })
                                }
                                case 'undefined/universe/categories/17464': {
                                    return new Observable((observer) => {
                                        observer.next({
                                            data: {
                                                "category_id": 25,
                                                "name": "Asteroid",
                                                "published": true
                                            }
                                        })
                                    })
                                }
                                case 'https://market.fuzzwork.co.uk/aggregates/?region=30000142&types=17464': {
                                    return new Observable((observer) => {
                                        observer.next({
                                            data: {
                                                "17464": {
                                                    "buy": {
                                                        "weightedAverage": "4.02878502065",
                                                        "max": "5.95",
                                                        "min": "0.01",
                                                        "stddev": "1.62036217159",
                                                        "median": "5.0",
                                                        "volume": "10024734026.0",
                                                        "orderCount": "52",
                                                        "percentile": "5.50168617928"
                                                    },
                                                    "sell": {
                                                        "weightedAverage": "6.60015441538",
                                                        "max": "2201571.0",
                                                        "min": "5.01",
                                                        "stddev": "177420.733866",
                                                        "median": "6.38",
                                                        "volume": "25573930856.0",
                                                        "orderCount": "179",
                                                        "percentile": "5.92257900667"
                                                    }
                                                }
                                            }
                                        })
                                    })
                                }
                            }
                        }
                    }
                }
            ],
        }).compile()

        httpService = moduleRef.get<HttpService>(HttpService)
        configure = moduleRef.get<ConfigService>(ConfigService)
        esiUniverseService = new ESIDataUniverseAdapter(httpService, configure)
    })

    describe('constructor contract phase', () => {
        test('should be created', () => {
            expect(esiUniverseService).toBeDefined()
        })
    })
    describe('endpoints phase', () => {
        test('when the esi type endpoint is called then we get a esi type data', async () => {
            const typeId: number = 17464
            const sut: Promise<GetUniverseTypesTypeIdOk> = esiUniverseService.getEsiType(typeId)
            expect(sut).toBeDefined()
            await sut.then(data => {
                expect(data.type_id).toBe(17464)
                expect(data.group_id).toBe(460)
                expect(data.icon_id).toBe(1356)
                expect(data.name).toBe('Massive Scordite')
                expect(data.volume).toBe(0.15)
            })
        })
        test('when the esi group endpoint is called then we get a esi group data', async () => {
            const typeId: number = 17464
            const sut: Promise<GetUniverseGroupsGroupIdOk> = esiUniverseService.getEsiGroup(typeId)
            expect(sut).toBeDefined()
            await sut.then(data => {
                expect(data.group_id).toBe(460)
                expect(data.name).toBe('Scordite')
                expect(data.category_id).toBe(25)
            })
        })
        test('when the esi category endpoint is called then we get a esi category data', async () => {
            const typeId: number = 17464
            const sut: Promise<GetUniverseCategoriesCategoryIdOk> = esiUniverseService.getEsiCategory(typeId)
            expect(sut).toBeDefined()
            await sut.then(data => {
                expect(data.category_id).toBe(25)
                expect(data.name).toBe('Asteroid')
            })
        })
        test('when the fuxxwork service endpoint is called then we get market data', async () => {
            const typeId: number = 17464
            const systemId: number = 30000142
            const sut: Promise<FuzzWorkMarketData> = esiUniverseService.getFuzzWorkMarketData(typeId, systemId)
            expect(sut).toBeDefined()
            await sut.then(data => {
                expect(data[typeId]).toBeDefined()
                const marketData: FuzzWorkMarketData = data[typeId]
                expect(marketData).toBeDefined()
                const buy: MarketData = marketData.buy
                const sell: MarketData = marketData.sell
                expect(buy).toBeDefined()
                expect(sell).toBeDefined()
                expect(Number(buy.max)).toBe(5.95)
                expect(Number(buy.median)).toBe(5.0)
                expect(Number(buy.orderCount)).toBe(52)
                expect(Number(sell.min)).toBe(5.01)
                expect(Number(sell.median)).toBe(6.38)
                expect(Number(sell.orderCount)).toBe(179)
            })
        })
    })
})
