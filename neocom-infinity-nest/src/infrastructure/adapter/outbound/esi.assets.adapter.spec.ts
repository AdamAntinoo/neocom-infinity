import { Test, TestingModule } from "@nestjs/testing"
import { HttpService } from '@nestjs/axios'
import { plainToInstance } from 'class-transformer'
import { ESIAssetsDataAdapter } from "./esi.assets.adapter"
import { ESIHttpService } from "../../network/esi.httpservice"

describe('ADAPTER ESIAssetsDataAdapter [Module: Infrastructure]', () => {
	let httpService: ESIHttpService;
	let esiAssetAdapter: ESIAssetsDataAdapter;
	beforeEach(async () => {
		const moduleRef = await Test.createTestingModule({
			controllers: [ESIAssetsDataAdapter],
			providers: [ESIHttpService],
		}).compile();
		httpService = moduleRef.get<ESIHttpService>(ESIHttpService);
		esiAssetAdapter = moduleRef.get<ESIAssetsDataAdapter>(ESIAssetsDataAdapter);
	});
	describe('constructor contract', () => {
		test('should be created', () => {
			expect(esiAssetAdapter).toBeDefined() // Assets adapter has not bween created.
		})
	})
})
