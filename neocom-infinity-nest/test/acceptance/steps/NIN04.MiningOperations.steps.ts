import { expect } from 'expect';
import { Given, When, Then, Before, After } from '@cucumber/cucumber'

import { StartMiningOperation } from '@App/use-cases/mining-operation/StartMiningOperation'
import { MiningOperation } from '@Domain/entities/V1.MiningOperation'
import { MiningOperationRepositoryMemory } from '@Infra/adapter/persistence/MiningOperationRepositoryMemory'
import { NeoComWorld } from '../neocom-world'
import { SchedulerRegistry } from '@nestjs/schedule';

// Before(async function (this: NeoComWorld) {
//   // Load and check the scenario dependencies
//   await this.init()
// })
// After(async function (this: NeoComWorld) {
//   await this.close();
// })

Given('an empty MiningOperations repository',
    function () {
        this.providerMiningOperationRepository = new MiningOperationRepositoryMemory()
        expect(this.providerMiningOperationRepository).toBeDefined
        this.providerMiningOperationRepository.cleanRepository()
        // expect(this.miningOperationRepository.findAll().size).toBe(0)
    })

When('a use case {string} is executed for character {int}',
    function (this: NeoComWorld, useCaseName: string, characterId: number) {
        switch (useCaseName) {
            case 'StartMiningOperation': {
                // this.startMiningOperationUseCase = new StartMiningOperation(this.provider)
                expect(this.providerStartMiningOperationUseCase).toBeDefined
                const sut: Promise<MiningOperation> = this.providerStartMiningOperationUseCase.execute(characterId)
                expect(sut).toBeDefined
                // Obtain the mining operation
                sut.then(operation => {
                    this.miningOperation = operation
                    expect(this.miningOperation).toBeDefined
                })
                break
            }
        }
    })

Then('a new scheduled job with name {string}',
    function (string) {
        expect(this.providerSchedulerRegistry).toBeDefined
        console.log(JSON.stringify(this.providerSchedulerRegistry.getCronJobs))
    })

export class feat {
    constructor(startMiningOperationUseCase: StartMiningOperation, schedulerRegistry: SchedulerRegistry, miningOperationRepository: MiningOperationRepositoryMemory) {
        console.log(startMiningOperationUseCase)
        console.log(schedulerRegistry)
        console.log(miningOperationRepository)
    }
}
