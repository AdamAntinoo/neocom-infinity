import { Test } from "@nestjs/testing"
import { GetTypeInformationUseCase } from "./GetTypeInformation.usecase"
import { ESIDataUniverseServicesPort } from "application/ports/ESIDataUniverseServices.port"
import { V1EsiTypeDto } from "neocom-domain"

describe('USECASE CapsuleerMiningOperationsUseCase [Module: Application.UseCases]', () => {
    let useCase: GetTypeInformationUseCase
    let dataServices: ESIDataUniverseServicesPort

    beforeEach(async () => {
        const moduleRef = await Test.createTestingModule({
            providers: [
                {
                    provide: ESIDataUniverseServicesPort, useValue: {
                        getEsiType: (typeId: number) => {
                            console.log('method->' + 'GET')
                            console.log('request->' + typeId)
                            return new Promise((resolve) => {
                                resolve({
                                    type_id: typeId,
                                    name: '-type-name-',
                                    description: '-test-description-',
                                    group_id: 34,
                                    icon_id: 34,
                                    volume: 0.1
                                })
                            })
                        },
                        getEsiGroup: (groupId: number) => {
                            console.log('method->' + 'GET')
                            console.log('request->' + groupId)
                            return new Promise((resolve) => {
                                resolve({
                                    group_id: groupId,
                                    name: '-group-name-',
                                    category_id: 76
                                })
                            })
                        },
                        getEsiCategory: (categoryId: number) => {
                            console.log('method->' + 'GET')
                            console.log('request->' + categoryId)
                            return new Promise((resolve) => {
                                resolve({
                                    category_id: categoryId,
                                    name: '-category-name-'
                                })
                            })
                        }
                    }
                }
            ],
        }).compile()

        dataServices = await moduleRef.resolve(ESIDataUniverseServicesPort)
        useCase = new GetTypeInformationUseCase(dataServices)
    })

    describe('constructor contract', () => {
        test('should be created', () => {
            expect(useCase).toBeDefined()
        })
    })
    describe('endpoints phase', () => {
        test('when request for an esi type', async () => {
            expect(useCase).toBeDefined()
            const typeId = 17464
            const sut: Promise<V1EsiTypeDto> = useCase.esiGetTypeInformation(typeId)
            expect(sut).toBeDefined()
            await sut.then((response: V1EsiTypeDto) => {
                expect(response.typeId).toBe(17464)
                expect(response.iconId).toBe(34)
                expect(response.name).toBe('-type-name-')
                expect(response.description).toBe('-test-description-')
                expect(response.groupId).toBe(34)
                expect(response.groupName).toBe('-group-name-')
                expect(response.categoryId).toBe(76)
                expect(response.categoryName).toBe('-category-name-')
                expect(response.volume).toBe(0.1)
                expect(response.marketDataLink).toBe('/esi/v1/fuzzworks/marketData/17464/30000142')
            })
        })
    })
})
