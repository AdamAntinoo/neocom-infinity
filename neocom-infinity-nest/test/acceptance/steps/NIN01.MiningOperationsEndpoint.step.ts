import { Before, Given, Then, When, setWorldConstructor } from '@cucumber/cucumber';
import { expect } from 'expect';
import { NIN01World } from '../worlds/NIN01World';
import { V2MiningOperation } from 'neocom-domain';

Then('the esi response has {int} items', function (this: NIN01World, size: number) {
    expect ( this.miningActionsResponse).toBeDefined()
    expect(this.miningActionsResponse.length).toBe(size)
})

Then('the response has {int} items', function (size: number) {
    expect(this.miningActionsResponse.length).toBe(size)
})
Then('the Mined Resources record at position {int} has next contents', function (this: NIN01World, position: number, dataTable) {
    const row = dataTable.hashes()[position - 1]
    expect(row).toBeDefined
    validateMiningOperation(row, this.miningActionsResponse[position - 1])
})

// ------------

function validateMiningOperation(row: any, operation: V2MiningOperation) {
    expect(row['jsonClass']).toBe(operation.jsonClass)
    expect(row['date']).toBe(operation.date)
    expect(row['quantity']).toBe(operation.quantity)
    expect(row['solarSystem']).toBe(operation.solarSystem)
    expect(row['typeId']).toBe(operation.typeId)
}
