import { GetTypeInformationUseCase } from "application/use-cases/esi-universe/GetTypeInformation.usecase"
import { V1EsiUniverseController } from "./v1.esiuniverse.controller"
import { NestFactory } from "@nestjs/core"
import { AppModule } from "app.module"
import { Test } from "@nestjs/testing"
import { V1EsiTypeDto } from "neocom-domain"

describe('CONTROLLER V1MiningOperationsController [Module: Infrastructure.Adapters]', () => {
    let appModule: any
    let controller: V1EsiUniverseController
    let useCase: GetTypeInformationUseCase

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
                }
            ],
        }).compile();

        useCase = moduleRef.get<GetTypeInformationUseCase>(GetTypeInformationUseCase);
        controller = moduleRef.get<V1EsiUniverseController>(V1EsiUniverseController);
    })

    describe('Constructor contract phase', () => {
        test('should be defined', () => {
            expect(controller).toBeDefined()
        })
    })
    describe('Endpoints phase', () => {
        test('when a request with id arrives we get a valid response', async () => {
            expect(controller).toBeDefined()
            const typeId: number = 17464
            const sut: V1EsiTypeDto = await controller.esiGetTypeInformation(typeId)
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
    })
})
