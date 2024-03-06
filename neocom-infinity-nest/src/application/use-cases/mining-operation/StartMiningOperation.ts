import { Inject, Injectable } from '@nestjs/common'
import { SchedulerRegistry } from '@nestjs/schedule'
import { CronJob } from 'cron'

import { MiningOperationRepository } from '@App/ports/MiningOperationRepository'
import { StartMiningOperationInterface } from '@App/interfaces/StartMiningOperationInterface'
import { MiningOperation } from '@Domain/entities/MiningOperation'
import { MiningOperationState } from '@Domain/entities/MiningOperationState'
import { MiningUpdateJob } from '@App/helpers/MiningUpdateJob'

@Injectable()
export class StartMiningOperation implements StartMiningOperationInterface {
  // @Inject()
  private miningOperationRepository: MiningOperationRepository
  // @Inject()
  private schedulerRegistry: SchedulerRegistry

  constructor( miningOperationRepository: MiningOperationRepository,  schedulerRegistry: SchedulerRegistry) {
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
