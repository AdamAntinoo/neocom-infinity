import { ESIDataServicesPort } from "@App/ports/ESIDataServices.port"
import { CapsuleerMiningOperationsUseCase } from "./CapsuleerMiningOperationsUseCase"
import { HttpService } from "@nestjs/axios"
import { Observable } from "rxjs"
import { Test } from "@nestjs/testing"
import { EsiMiningAdapter } from "@Infra/adapter/outbound/ESISecureDataServices/esi.mining.adapter"
import { ESISecureDataServiceHALGeneratorAdapter } from "@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservice.halgenerator.adapter"
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation"

describe('USECASE CapsuleerMiningOperationsUseCase [Module: Application.UseCases]', () => {
    let useCase: CapsuleerMiningOperationsUseCase

    beforeEach(async () => {
        const moduleRef = await Test.createTestingModule({
            providers: [
                { provide: CapsuleerMiningOperationsUseCase, useClass: CapsuleerMiningOperationsUseCase },
                { provide: ESIDataServicesPort, useClass: EsiMiningAdapter },
                { provide: ESISecureDataServiceHALGeneratorAdapter, useClass: ESISecureDataServiceHALGeneratorAdapter },
                {
                    provide: HttpService, useValue: {
                        get: (request: string) => {
                            console.log('method->' + 'GET')
                            console.log('request->' + request)
                            return new Observable((observer) => {
                                observer.next({
                                    data: [
                                        {
                                            "date": "2024-02-23",
                                            "quantity": 210,
                                            "solar_system_id": 30003541,
                                            "type_id": 17453
                                        }
                                    ]
                                })
                            })
                        }
                    }
                }
            ],
        }).compile()

        useCase = await moduleRef.resolve(CapsuleerMiningOperationsUseCase);
    })

    describe('constructor contract', () => {
        test('should be created', () => {
            expect(useCase).toBeDefined()
        })
    })
    describe('endpoints', () => {
        test('when request for mining operations then return a list of trabsformed operations', () => {
            expect(useCase).toBeDefined()
            const characterId: number = 321
            const sut: Promise<V2MiningOperation[]> = useCase.getMiningOperations(characterId)
            expect(sut).toBeDefined
            sut.then((response: V2MiningOperation[]) => {
                expect(response.length).toBe(4)
                expect(response[0] instanceof V2MiningOperation).toBeTruthy
            })
        })
    })
})
