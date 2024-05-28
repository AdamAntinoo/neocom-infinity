import { HttpService } from '@nestjs/axios'
import { ConfigModule, ConfigService } from '@nestjs/config'
import { Test } from '@nestjs/testing'
import { ESIDataUniverseAdapter } from './ESIData.universe.adapter'
import { HttpServiceMock } from 'mocks/HttpService.mock'
import {
	GetUniverseTypesTypeIdOk,
	GetUniverseGroupsGroupIdOk,
	GetUniverseCategoriesCategoryIdOk,
	FuzzWorkMarketData,
	MarketData,
	GetUniverseRegionsRegionIdOk,
	GetUniverseConstellationsConstellationIdOk,
	GetUniverseSystemsSystemIdOk,
} from 'neocom-domain'

describe('SERVICE ESIDataUniverseAdapter [Module: Infrastructure.service]', () => {
	let httpService: HttpService
	let configure: ConfigService
	let esiUniverseService: ESIDataUniverseAdapter

	beforeEach(async () => {
		const ENV = process.env.NODE_ENV

		const moduleRef = await Test.createTestingModule({
			imports: [
				ConfigModule.forRoot({
					isGlobal: true,
					envFilePath: '.env',
				}),
			],
			providers: [ConfigService, { provide: HttpService, useClass: HttpServiceMock }],
		}).compile()

		httpService = moduleRef.get<HttpService>(HttpService)
		configure = moduleRef.get<ConfigService>(ConfigService)
		esiUniverseService = new ESIDataUniverseAdapter(httpService, configure)
	})

	describe('constructor contract phase', () => {
		test('should be created', () => {
			expect(esiUniverseService).toBeDefined()
		})
	})
	describe('endpoints phase', () => {
		test('when the esi type endpoint is called then we get a esi type data', async () => {
			const typeId: number = 17464
			const sut: Promise<GetUniverseTypesTypeIdOk> = esiUniverseService.getEsiType(typeId)
			expect(sut).toBeDefined()
			await sut.then(data => {
				expect(data.type_id).toBe(17464)
				expect(data.group_id).toBe(460)
				expect(data.icon_id).toBe(1356)
				expect(data.name).toBe('Massive Scordite')
				expect(data.volume).toBe(0.15)
			})
		})
		test('when the esi group endpoint is called then we get a esi group data', async () => {
			const typeId: number = 17464
			const sut: Promise<GetUniverseGroupsGroupIdOk> = esiUniverseService.getEsiGroup(typeId)
			expect(sut).toBeDefined()
			await sut.then(data => {
				expect(data.group_id).toBe(460)
				expect(data.name).toBe('Scordite')
				expect(data.category_id).toBe(25)
			})
		})
		test('when the esi category endpoint is called then we get a esi category data', async () => {
			const typeId: number = 17464
			const sut: Promise<GetUniverseCategoriesCategoryIdOk> = esiUniverseService.getEsiCategory(typeId)
			expect(sut).toBeDefined()
			await sut.then(data => {
				expect(data.category_id).toBe(25)
				expect(data.name).toBe('Asteroid')
			})
		})
		test('when the fuzzwork service endpoint is called then we get market data', async () => {
			const typeId: number = 17464
			const systemId: number = 30000142
			const sut: Promise<FuzzWorkMarketData> = esiUniverseService.getFuzzWorkMarketData(typeId, systemId)
			expect(sut).toBeDefined()
			await sut.then(data => {
				expect(data[typeId]).toBeDefined()
				const marketData: FuzzWorkMarketData = data[typeId]
				expect(marketData).toBeDefined()
				const buy: MarketData = marketData.buy
				const sell: MarketData = marketData.sell
				expect(buy).toBeDefined()
				expect(sell).toBeDefined()
				expect(Number(buy.max)).toBe(5.95)
				expect(Number(buy.median)).toBe(5.0)
				expect(Number(buy.orderCount)).toBe(52)
				expect(Number(sell.min)).toBe(5.01)
				expect(Number(sell.median)).toBe(6.38)
				expect(Number(sell.orderCount)).toBe(179)
			})
		})
		test('when the location region endpoint is called then we get the si data', async () => {
			const regionId: number = 10000002
			const sut: Promise<GetUniverseRegionsRegionIdOk> = esiUniverseService.getUniverseRegion(regionId)
			expect(sut).toBeDefined()
			await sut.then(data => {
				expect(data.region_id).toBe(10000002)
				expect(data.name).toBe('The Forge')
			})
		})
		test('when the location constellation endpoint is called then we get the si data', async () => {
			const constellationId: number = 20000020
			const sut: Promise<GetUniverseConstellationsConstellationIdOk> = esiUniverseService.getUniverseConstellation(constellationId)
			expect(sut).toBeDefined()
			await sut.then(data => {
				expect(data.constellation_id).toBe(20000020)
				expect(data.name).toBe('Kimotoro')
			})
		})
		test('when the location system endpoint is called then we get the si data', async () => {
			const systemId: number = 30000142
			const sut: Promise<GetUniverseSystemsSystemIdOk> = esiUniverseService.getUniverseSystem(systemId)
			expect(sut).toBeDefined()
			await sut.then(data => {
				expect(data.system_id).toBe(30000142)
				expect(data.name).toBe('Jita')
			})
		})
	})
})
