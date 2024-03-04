import { MiningOperation } from "./MiningOperation";
import { MiningOperationState } from "./MiningOperationState";

describe('ENTITY MiningOperation [Module: Entities]', () => {
  //    let targetMiningOperation:MiningOperation  
  // beforeEach(async () => {
  //   targetMiningOperation=new MiningOperation()
  // });
  describe('constructor contract', () => {
    test('should be created with state', () => {
      const targetMiningOperation: MiningOperation = new MiningOperation({ state: MiningOperationState.OPEN })
      expect(targetMiningOperation).toBeDefined();
      expect(targetMiningOperation.state).toBe(MiningOperationState.OPEN)
    });
  })
})
