import { MiningOperation } from '@Domain/entities/MiningOperation'

export interface MiningOperationRepository {
  cleanRepository(): number
  createMiningOperation(miningOperation: MiningOperation): MiningOperation
  updateMiningOperation(miningOperation: MiningOperation): MiningOperation
}
