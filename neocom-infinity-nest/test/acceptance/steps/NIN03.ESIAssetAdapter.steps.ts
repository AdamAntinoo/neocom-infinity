import { Before, Given, Then, When } from '@cucumber/cucumber';
import { expect } from 'expect';
import { NeoComWorld } from '../neocom-world';
import { PilotCard } from '../../../src/application/domain/pilot.card';
import { ESIAssetsDataAdapter } from '../../../src/infrastructure/adapter/outbound/esi.assets.adapter';
import { MockESIHttpSecureService } from '../../../src/infrastructure/network/mock.esi.httpsecureservice';
import { HttpSecureServiceInterface } from '../../../src/infrastructure/network/http.secure.service.interface';
import { AssetEsi } from '../../../src/domain/dto/ESIAsset.esi';

Given('pilot information card with the next data', function (dataTable) {
  const row = dataTable.hashes()[0];
  this.pilotCard = new PilotCard();
  expect(this.pilotCard).toBeDefined();
  this.pilotCard.setPilotId(row.pilotId);
});

When(
  'the AssetAdapter {string} endpoint is called',
  function (endpoint: string) {
    this.httpSecureService = new MockESIHttpSecureService();
    this.assetAdapter = new ESIAssetsDataAdapter(this.httpSecureService);
    expect(this.assetAdapter).toBeDefined();
    switch (endpoint) {
      case 'character/assets': {
        console.log('enter->characterAssetsEndpoint');
        const sut: Promise<AssetEsi[]> =
          this.assetAdapter.apiEsiCharacterAssetsData(1001);
        expect(sut).toBeDefined();
        this.characterAssetsResponse = sut;
      }
    }
  },
);
Then('the number os assets downloaded is {int}', function (assetCount: number) {
  expect(this.characterAssetsResponse).toBeDefined();
  this.characterAssetsResponse.then((assetlist) => {
    expect(assetlist).toBeDefined();
    this.assetlist = assetlist;
    expect(assetlist.length).toBe(3);
  });
});
Then('the asset data downloaded is', function (dataTable) {
  console.log('dataTable->' + JSON.stringify(dataTable));
  console.log('dataTable->' + JSON.stringify(dataTable.hashes()));
  console.log('tableCount->' + dataTable.hashes().length);
  for (let index = 0; index < dataTable.hashes().length; index++) {
    const element = dataTable.hashes()[index];
    const asset = this.assetlist[index];
    console.log('expected->' + JSON.stringify(element));
    console.log('asset->' + JSON.stringify(asset));
    expect(assetMatch(element, asset)).toBeTruthy();
  }
});
function assetMatch(element: any, asset: AssetEsi): any {
  console.log('enter assetMatch->');
  if (parseInt(element.id) != asset.item_id) return false;
  if (parseInt(element.typeId) != asset.type_id) return false;
  if (parseInt(element.quantity) != asset.quantity) return false;
  return true;
}
