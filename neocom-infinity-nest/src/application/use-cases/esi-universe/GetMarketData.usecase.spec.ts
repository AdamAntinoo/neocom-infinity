import { Test } from '@nestjs/testing'
import { ESIDataUniverseServicesPort } from 'application/ports/ESIDataUniverseServices.port'
import { V1MarketDataDto } from 'neocom-domain'
import { EsiDataUniverseMock } from 'mocks/EsiDataUniverse.mock'
import { GetMarketDataUseCase, GetMarketDataUseCaseInput } from './GetMarketData.usecase'

describe('USECASE CapsuleerMiningOperationsUseCase [Module: Application.UseCases]', () => {
	let useCase: GetMarketDataUseCase
	let dataServices: ESIDataUniverseServicesPort

	beforeEach(async () => {
		const moduleRef = await Test.createTestingModule({
			providers: [{ provide: ESIDataUniverseServicesPort, useClass: EsiDataUniverseMock }],
		}).compile()

		dataServices = await moduleRef.resolve(ESIDataUniverseServicesPort)
		useCase = new GetMarketDataUseCase(dataServices)
	})

	describe('constructor contract', () => {
		test('should be created', () => {
			expect(useCase).toBeDefined()
		})
	})
	describe('endpoints phase', () => {
		test('when request for market data', async () => {
			expect(useCase).toBeDefined()
			const input: GetMarketDataUseCaseInput = {
				typeId: 17464,
				systemId: 30000142,
			}
			const sut: Promise<V1MarketDataDto> = useCase.esiGetMarketData(input)
			expect(sut).toBeDefined()
			await sut.then((response: V1MarketDataDto) => {
				expect(response.buyPrice).toBe(13.86)
				expect(response.buyOrderCount).toBe(5)
				expect(response.sellPrice).toBe(16.0)
				expect(response.sellOrderCount).toBe(18)
			})
		})
	})
})
