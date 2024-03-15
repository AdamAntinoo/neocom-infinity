import { MiningOperation } from '@Domain/entities/V1.MiningOperation'

export interface MiningOperationRepository {
    cleanRepository(): number
    createMiningOperation(miningOperation: MiningOperation): MiningOperation
    updateMiningOperation(miningOperation: MiningOperation): MiningOperation
}
