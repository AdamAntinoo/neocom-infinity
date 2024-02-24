import { Before, Given, Then, When } from '@cucumber/cucumber';
import expect from 'expect';
import { NeoComWorld } from './NeoComWorld';

When('retrieve {string} for character {string}', async function (this: NeoComWorld, endpoint:string, characterId:string) {
  // Write code here that turns the phrase above into concrete actions
  return 'pending';
});

Then('the response status code is {string}', function (this: NeoComWorld, statusCode: string) {
  // Write code here that turns the phrase above into concrete actions
  return 'pending';
});

Then('the number of assets should be {int}', function (this: NeoComWorld, assetCount:string) {
  // Write code here that turns the phrase above into concrete actions
  return 'pending';
});
Before({ tags: '@foo' }, async function (this: NeoComWorld) {
  // this.foo = true;
});
