import { GetTypeInformationUseCase } from "application/use-cases/esi-universe/GetTypeInformation.usecase"
import { V1EsiUniverseController } from "./v1.esiuniverse.controller"
import { Test } from "@nestjs/testing"
import { V1EsiTypeDto, V1MarketDataDto, V1SpaceLocationDto } from "neocom-domain"
import { GetMarketDataUseCase, GetMarketDataUseCaseInput } from "application/use-cases/esi-universe/GetMarketData.usecase"
import { GetSpaceLocationUseCase } from "application/use-cases/esi-universe/GetSpaceLocation.usecase"

describe('CONTROLLER V1EsiUniverseController [Module: Infrastructure.Adapters]', () => {
    let controller: V1EsiUniverseController
    let typeUseCase: GetTypeInformationUseCase
    let marketUseCase: GetMarketDataUseCase
    let locationUseCase: GetSpaceLocationUseCase

    beforeEach(async () => {
        const moduleRef = await Test.createTestingModule({
            controllers: [V1EsiUniverseController],
            providers: [
                {
                    provide: GetTypeInformationUseCase, useValue: {
                        esiGetTypeInformation: (typeId: number) => {
                            return new Promise((resolve) => {
                                resolve({
                                    typeId: typeId,
                                    iconId: 34,
                                    name: '-type-name-',
                                    description: '-test-description-',
                                    groupId: 34,
                                    groupName: '-group-name-',
                                    categoryId: 76,
                                    categoryName: '-category-name-',
                                    volume: 0.1,
                                    marketDataLink: '/esi/v1/fuzzworks/marketData/17464'
                                })
                            })
                        }
                    }
                },
                {
                    provide: GetMarketDataUseCase, useValue: {
                        esiGetMarketData: (input: GetMarketDataUseCaseInput) => {
                            return new Promise((resolve) => {
                                resolve(new V1MarketDataDto({
                                    buyPrice: 15.0,
                                    buyOrderCount: 100.0,
                                    sellPrice: 34.0,
                                    sellOrderCount: 80.0
                                }))
                            })
                        }
                    }
                },
                {
                    provide: GetSpaceLocationUseCase, useValue: {
                        esiGetSpaceLocation: (locationId: number) => {
                            return new Promise((resolve) => {
                                resolve(new V1SpaceLocationDto({
                                    jsonClass: 'SpaceLocationDto',
                                    referenceType: 'System',
                                    regionId: 10000002,
                                    regionName: 'The Forge',
                                    constellationId: 20000020,
                                    constellationName: 'Kimotoro',
                                    solarSystemId: 30000142,
                                    solarSystemName: 'Jita'
                                }))
                            })
                        }
                    }
                }
            ],
        }).compile();

        typeUseCase = moduleRef.get<GetTypeInformationUseCase>(GetTypeInformationUseCase)
        marketUseCase = moduleRef.get<GetMarketDataUseCase>(GetMarketDataUseCase)
        locationUseCase = moduleRef.get<GetSpaceLocationUseCase>(GetSpaceLocationUseCase)
        controller = new V1EsiUniverseController(typeUseCase, marketUseCase, locationUseCase)
    })

    describe('Constructor contract phase', () => {
        test('should be defined', () => {
            expect(controller).toBeDefined()
        })
    })
    describe('Endpoints phase', () => {
        test('when a request for esi type arrives we get a valid response', async () => {
            expect(controller).toBeDefined()
            const typeId: number = 17464
            const sut: V1EsiTypeDto = await controller.esiGetTypeInformation({ typeId: typeId })
                .then((response: V1EsiTypeDto) => {
                    expect(response.typeId).toBe(17464)
                    expect(response.iconId).toBe(34)
                    expect(response.name).toBe('-type-name-')
                    expect(response.description).toBe('-test-description-')
                    expect(response.groupId).toBe(34)
                    expect(response.groupName).toBe('-group-name-')
                    expect(response.categoryId).toBe(76)
                    expect(response.categoryName).toBe('-category-name-')
                    expect(response.volume).toBe(0.1)
                    expect(response.marketDataLink).toBe('/esi/v1/fuzzworks/marketData/17464')
                    return response
                })
            expect(sut).toBeDefined()
        })
        test('when a request for market data arrives we get a valid response', async () => {
            expect(controller).toBeDefined()
            const input: GetMarketDataUseCaseInput = {
                typeId: 17464,
                systemId: 300
            }
            const sut: V1MarketDataDto = await controller.esiGetMarketData(input)
                .then((response: V1MarketDataDto) => {
                    expect(response.buyPrice).toBe(15.0)
                    expect(response.buyOrderCount).toBe(100.0)
                    expect(response.sellPrice).toBe(34.0)
                    expect(response.sellOrderCount).toBe(80.0)
                    return response
                })
            expect(sut).toBeDefined()
        })
        test('when a request for space location data arrives we get a valid response', async () => {
            expect(controller).toBeDefined()
            const locationId: number = 30000142
            const sut: V1SpaceLocationDto = await controller.esiGetLocation({ locationId: locationId })
                .then((response: V1SpaceLocationDto) => {
                    expect(response.referenceType).toBe('System')
                    expect(response.regionId).toBe(10000002)
                    expect(response.regionName).toBe('The Forge')
                    expect(response.constellationId).toBe(20000020)
                    expect(response.constellationName).toBe('Kimotoro')
                    expect(response.solarSystemId).toBe(30000142)
                    expect(response.solarSystemName).toBe('Jita')
                    return response
                })
            expect(sut).toBeDefined()
        })
    })
})
