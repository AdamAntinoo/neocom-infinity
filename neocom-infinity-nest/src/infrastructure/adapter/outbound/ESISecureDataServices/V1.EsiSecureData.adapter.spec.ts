import { ESIDataServicesPort } from '@App/ports/v1ESIDataServices.port'
import { HttpService } from '@nestjs/axios'
import { ConfigService } from '@nestjs/config'
import { Test } from '@nestjs/testing'
import { Observable } from 'rxjs'
import { V1ESISecureDataAdapter } from './V1.EsiSecureData.adapter'
import { GetCharactersCharacterIdMining200Ok } from 'neocom-domain'

describe('SERVICE ESIMiningSecureService [Module: Infrastructure.service]', () => {
	let httpService: HttpService
	let configure: ConfigService
	let esiSecureService: ESIDataServicesPort

	beforeEach(async () => {
		const moduleRef = await Test.createTestingModule({
			providers: [
				ConfigService,
				{ provide: ESIDataServicesPort, useClass: V1ESISecureDataAdapter },
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
											date: '2024-02-23',
											quantity: 210,
											solar_system_id: 30003541,
											type_id: 17453,
										},
									],
								})
							})
						},
					},
				},
			],
		}).compile()

		httpService = await moduleRef.resolve(HttpService)
		configure = await moduleRef.resolve(ConfigService)
		esiSecureService = new V1ESISecureDataAdapter(httpService, configure)
	})

	describe('constructor contract phase', () => {
		test('should be created', () => {
			expect(esiSecureService).toBeDefined()
		})
	})
	describe('functionality phase', () => {
		test('when the endpoint is called then we get a list of mining actions', () => {
			const pilotId: number = 321
			const token: string = ''
			const sut: Promise<GetCharactersCharacterIdMining200Ok[]> = esiSecureService.getMiningOperations(pilotId, token)
			expect(sut).toBeDefined()
			sut.then(data => {
				expect(data.length).toBe(1)
				const operation = data[0]
				console.log('operation->' + JSON.stringify(operation))
				expect(operation.date).toBe('2024-02-23')
				expect(operation.quantity).toBe(210)
				expect(operation.solar_system_id).toEqual(30003541)
				expect(operation.type_id).toEqual(17453)
			})
		})
	})
})
