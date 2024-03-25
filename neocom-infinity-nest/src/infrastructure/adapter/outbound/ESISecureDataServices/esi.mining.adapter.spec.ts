import { HttpService } from '@nestjs/axios';
import { Test, TestingModule } from '@nestjs/testing'
import { Observable } from 'rxjs';
import { EsiMiningAdapter } from './esi.mining.adapter';
import { V2MiningOperation } from '@Domain/entities/V2.MiningOperation';

describe('SERVICE EsiMiningAdapter [Module: Infrastructure.service]', () => {
    let esiAdapter: EsiMiningAdapter

    beforeEach(async () => {
        const moduleRef = await Test.createTestingModule({
            providers: [
                { provide: EsiMiningAdapter, useClass: EsiMiningAdapter },
                {
                    provide: HttpService, useValue: {
                        get: (request: string) => {
                            console.log('method->' + 'GET')
                            console.log('request->' + request)
                            return new Observable((observer) => {
                                observer.next([
                                    {
                                        "date": "2024-02-23",
                                        "quantity": 210,
                                        "solar_system_id": 30003541,
                                        "type_id": 17453
                                    }
                                ])
                                observer.next(
                                    {
                                        "date": "2024-02-23",
                                        "quantity": 210,
                                        "solar_system_id": 30003541,
                                        "type_id": 17453
                                    }
                                )
                                observer.complete()
                            })
                        }
                    }
                }
            ],
        }).compile();

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
                expect(operation.id).toBe('2024-02-23-30003541-17453')
            })
        })
        test('when the endpoint is called then we get a single mining operation', () => {
            const pilotId: number = 321
            const sut: Promise<V2MiningOperation[]> = esiAdapter.miningOperations.getMiningOperations(pilotId)
            expect(sut).toBeDefined()
            sut.then(data => {
                expect(data.length).toBe(1)
                expect(data[0] instanceof V2MiningOperation)
                const operation = data[0]
                expect(operation.id).toBe('2024-02-23-30003541-17453')
            })
        })
    })
})
