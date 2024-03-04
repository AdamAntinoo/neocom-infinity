import { StartMiningOperationInterface } from "@App/ports/StartMiningOperationInterface";
import { MiningOperation } from "@Domain/entities/MiningOperation";
import { MiningOperationState } from "@Domain/entities/MiningOperationState";

export class StartMiningOperation implements StartMiningOperationInterface {
  constructor() { }
  execute(): Promise<MiningOperation> {
    return new Promise<MiningOperation>((resolve, reject) => {
      const newMiningOperation: MiningOperation = new MiningOperation({ state: MiningOperationState.OPEN })
      // Persist the operation
      resolve(newMiningOperation)
    })
  }
}
