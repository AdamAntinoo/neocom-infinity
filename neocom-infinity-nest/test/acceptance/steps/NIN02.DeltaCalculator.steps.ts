import { Before, Given, Then, When } from '@cucumber/cucumber';
import { NeoComWorld } from '../neocom-world';
import { AssetEsi } from '../../../src/application/domain/asset-esi';
import { DeltaCalculator } from '../../../src/application/dmos/delta-calculator';
import { expect } from 'expect';

Given('a base asset list of type {string}', function (type: string) {
  console.info('type:' + type);
  let initialList = []
  switch (type) {
    case 'A':{
      this.initialList = [
        new AssetEsi({
          is_singleton: false,
          item_id: 100001,
          location_flag: 'AutoFit',
          location_id: 1035124094434,
          location_type: 'item',
          quantity: 1000,
          type_id: 101,
        }),
        new AssetEsi({
          is_singleton: false,
          item_id: 100002,
          location_flag: 'AutoFit',
          location_id: 1035124094434,
          location_type: 'item',
          quantity: 1000,
          type_id: 102,
        }),
        new AssetEsi({
          is_singleton: false,
          item_id: 100003,
          location_flag: 'AutoFit',
          location_id: 1035124094434,
          location_type: 'item',
          quantity: 1000,
          type_id: 103,
        }),
      ];
    }
      break;
  }
});

Given('a new asset list of type {string}', function (type: string) {
  console.info('type:' + type);
  switch (type) {
    case 'A': {
      this.secondList = [
        new AssetEsi({
          is_singleton: false,
          item_id: 100001,
          location_flag: 'AutoFit',
          location_id: 1035124094434,
          location_type: 'item',
          quantity: 1000,
          type_id: 101,
        }),
        new AssetEsi({
          is_singleton: false,
          item_id: 100002,
          location_flag: 'AutoFit',
          location_id: 1035124094434,
          location_type: 'item',
          quantity: 1000,
          type_id: 102,
        }),
        new AssetEsi({
          is_singleton: false,
          item_id: 100003,
          location_flag: 'AutoFit',
          location_id: 1035124094434,
          location_type: 'item',
          quantity: 1000,
          type_id: 103,
        }),
      ];
      break;
    }
      case 'B': {
        this.secondList = [
          new AssetEsi({
            is_singleton: false,
            item_id: 100004,
            location_flag: 'AutoFit',
            location_id: 1035124094434,
            location_type: 'item',
            quantity: 2000,
            type_id: 101,
          }),
          new AssetEsi({
            is_singleton: false,
            item_id: 100005,
            location_flag: 'AutoFit',
            location_id: 1035124094434,
            location_type: 'item',
            quantity: 1000,
            type_id: 102,
          }),
          new AssetEsi({
            is_singleton: false,
            item_id: 100006,
            location_flag: 'AutoFit',
            location_id: 1035124094434,
            location_type: 'item',
            quantity: 1000,
            type_id: 103,
          }),
        ];
        break;
      }
      case 'C': {
        this.secondList = [
          new AssetEsi({
            is_singleton: false,
            item_id: 100001,
            location_flag: 'AutoFit',
            location_id: 1035124094434,
            location_type: 'item',
            quantity: 1000,
            type_id: 101,
          }),
          new AssetEsi({
            is_singleton: false,
            item_id: 100002,
            location_flag: 'AutoFit',
            location_id: 1035124094434,
            location_type: 'item',
            quantity: 2000,
            type_id: 102,
          }),
          new AssetEsi({
            is_singleton: false,
            item_id: 100003,
            location_flag: 'AutoFit',
            location_id: 1035124094434,
            location_type: 'item',
            quantity: 3000,
            type_id: 103,
          }),
        ];
        break;
      }
     }
});

When('both list are entered to the Delta Calculator', function (this: NeoComWorld,) {
  this.deltaCalculator=new DeltaCalculator();
  this.output = this.deltaCalculator.apply(this.initialList, this.secondList);
  console.info('output:' + JSON.stringify(this.output));
});

Then('the output asset list has {int} assets', function (size: number) {
  console.info('output:' + JSON.stringify(this.output));
  expect(this.output.length).toBe(size);
});

Then('the asset list returned has the next contents', function (dataTable) {
  for (let index = 0; index < dataTable.length; index++) {
    const element = dataTable[index];
    let asset: AssetEsi = this.output[parseInt(element.position) - 1];
    expect(asset).toBeDefined();
    expect(asset.item_id).toBe(parseInt(element.id))
    expect(asset.quantity).toBe(parseInt(element.quantity))
  }
});
Then('the asset at position {int} has the next values', function (position: number, dataTable) {
  const element = dataTable[position - 1];
  let asset: AssetEsi = this.output[position - 1];
  expect(asset).toBeDefined();
  expect(asset.item_id).toBe(parseInt(element.id))
  expect(asset.quantity).toBe(parseInt(element.quantity))
});
