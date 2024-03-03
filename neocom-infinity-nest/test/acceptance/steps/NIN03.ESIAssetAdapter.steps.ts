import { Before, Given, Then, When } from '@cucumber/cucumber';
import { expect } from 'expect';
import { NeoComWorld } from '../neocom-world';
import { AssetEsi } from '../../../src/application/domain/asset.esi';
import { PilotCard } from '../../../src/application/domain/pilot.card';
import { ESIAssetsAdapter } from '../../../src/infrastructure/adapter/outbound/esi.assets.adapter';

Given('pilot information card with the next data', function (dataTable) {
  const row = dataTable.hashes()[0]
  this.pilotCard = new PilotCard();
  expect(this.pilotCard).toBeDefined();
  this.pilotCard.setPilotId(row.pilotId);
});

When('the AssetAdapter {string} endpoint is called', function (string) {
  this.assetAdapter=new ESIAssetsAdapter();
  const sut : = this.assetAdapter.getCharacterAssets();
});
