import { Before, Given, Then, When } from '@cucumber/cucumber';
import { NeoComWorld } from '../neocom-world';
import { AssetEsi } from '../../../src/application/domain/asset-esi';

Given('a base asset list of type {string}', function ( type: string) {
  console.info('type:' + type);
  let initialList =[]
  switch (type) {
    case 'A': {
      console.info('>>> Type 01');
      initialList = [
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
  console.info('initialList:' + initialList);
  // console.info('secondList:' + secondList);
});

Given('a new asset list of type {string}', function (string) {
  // Given('a new asset list of type {float}', function (float) {
  // Write code here that turns the phrase above into concrete actions
  return 'pending';
});

When('both list are entered to the Delta Calculator', function () {
  // Write code here that turns the phrase above into concrete actions
  return 'pending';
});

When('both list are entered to the Delta Calculator', function () {
  // Write code here that turns the phrase above into concrete actions
  return 'pending';
});

Then('the output asset list has {int} assets', function (int) {
  // Then('the output asset list has {float} assets', function (float) {
  // Write code here that turns the phrase above into concrete actions
  return 'pending';
});

Then('the asset list returned has the next contents', function (dataTable) {
  // Write code here that turns the phrase above into concrete actions
  return 'pending';
});
Then('the asset at position {int} has the next values', function (int, dataTable) {
  // Then('the asset at position {float} has the next values', function (float, dataTable) {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
  });
