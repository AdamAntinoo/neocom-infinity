import { MiningOperation } from '@Domain/entities/V1.MiningOperation'

export interface StartMiningOperationInterface {
    execute(request: StartMiningOperationInterface.Request): Promise<StartMiningOperationInterface.Response>
}

declare namespace StartMiningOperationInterface {
    export type Request = number
    export type Response = MiningOperation
}
