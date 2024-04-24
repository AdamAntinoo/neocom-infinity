import { CapsuleerMiningOperationsUseCase, MiningOperationsUseCaseInput } from './CapsuleerMiningOperationsUseCase'
import { HttpService } from '@nestjs/axios'
import { Observable } from 'rxjs'
import { Test } from '@nestjs/testing'
import { ESIDataServicesPort } from 'application/ports/EsiDataServices.port'
import { ESIMiningSecureService } from '@Infra/adapter/outbound/ESISecureDataServices/esi.mining.secure.adapter'
import { ESISecureDataServicesAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservices.adapter'
import { IEsiMiningSecureService } from 'application/ports/IEsiMiningSecureService.port'
import { V1MiningOperationDto } from 'neocom-domain'
import { ConfigService } from '@nestjs/config'
import { GetCharactersCharacterIdMining200Ok } from 'application/domain/esi-model/getCharactersCharacterIdMining200Ok'

describe('USECASE CapsuleerMiningOperationsUseCase [Module: Application.UseCases]', () => {
	let useCase: CapsuleerMiningOperationsUseCase
	let dataServices: ESISecureDataServicesAdapter

	beforeEach(async () => {
		const moduleRef = await Test.createTestingModule({
			providers: [
				ConfigService,
				{ provide: ESIDataServicesPort, useClass: ESISecureDataServicesAdapter },
				{ provide: IEsiMiningSecureService, useClass: ESIMiningSecureService },
				{ provide: CapsuleerMiningOperationsUseCase, useClass: CapsuleerMiningOperationsUseCase },
				{
					provide: HttpService,
					useValue: {
						get: (request: string) => {
							console.log('method->' + 'GET')
							console.log('request->' + request)
							return new Observable(observer => {
								observer.next({
									data: [
										{
											date: '2024-02-25',
											quantity: 10000,
											solar_system_id: 30003538,
											type_id: 17464,
										},
										{
											date: '2024-02-25',
											quantity: 12000,
											solar_system_id: 30003538,
											type_id: 1224,
										},
										{
											date: '2024-02-23',
											quantity: 210,
											solar_system_id: 30003541,
											type_id: 17453,
										},
										{
											date: '2024-02-23',
											quantity: 34243,
											solar_system_id: 30003538,
											type_id: 17464,
										},
										{
											date: '2024-02-23',
											quantity: 3073,
											solar_system_id: 30003538,
											type_id: 1224,
										},
										{
											date: '2024-02-23',
											quantity: 10288,
											solar_system_id: 30003538,
											type_id: 17459,
										},
									],
								})
							})
						},
					},
				},
			],
		}).compile()

		dataServices = await moduleRef.resolve(ESIDataServicesPort)
		useCase = new CapsuleerMiningOperationsUseCase(dataServices)
	})

	describe('constructor contract', () => {
		test('should be created', () => {
			expect(useCase).toBeDefined()
		})
	})
	describe('endpoints phase', () => {
		test('when request for mining operations then return a list of aggregated operations', async () => {
			expect(useCase).toBeDefined()
			const input: MiningOperationsUseCaseInput = {
				jwt: '-jwt-token-',
				token: {
					scp: [''],
					sub: 'string',
					tenant: 'string',
					name: 'string',
				},
				capsuleerId: 32,
			}
			const service = dataServices.miningOperations
			const operations = new Promise<GetCharactersCharacterIdMining200Ok[]>(resolve => {
				resolve([
					{
						date: '2024-02-25',
						quantity: 10000,
						solar_system_id: 30003538,
						type_id: 17464,
					},
					{
						date: '2024-02-25',
						quantity: 12000,
						solar_system_id: 30003538,
						type_id: 1224,
					},
					{
						date: '2024-02-23',
						quantity: 210,
						solar_system_id: 30003541,
						type_id: 17453,
					},
					{
						date: '2024-02-23',
						quantity: 34243,
						solar_system_id: 30003538,
						type_id: 17464,
					},
					{
						date: '2024-02-23',
						quantity: 3073,
						solar_system_id: 30003538,
						type_id: 1224,
					},
					{
						date: '2024-02-23',
						quantity: 10288,
						solar_system_id: 30003538,
						type_id: 17459,
					},
				])
			})
			jest.spyOn(service, 'getMiningOperations').mockImplementation(() => operations)

			const sut: Promise<V1MiningOperationDto[]> = useCase.getMiningOperations(input)
			expect(sut).toBeDefined
			await sut.then((response: V1MiningOperationDto[]) => {
				expect(response.length).toBe(3)
				expect(response[0]).toBeDefined()
				expect(response[0] instanceof V1MiningOperationDto).toBeTruthy()
				expect(response[0].id).toEqual('2024-02-25/30003538')
				expect(response[0].date).toEqual('2024-02-25')
				expect(response[0].solarSystemLink).toEqual('/esi/v1/universe/spacelocation/30003538')
				expect(response[0].getResources().length).toBe(2)
			})
		})
	})
})
