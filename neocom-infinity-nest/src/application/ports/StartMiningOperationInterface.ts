import { MiningOperation } from "src/domain/entities/MiningOperation";

export interface StartMiningOperationInterface {
  execute(): Promise<StartMiningOperationInterface.Response>;
}

export namespace StartMiningOperationInterface {
  export type Response = MiningOperation;
}
