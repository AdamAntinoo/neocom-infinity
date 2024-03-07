import { Inject, Injectable } from '@nestjs/common'
import { SchedulerRegistry } from '@nestjs/schedule'
import { CronJob } from 'cron'

import { MiningOperationRepository } from '@App/ports/MiningOperationRepository'
import { StartMiningOperationInterface } from '@App/interfaces/StartMiningOperationInterface'
import { MiningOperation } from '@Domain/entities/MiningOperation'
import { MiningOperationState } from '@Domain/entities/MiningOperationState'
import { MiningUpdateJob } from '@App/helpers/MiningUpdateJob'
import { MiningOperationRepositoryMemory } from '@Infra/adapter/persistence/MiningOperationRepositoryMemory'
/**
 * RESPONSIBILITY:
 * Interact with the UX to process the StartMiningOperation request. That request will create a new MiningOperation and start the asset processing to evaluate the assets collected during the time the operation is active.
 * Will connect all the Helpers and Instances to be able to process the assets in a periodic way.
 * Will persist the MiningOperation on the corresponding repository.
 */
@Injectable()
export class StartMiningOperation implements StartMiningOperationInterface {
  constructor(
    private miningOperationRepository: MiningOperationRepositoryMemory,
    private schedulerRegistry: SchedulerRegistry,
    private starter: MiningOperationStarter4) {
    this.miningOperationRepository = miningOperationRepository
    this.schedulerRegistry = schedulerRegistry
  }

  public async execute(request: number): Promise<MiningOperation> {
    return new Promise<MiningOperation>((resolve) => {
      const newMiningOperation: MiningOperation = new MiningOperation({
        state: MiningOperationState.OPEN,
      })
      // Persist the operation.
      const savedMiningOperation: MiningOperation = this.miningOperationRepository.createMiningOperation(newMiningOperation)
      // Create the scheduled job to upload the assets.
      const miningJob: MiningUpdateJob = new MiningUpdateJob({
        characterId: request,
      })
      miningJob.setMiningOperation(savedMiningOperation)
      const job: CronJob = miningJob.getScheduledJob()

      this.schedulerRegistry.addCronJob(miningJob.name, job)
      job.start()

      resolve(savedMiningOperation)
    })
  }
}
