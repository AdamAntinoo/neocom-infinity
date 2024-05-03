import { ConfigService } from '@nestjs/config'
import { MiningOperationsTypedRequest } from './MiningOperations.typedrequest'
import { Test } from '@nestjs/testing'

describe('SERVICE MiningOperationsTypedRequest [Module: Infrastructure.adapter]', () => {
	let configure: ConfigService
	// let typedRequest: MiningOperationsTypedRequest

	beforeEach(async () => {
		const moduleRef = await Test.createTestingModule({
			providers: [{ provide: ConfigService, useClass: ConfigService }],
		}).compile()

		configure = moduleRef.get<ConfigService>(ConfigService)
		// typedRequest = new MiningOperationsTypedRequest(httpService, configure)
	})

	describe('constructor contract phase', () => {
		test('should be created', () => {
			const typedRequest: MiningOperationsTypedRequest = new MiningOperationsTypedRequest(
				{
					characterId: 1000,
					token: '-TOKEN-',
				},
				configure,
			)
			expect(typedRequest).toBeDefined()
		})
	})
})
