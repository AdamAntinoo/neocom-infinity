import { Test } from '@nestjs/testing'
import { CapsuleerMiningOperationsUseCase, MiningOperationsUseCaseInput } from '@Application/use-cases/esisecure/CapsuleerMiningOperationsUseCase'
import { JwtService } from '@nestjs/jwt'
import { NestFactory } from '@nestjs/core'
import { AppModule } from 'app.module'
import { V1MiningOperationDto } from 'neocom-domain/'
import { ESIDataServicesPort } from 'application/ports/EsiDataServices.port'
import { ConfigService } from '@nestjs/config'
import { IEsiMiningSecureService } from 'application/ports/IEsiMiningSecureService.port'
import { HttpModule } from '@nestjs/axios'
import { V1IndustryController } from './V1.Industry.controller'

describe('CONTROLLER V1MiningOperationsController [Module: Infrastructure.Adapters]', () => {
	let appModule: any
	let controller: V1IndustryController
	let useCase: CapsuleerMiningOperationsUseCase
	let jwtService: JwtService

	beforeEach(async () => {
		appModule = await NestFactory.create(AppModule)
		useCase = appModule.get(CapsuleerMiningOperationsUseCase)
		jwtService = appModule.get(JwtService)

		controller = new V1IndustryController(useCase, jwtService)
	})
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
				{ provide: IEsiMiningSecureService, useClass: V1MiningOperationsAdapter },
				{ provide: ESIDataServicesPort, useClass: ESISecureDataServicesAdapter },
			],
		}).compile()

		useCase = moduleRef.get<CapsuleerMiningOperationsUseCase>(CapsuleerMiningOperationsUseCase)
		jwtService = moduleRef.get<JwtService>(JwtService)
		controller = moduleRef.get<V1IndustryController>(V1IndustryController)
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
			const token: string =
				'eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1pbmR1c3RyeS5yZWFkX2NoYXJhY3Rlcl9taW5pbmcudjEiXSwianRpIjoiY2NhZWFmNjctNjM2ZS00ZDhhLThiNDQtZThlNzViM2ExMjE2Iiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzExNTMwMjExLCJpYXQiOjE3MTE1MjkwMTEsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.cVkBkCgLir-kzahfqxjhjNMmQoks1xbt0zthSvWt0Ynuv-rJhI25m4SGReMJSvnjyseh9bmyblJbXOYEJeF_zdbuyP-KRwshWj4hre-VZ4jJPf4Rl-QcdRxPJ-2hPk-w06ltuDCwWUmaCedQauXg9tHKnM8KGapZ64OENaEKbY4A4ilAS0Iukaz9HqqXEuW7rcGAKXvN27yguF2U_hoN3QzCzGcOB0sLyiW1lpjpOC-vO-1X9nc-RUJGK4bYxoVMtxUL1bBrbBmpp2Rb1A43bpsgNGWQQc-PSzasWw2sNX90oYFHKCGQ7_dRCsk2cT7xgcdaAGjVZe9yaIHTg4Ogzg'

			expect(await controller.getMiningOperations(token)).toBe(mockOperationsList)
		})
		test('when a request without cookie then we get an error', async () => {
			expect(controller).toBeDefined()
			const mockOperationsList: V1MiningOperationDto[] = []
			const operations = new Promise<V1MiningOperationDto[]>(resolve => {
				resolve(mockOperationsList)
			})
			jest.spyOn(useCase, 'getMiningOperations').mockImplementation(() => operations)
			const expected = 'The authentication token is not present or invalid so the request is not authorized.'
			try {
				await controller.getMiningOperations(null)
			} catch (e) {
				expect(e.message).toEqual(expected)
			}
		})
	})
})
