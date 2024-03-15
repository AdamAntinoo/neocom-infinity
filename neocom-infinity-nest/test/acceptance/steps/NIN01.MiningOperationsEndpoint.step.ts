import { Before, Given, Then, When } from '@cucumber/cucumber';
import { expect } from 'expect';
import { NIN01World } from '../worlds/NIN01World';

Given('en environment prepared for capsuleer {int}', function (this: NIN01World, pilotId: number) {
    this.characterId = pilotId
})
When('the endpoint {string} is activated', function (endpoint:string) {
   
})
