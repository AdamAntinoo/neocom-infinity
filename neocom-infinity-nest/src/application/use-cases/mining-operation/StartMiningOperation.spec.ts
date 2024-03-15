import { MiningOperation } from "@Domain/entities/V1.MiningOperation";
import { StartMiningOperation } from "./StartMiningOperation";
import { MiningOperationState } from "@Domain/entities/MiningOperationState";

describe('USE CASE StartMiningOperation [Module: use-cases]', () => {
    let startMiningOperationUseCase: StartMiningOperation;
    beforeEach(async () => {
        startMiningOperationUseCase = new StartMiningOperation();
    });
    describe('constructor contract', () => {
        test('should be created', () => {
            expect(startMiningOperationUseCase).toBeDefined();
        });
    });
    describe('execute', () => {
        test('when the command arrives then a new MiningOperation is created', () => {
            const sutPromise: Promise<MiningOperation> = startMiningOperationUseCase.execute();
            expect(sutPromise).toBeDefined();
            sutPromise.then((sut) => {
                expect(sut).toBeDefined()
                expect(sut.id).toBeDefined()
                expect(sut.state).toBe(MiningOperationState.OPEN)
                expect(sut.startDate).toBeUndefined()
                expect(sut.endDate).toBeUndefined()
            })
        });
    });
});
