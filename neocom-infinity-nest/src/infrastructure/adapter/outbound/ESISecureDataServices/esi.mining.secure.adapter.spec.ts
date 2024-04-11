import { HttpService } from '@nestjs/axios';
import { Test, TestingModule } from '@nestjs/testing'
import { Observable } from 'rxjs';
import { EsiMiningAdapter } from './esi.mining.secure.adapter';
import { V2MiningOperation } from '@Domain/entities/V2.MiningOperation';
import { ESISecureDataServiceHALGeneratorAdapter } from './esi.securedataservice.halgenerator.adapter';

describe('SERVICE EsiMiningAdapter [Module: Infrastructure.service]', () => {
    let esiAdapter: EsiMiningAdapter

    beforeEach(async () => {
        const moduleRef = await Test.createTestingModule({
            providers: [
                { provide: EsiMiningAdapter, useClass: EsiMiningAdapter },
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

        esiAdapter = await moduleRef.resolve(EsiMiningAdapter);
    })

    describe('constructor contract', () => {
        test('should be created', () => {
            expect(esiAdapter).toBeDefined()
        })
    })
    describe('functionality', () => {
        test('when the endpoint is called then we get a mining operation', () => {
            const pilotId: number = 321
            const sut: Promise<V2MiningOperation[]> = esiAdapter.miningOperations.getMiningOperations(pilotId)
            expect(sut).toBeDefined()
            sut.then(data => {
                expect(data.length).toBe(1)
                expect(data[0] instanceof V2MiningOperation)
                const operation = data[0]
                console.log('operation->' + JSON.stringify(operation))
                expect(operation.jsonClass).toBe('MiningOperation')
                expect(operation.id).toBe('2024-02-23-30003541-17453')
                expect(operation.date).toBe('2024-02-23')
                expect(operation.quantity).toBe(210)
                expect(operation.solarSystem).toBe('https://esi.evetech.net/latest/universe/systems/' + '30003541' + '/?datasource=tranquility&language=en')
                expect(operation.typeId).toBe('https://esi.evetech.net/latest/universe/types/' + '17453' + '/?datasource=tranquility&language=en')
            })
        })
    })
})
