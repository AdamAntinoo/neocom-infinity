import { Test, TestingModule } from '@nestjs/testing';
import { ESIAssetsDataAdapter } from './esi.assets.adapter';
import { ESISecureDataServiceAdapter } from '../../network/esi.secure.dataservice.adapter';
import { MockESIHttpSecureService } from '../../network/mock.esi.httpsecureservice';
import { AssetEsi } from '../../../domain/dto/ESIAsset.esi';

describe('ADAPTER ESIAssetsDataAdapter [Module: Infrastructure]', () => {
  let httpService: MockESIHttpSecureService;
  let esiAssetAdapter: ESIAssetsDataAdapter;
  beforeEach(async () => {
    httpService = new MockESIHttpSecureService();
    esiAssetAdapter = new ESIAssetsDataAdapter(httpService);
  });
  describe('constructor contract', () => {
    test('should be created', () => {
      expect(esiAssetAdapter).toBeDefined(); // Assets adapter has not bween created.
    });
    test('when the apiEsiCharacterAssetsData is called we get a set of 3 assets', () => {
      console.log('create Promise');
      const sutPromise: Promise<AssetEsi[]> =
        esiAssetAdapter.apiEsiCharacterAssetsData(10001);
      expect(sutPromise).toBeDefined();
      sutPromise.then((sut: AssetEsi[]) => {
        console.log('resolve promise');
        console.log('sut->' + JSON.stringify(sut));
        expect(sut).toBeDefined();
        expect(sut.length).toBe(3);
        // expect(validateAsset({}, sut[0])).toBeTruthy();
      });
    });
    test('when the apiEsiCharacterAssetsData is called the first asset has some expected content', () => {
      console.log('create Promise');
      const sutPromise: Promise<AssetEsi[]> =
        esiAssetAdapter.apiEsiCharacterAssetsData(10001);
      expect(sutPromise).toBeDefined();
      sutPromise.then((sut: AssetEsi[]) => {
        console.log('resolve promise');
        console.log('sut->' + JSON.stringify(sut));
        expect(sut).toBeDefined();
        expect(sut.length).toBe(3);
        expect(
          validateAsset(
            new AssetEsi({
							is_singleton: false,
							item_id: 1012512980002,
							location_flag: 'AutoFit',
							location_id: 1035124094434,
							location_type: 'item',
							quantity: 1000,
							type_id: 1404,
						}),
            sut[1],
          ),
        ).toBeTruthy();
      });
    });
  });
});
function validateAsset(expected: AssetEsi, obtained: AssetEsi): any {
  console.log('>>> validateAsset');
  console.log('expected->' + JSON.stringify(expected));
  console.log('obtained->' + JSON.stringify(obtained));
  // const expected: AssetEsi = new AssetEsi(input);
  if (expected.is_singleton != obtained.is_singleton) return false;
  if (expected.item_id != obtained.item_id) return false;
  if (expected.location_flag != obtained.location_flag) return false;
  // public item_id: number;
  // public location_flag: string;
  // public location_id: number;
  // public location_type: string;
  // public quantity: number;
  // public type_id: number;
	return true
}
