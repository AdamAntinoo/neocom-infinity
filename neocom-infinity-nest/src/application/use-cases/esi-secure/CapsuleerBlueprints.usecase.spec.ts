import { HttpService } from '@nestjs/axios'
import { Test } from '@nestjs/testing'
import { ESIDataServicesPort } from '@App/ports/ESIDataServices.port'
import { ConfigService } from '@nestjs/config'
import { V1ESISecureDataAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/V1.EsiSecureData.adapter'
import { CapsuleerBlueprintsUseCase, CapsuleerBlueprintsUseCaseInput } from './CapsuleerBlueprints.usecase'
import { GetCharactersCharacterIdBlueprints200Ok, V1BlueprintDto } from 'neocom-domain'
import { Observable } from 'rxjs'

describe('USECASE CapsuleerBlueprintsUseCaseInput [Module: Application.UseCases]', () => {
	let useCase: CapsuleerBlueprintsUseCase
	let dataServices: ESIDataServicesPort

	beforeEach(async () => {
		const moduleRef = await Test.createTestingModule({
			providers: [
				ConfigService,
				{ provide: ESIDataServicesPort, useClass: V1ESISecureDataAdapter },
				{
					provide: HttpService, useValue: {
						get: (request: string) => {
							console.log('method->' + 'GET')
							console.log('request->' + request)
							return new Observable(observer => {
								observer.next()
							})
						},
					}
				}]
		}).compile()

		dataServices = await moduleRef.resolve(ESIDataServicesPort)
		useCase = new CapsuleerBlueprintsUseCase(dataServices)
	})

	describe('constructor contract', () => {
		test('should be created', () => {
			expect(useCase).toBeDefined()
		})
	})
	describe('endpoints phase', () => {
		test('when request for assets then return a list of character assets', async () => {
			expect(useCase).toBeDefined()
			const input: CapsuleerBlueprintsUseCaseInput = {
				jwt: '-jwt-token-',
				token: {
					scp: [''],
					sub: 'string',
					tenant: 'string',
					name: 'string',
				},
				capsuleerId: 32,
			}
			const assets = new Promise<GetCharactersCharacterIdBlueprints200Ok[]>(resolve => {
				resolve([
					{
						item_id: 1004406235971,
						location_flag: 'Hangar',
						location_id: 60006907,
						material_efficiency: 10,
						quantity: -1,
						runs: -1,
						time_efficiency: 20,
						type_id: 785,
					},
					{
						item_id: 1005458604009,
						location_flag: 'Hangar',
						location_id: 60006907,
						material_efficiency: 10,
						quantity: -1,
						runs: -1,
						time_efficiency: 20,
						type_id: 31741,
					},
				])
			})
			jest.spyOn(dataServices, 'getCharactersCharacterIdBlueprints').mockImplementation(() => assets)

			const sut: Promise<V1BlueprintDto[]> = useCase.getBlueprints(input)
			expect(sut).toBeDefined
			await sut.then((response: V1BlueprintDto[]) => {
				expect(response.length).toBe(2)
				expect(response[0]).toBeDefined()
				expect(response[0] instanceof V1BlueprintDto).toBeTruthy()
				expect(response[0].id).toEqual(1004406235971)
				expect(response[0].typeLink).toEqual('/api/v3/neocom/universe/types/785')
				expect(response[0].storageLocation).toBeDefined()
				expect(response[0].materialEfficiency).toEqual(10)
				expect(response[0].timeEfficiency).toEqual(20)
				expect(response[0].runs).toEqual(-1)
				expect(response[0].original).toBeTruthy()
			})
		})
	})
})
