import { Test, TestingModule } from '@nestjs/testing';
import { V1MiningOperationsController } from './v1.miningoperations.controller';
import { CapsuleerMiningOperationsUseCase, MiningOperationsUseCaseInput } from 'application/use-cases/mining-operation/CapsuleerMiningOperationsUseCase';
import { JwtService } from '@nestjs/jwt';
import { NestFactory } from '@nestjs/core';
import { AppModule } from 'app.module';
import { V1MiningOperationDto } from 'neocom-domain/dto/V1MiningOperationDto.dto';
import { ESIDataServicesPort } from 'application/ports/EsiDataServices.port';
import { ESISecureDataServicesAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservices.adapter';
import { ESISecureDataServiceHALGeneratorAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservice.halgenerator.adapter';
import { ConfigService } from '@nestjs/config';
import { IEsiMiningSecureService } from 'application/ports/IEsiMiningSecureService.port';
import { ESIMiningSecureService } from '@Infra/adapter/outbound/ESISecureDataServices/esi.mining.secure.adapter';
import { HttpModule, HttpService } from '@nestjs/axios';

describe('CONTROLLER V1MiningOperationsController [Module: Infrastructure.Adapters]', () => {
    let appModule: any
    let controller: V1MiningOperationsController
    let useCase: CapsuleerMiningOperationsUseCase
    let jwtService: JwtService

    beforeEach(async () => {
        appModule = await NestFactory.create(AppModule)
        useCase = appModule.get(CapsuleerMiningOperationsUseCase)
        jwtService = appModule.get(JwtService)

        controller = new V1MiningOperationsController(useCase, jwtService)
    })
    beforeEach(async () => {
        const moduleRef = await Test.createTestingModule({
            imports: [
                HttpModule.register({
                    timeout: 90000, // Add time for debugging.
                    maxRedirects: 5,
                    headers: { 'X-Requested-With': 'XMLHttpRequest' },
                    withCredentials: true
                }),],
            controllers: [V1MiningOperationsController],
            providers: [
                ConfigService, CapsuleerMiningOperationsUseCase, JwtService,
                { provide: IEsiMiningSecureService, useClass: ESIMiningSecureService },
                { provide: ESIDataServicesPort, useClass: ESISecureDataServicesAdapter },
                { provide: ESISecureDataServiceHALGeneratorAdapter, useClass: ESISecureDataServiceHALGeneratorAdapter }
            ],
        }).compile();

        useCase = moduleRef.get<CapsuleerMiningOperationsUseCase>(CapsuleerMiningOperationsUseCase);
        jwtService = moduleRef.get<JwtService>(JwtService);
        controller = moduleRef.get<V1MiningOperationsController>(V1MiningOperationsController);
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
            const operations = new Promise<V1MiningOperationDto[]>((resolve) => {
                resolve(mockOperationsList)
            })
            jest.spyOn(useCase, 'getMiningOperations').mockImplementation(() => operations)
            const input: MiningOperationsUseCaseInput = {
                jwt: '-jwt-token-',
                token: {
                    scp: [''],
                    sub: 'string',
                    tenant: 'string',
                    name: 'string'
                },
                capsuleerId: 32
            }
            const token: string = 'Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1pbmR1c3RyeS5yZWFkX2NoYXJhY3Rlcl9taW5pbmcudjEiXSwianRpIjoiY2NhZWFmNjctNjM2ZS00ZDhhLThiNDQtZThlNzViM2ExMjE2Iiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzExNTMwMjExLCJpYXQiOjE3MTE1MjkwMTEsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.cVkBkCgLir-kzahfqxjhjNMmQoks1xbt0zthSvWt0Ynuv-rJhI25m4SGReMJSvnjyseh9bmyblJbXOYEJeF_zdbuyP-KRwshWj4hre-VZ4jJPf4Rl-QcdRxPJ-2hPk-w06ltuDCwWUmaCedQauXg9tHKnM8KGapZ64OENaEKbY4A4ilAS0Iukaz9HqqXEuW7rcGAKXvN27yguF2U_hoN3QzCzGcOB0sLyiW1lpjpOC-vO-1X9nc-RUJGK4bYxoVMtxUL1bBrbBmpp2Rb1A43bpsgNGWQQc-PSzasWw2sNX90oYFHKCGQ7_dRCsk2cT7xgcdaAGjVZe9yaIHTg4Ogzg'

            expect(await controller.getMiningOperations(token)).toBe(mockOperationsList)
        })
        test('when a request without cookie then we get an error', async () => {
            expect(controller).toBeDefined()
            const mockOperationsList: V1MiningOperationDto[] = []
            const operations = new Promise<V1MiningOperationDto[]>((resolve) => {
                resolve(mockOperationsList)
            })
            jest.spyOn(useCase, 'getMiningOperations').mockImplementation(() => operations)
            const input: MiningOperationsUseCaseInput = {
                jwt: '-jwt-token-',
                token: {
                    scp: [''],
                    sub: 'string',
                    tenant: 'string',
                    name: 'string'
                },
                capsuleerId: 32
            }
            const token: string = 'Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1pbmR1c3RyeS5yZWFkX2NoYXJhY3Rlcl9taW5pbmcudjEiXSwianRpIjoiY2NhZWFmNjctNjM2ZS00ZDhhLThiNDQtZThlNzViM2ExMjE2Iiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzExNTMwMjExLCJpYXQiOjE3MTE1MjkwMTEsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.cVkBkCgLir-kzahfqxjhjNMmQoks1xbt0zthSvWt0Ynuv-rJhI25m4SGReMJSvnjyseh9bmyblJbXOYEJeF_zdbuyP-KRwshWj4hre-VZ4jJPf4Rl-QcdRxPJ-2hPk-w06ltuDCwWUmaCedQauXg9tHKnM8KGapZ64OENaEKbY4A4ilAS0Iukaz9HqqXEuW7rcGAKXvN27yguF2U_hoN3QzCzGcOB0sLyiW1lpjpOC-vO-1X9nc-RUJGK4bYxoVMtxUL1bBrbBmpp2Rb1A43bpsgNGWQQc-PSzasWw2sNX90oYFHKCGQ7_dRCsk2cT7xgcdaAGjVZe9yaIHTg4Ogzg'
            const expected = 'The authentication token is not present or invalid so the request is not authorized.'
            try {
                await controller.getMiningOperations(null)
            } catch (e) {
                expect(e.message).toEqual(expected)
            }
        })
    })
})
