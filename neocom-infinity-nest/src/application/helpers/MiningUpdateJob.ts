import { CronJob } from 'cron'
import { MiningOperation } from '@Domain/entities/MiningOperation'
import { BackendException } from '@Infra/exceptions/BackendException'

export class MiningUpdateJob {
  private readonly MINING_JOB_SCHEDULE = `0 0/5 * * * *`
  private readonly PREFIX = 'MOJ-'
  public readonly name: string
  private readonly characterId: number
  private miningOperation: MiningOperation

  constructor(fields: object = {}) {
    Object.assign(this, fields)
    if (this.characterId == undefined)
      throw new BackendException({ message: 'Mandatory characterId is not net.' })
    else this.name = this.PREFIX + this.characterId
  }

  public setMiningOperation(
    newMiningOperation: MiningOperation,
  ): MiningUpdateJob {
    this.miningOperation = newMiningOperation
    return this
  }
  public getScheduledJob(): CronJob {
    return new CronJob(this.MINING_JOB_SCHEDULE, () => {
      this.run()
    })
  }
  private run(): void {
    // Do job run actions
  }
}
