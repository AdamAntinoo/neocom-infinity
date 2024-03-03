import { Test, TestingModule } from "@nestjs/testing";
import { ESIAssetsDataAdapter } from "./esi.assets.adapter";
import { ESISecureDataServiceAdapter } from "../../network/esi.secure.dataservice.adapter";
import { MockESIHttpSecureService } from "../../network/mock.esi.httpsecureservice";
import { AssetEsi } from "../../../domain/dto/ESIAsset.esi";

describe('ADAPTER ESIAssetsDataAdapter [Module: Infrastructure]', () => {
	let httpService: MockESIHttpSecureService;
	let esiAssetAdapter: ESIAssetsDataAdapter;
	beforeEach(async () => {
		// const moduleRef = await Test.createTestingModule({
		// 	controllers: [ESIAssetsDataAdapter],
		// 	providers: [ESIHttpService],
		// }).compile();
		// httpService = moduleRef.get<MockESIHttpService>(MockESIHttpService);
		// esiAssetAdapter = moduleRef.get<ESIAssetsDataAdapter>(ESIAssetsDataAdapter);
		httpService = new MockESIHttpSecureService();
		esiAssetAdapter = new ESIAssetsDataAdapter(httpService);
	});
	describe('constructor contract', () => {
		test('should be created', () => {
			expect(esiAssetAdapter).toBeDefined() // Assets adapter has not bween created.
		});
		test('when the apiEsiCharacterAssetsData is called we get a set of 3 assets', () => {
			const sutPromise: Promise<AssetEsi[]> = esiAssetAdapter.apiEsiCharacterAssetsData(10001);
			console.log("sut->" + JSON.stringify(sutPromise))
			expect(sutPromise).toBeDefined();
			const sut = sutPromise.then((sut) => {
				expect(sut).toBeDefined()
				expect(sut.length).toBe(3)
				expect(validateAsset({

				},
					sut[0])).toBeTruthy()
			})
		});
	});
});
function validateAsset(input: {}, obtained: AssetEsi): any {
	const expected: AssetEsi = new AssetEsi(input)
	return (expected.is_singleton == obtained.is_singleton)
	// public item_id: number;
	// public location_flag: string;
	// public location_id: number;
	// public location_type: string;
	// public quantity: number;
	// public type_id: number;
}

