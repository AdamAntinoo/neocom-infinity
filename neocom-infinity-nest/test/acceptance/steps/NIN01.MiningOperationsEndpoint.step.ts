import { Then, When } from '@cucumber/cucumber';
import { expect } from 'expect';
import { NIN01World } from '../worlds/NIN01World';
import { GetCharactersCharacterIdMining200Ok, V1MiningOperationDto } from 'neocom-domain';
import { V1StackDto } from 'neocom-domain';

Then('the esi response has {int} items', function (this: NIN01World, size: number) {
    expect(this.miningActionsResponse).toBeDefined()
    expect(this.miningActionsResponse.length).toBe(size)
})

Then('the response has {int} items', function (size: number) {
    expect(this.miningActionsResponse.length).toBe(size)
})

When('the list of items is processed we get {int} MiningOperations', function (this: NIN01World, opCount: number) {
    expect(this.miningOperationsResponse).toBeDefined()
    expect(this.miningOperationsResponse.length).toBe(opCount)
})
When('the MiningOperation record at position {int} has next contents', function (this: NIN01World, position: number, dataTable) {
    expect(this.miningOperationsResponse).toBeDefined()
    const target: V1MiningOperationDto = this.miningOperationsResponse[position - 1]
    expect(target).toBeDefined()
    const row = dataTable.hashes()[position - 1]
    expect(row).toBeDefined
    expect(validateMiningOperation(row, target)).toBeTruthy()
})
//@deprecated
Then('the Mined Resources record at position {int} has next contents', function (this: NIN01World, position: number, dataTable) {
    const row = dataTable.hashes()[position - 1]
    expect(row).toBeDefined
    validateEsiMiningOperation(row, this.miningActionsResponse[position - 1])
})
Then('the MiningOperation record at position {int} is the target', function (this: NIN01World, position: number) {
    const target: V1MiningOperationDto = this.miningOperationsResponse[position - 1]
    expect(target).toBeDefined()
    this.targetMiningOperation = target
})
Then('the MiningOperation has {int} resources', function (this: NIN01World, resourceCount: number) {
    expect(this.targetMiningOperation).toBeDefined()
    expect(this.targetMiningOperation.getResources()).toBeDefined()
    expect(this.targetMiningOperation.getResources().length).toBe(resourceCount)
})
Then('the MiningResource at position {int} has next contents', function (this: NIN01World, position: number, dataTable) {
    const row = dataTable.hashes()[position - 1]
    expect(row).toBeDefined()
    expect(this.targetMiningOperation).toBeDefined()
    expect(this.targetMiningOperation.getResources()).toBeDefined()
    const resource: V1StackDto = this.targetMiningOperation.getResources()[position - 1]
    expect(resource).toBeDefined()
    expect(validateMiningResource(row, resource)).toBeTruthy()
})
// ------------

function validateMiningOperation(row: any, operation: V1MiningOperationDto): boolean {
    expect(row['jsonClass']).toBe(operation.jsonClass)
    expect(row['id']).toBe(operation.id)
    expect(row['date']).toBe(operation.date)
    expect(row['solarSystemLink']).toBe(operation.solarSystemLink)
    expect(Number(row['resourceCount'])).toBe(operation.getResources().length)
    return true
}
function validateEsiMiningOperation(row: any, operation: GetCharactersCharacterIdMining200Ok): boolean {
    expect(row['date']).toBe(operation.date)
    expect(row['quantity']).toBe(operation.quantity)
    expect(row['solarSystem']).toBe(operation.solar_system_id)
    expect(row['typeId']).toBe(operation.type_id)
    return true
}
function validateMiningResource(row: any, resource: V1StackDto): boolean {
    expect(Number(row['quantity'])).toBe(resource.quantity)
    expect(row['typeLink']).toBe(resource.typeLink)
    return true
}
