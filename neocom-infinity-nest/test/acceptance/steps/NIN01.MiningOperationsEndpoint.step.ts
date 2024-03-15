import { Before, Given, Then, When, setWorldConstructor } from '@cucumber/cucumber';
import { expect } from 'expect';
import { NIN01World } from '../worlds/NIN01World';
import { V2MiningOperation } from '@Domain/entities/V2.MiningOperation';

setWorldConstructor(NIN01World);

Before(async function (scenario) {
    await this.init(scenario);
})

Given('en environment prepared for capsuleer {int}', function (pilotId: number) {
    this.characterId = pilotId
})
When('the endpoint {string} is activated',async function (endpoint: string) {
    expect(this.controller).toBeDefined
    const pilotId: number = 423
    const sut: Promise<V2MiningOperation[]> = this.controller.getMiningOperationsForCharacter({ characterId: pilotId })
    expect(sut).toBeDefined
    console.log(sut)
   await sut.then(data => {
        console.log('then.data->' + data)
        this.miningActionsResponse = data
    })
})
When('the response has {int} items', function (size: number) {
    expect(this.miningActionsResponse.length).toBe(size)
})
