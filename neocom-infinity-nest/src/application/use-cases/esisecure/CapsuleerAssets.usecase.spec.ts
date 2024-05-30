import { HttpService } from '@nestjs/axios'
import { Observable } from 'rxjs'
import { Test } from '@nestjs/testing'
import { ESIDataServicesPort } from '@App/ports/ESIDataServices.port'
import { GetCharactersCharacterIdAssets200Ok, V1AssetDto, V1StackDto } from 'neocom-domain'
import { ConfigService } from '@nestjs/config'
import { V1ESISecureDataAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/V1.EsiSecureData.adapter'
import { CapsuleerAssetsUseCase, CapsuleerAssetsUseCaseInput } from './CapsuleerAssets.usecase'

describe('USECASE CapsuleerAssetsUseCase [Module: Application.UseCases]', () => {
	let useCase: CapsuleerAssetsUseCase
	let dataServices: ESIDataServicesPort

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
								observer.next()
							})
						},
					},
				},
			],
		}).compile()

		dataServices = await moduleRef.resolve(ESIDataServicesPort)
		useCase = new CapsuleerAssetsUseCase(dataServices)
	})

	describe('constructor contract', () => {
		test('should be created', () => {
			expect(useCase).toBeDefined()
		})
	})
	describe('endpoints phase', () => {
		test('when request for assets then return a list of character assets', async () => {
			expect(useCase).toBeDefined()
			const input: CapsuleerAssetsUseCaseInput = {
				jwt: '-jwt-token-',
				token: {
					scp: [''],
					sub: 'string',
					tenant: 'string',
					name: 'string',
				},
				capsuleerId: 32,
			}
			const assets = new Promise<GetCharactersCharacterIdAssets200Ok[]>(resolve => {
				resolve([
					{
						is_blueprint_copy: true,
						is_singleton: true,
						item_id: 1012139393152,
						location_flag: 'AutoFit',
						location_id: 1010848366291,
						location_type: 'item',
						quantity: 1,
						type_id: 1540,
					},
					{
						is_blueprint_copy: true,
						is_singleton: true,
						item_id: 1015046088204,
						location_flag: 'AutoFit',
						location_id: 1010848366291,
						location_type: 'item',
						quantity: 1,
						type_id: 1245,
					},
					{
						is_blueprint_copy: true,
						is_singleton: true,
						item_id: 1015138739159,
						location_flag: 'AutoFit',
						location_id: 1010848366291,
						location_type: 'item',
						quantity: 1,
						type_id: 11613,
					},
				])
			})
			jest.spyOn(dataServices, 'getCharactersCharacterIdAssets').mockImplementation(() => assets)

			const sut: Promise<V1AssetDto[]> = useCase.getAssets(input)
			expect(sut).toBeDefined
			await sut.then((response: V1AssetDto[]) => {
				expect(response.length).toBe(3)
				expect(response[0]).toBeDefined()
				expect(response[0] instanceof V1AssetDto).toBeTruthy()
				expect(response[0].id).toEqual(1012139393152)
				expect(response[0].locationLink).toEqual('/esi/v1/universe/spacelocation/1010848366291')
				expect(response[0].resource instanceof V1StackDto).toBeTruthy()
				expect(response[0].resource).toBeDefined()
				expect(response[0].resource.jsonClass).toEqual('StackDto')
				expect(response[0].resource.quantity).toEqual(1)
				expect(response[0].resource.typeLink).toEqual('/api/v3/neocom/universe/types/1540')
			})
		})
	})
})
