import { Test } from '@nestjs/testing'
import { CapsuleerMiningOperationsUseCase } from '@App/use-cases/esi-secure/CapsuleerMiningOperationsUseCase'
import { JwtService } from '@nestjs/jwt'
import { V1MiningOperationDto } from 'neocom-domain/'
import { ESIDataServicesPort } from '@App/ports/v1ESIDataServices.port'
import { ConfigService } from '@nestjs/config'
import { HttpModule } from '@nestjs/axios'
import { V1IndustryController } from './V1.Industry.controller'
import { V1ESISecureDataAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/V1.EsiSecureData.adapter'

describe('CONTROLLER V1MiningOperationsController [Module: Infrastructure.Adapters]', () => {
	let controller: V1IndustryController
	let useCase: CapsuleerMiningOperationsUseCase

	beforeEach(async () => {
		const moduleRef = await Test.createTestingModule({
			imports: [
				HttpModule.register({
					timeout: 90000, // Add time for debugging.
					maxRedirects: 5,
					headers: { 'X-Requested-With': 'XMLHttpRequest' },
					withCredentials: true,
				}),
			],
			controllers: [V1IndustryController],
			providers: [
				ConfigService,
				CapsuleerMiningOperationsUseCase,
				JwtService,
				{ provide: ESIDataServicesPort, useClass: V1ESISecureDataAdapter },
			],
		}).compile()

		useCase = await moduleRef.resolve(CapsuleerMiningOperationsUseCase)
		controller = await moduleRef.resolve(V1IndustryController)
	})

	describe('Constructor contract phase', () => {
		test('should be defined', () => {
			expect(controller).toBeDefined()
		})
	})
	describe('Endpoints phase', () => {
		test('when a request with cookie then we get a valid response', async () => {
			expect(controller).toBeDefined()
			const mockOperationsList: V1MiningOperationDto[] = []
			const operations = new Promise<V1MiningOperationDto[]>(resolve => {
				resolve(mockOperationsList)
			})
			jest.spyOn(useCase, 'getMiningOperations').mockImplementation(() => operations)
			const token: string =
				'eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1pbmR1c3RyeS5yZWFkX2NoYXJhY3Rlcl9taW5pbmcudjEiXSwianRpIjoiY2NhZWFmNjctNjM2ZS00ZDhhLThiNDQtZThlNzViM2ExMjE2Iiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzExNTMwMjExLCJpYXQiOjE3MTE1MjkwMTEsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.cVkBkCgLir-kzahfqxjhjNMmQoks1xbt0zthSvWt0Ynuv-rJhI25m4SGReMJSvnjyseh9bmyblJbXOYEJeF_zdbuyP-KRwshWj4hre-VZ4jJPf4Rl-QcdRxPJ-2hPk-w06ltuDCwWUmaCedQauXg9tHKnM8KGapZ64OENaEKbY4A4ilAS0Iukaz9HqqXEuW7rcGAKXvN27yguF2U_hoN3QzCzGcOB0sLyiW1lpjpOC-vO-1X9nc-RUJGK4bYxoVMtxUL1bBrbBmpp2Rb1A43bpsgNGWQQc-PSzasWw2sNX90oYFHKCGQ7_dRCsk2cT7xgcdaAGjVZe9yaIHTg4Ogzg'
			const sessionId = '-test-session-id-'
			expect(await controller.getMiningOperations4Pilot(token, sessionId)).toBe(mockOperationsList)
		})
		test('when a request without cookie then we get an error', async () => {
			expect(controller).toBeDefined()
			const mockOperationsList: V1MiningOperationDto[] = []
			const operations = new Promise<V1MiningOperationDto[]>(resolve => {
				resolve(mockOperationsList)
			})
			jest.spyOn(useCase, 'getMiningOperations').mockImplementation(() => operations)
			const expected = 'The authentication token is not present or invalid so the request is not authorized.'
			const sessionId = '-test-session-id-'
			try {
				await controller.getMiningOperations4Pilot(null, sessionId)
			} catch (e) {
				expect(e.message).toEqual(expected)
			}
		})
	})
})
