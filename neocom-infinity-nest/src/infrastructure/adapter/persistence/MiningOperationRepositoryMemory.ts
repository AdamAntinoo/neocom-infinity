import { Injectable } from '@nestjs/common';
import { MiningOperation } from '@Domain/entities/V1.MiningOperation';
import { MiningOperationRepository } from '../../../application/ports/MiningOperationRepository';
import { UUID } from 'crypto';
import { min } from 'rxjs';

@Injectable()
// eslint-disable-next-line prettier/prettier
export class MiningOperationRepositoryMemory implements MiningOperationRepository {
    private readonly miningMap: Map<UUID, MiningOperation>;

    constructor() {
        this.miningMap = new Map();
    }

    public cleanRepository(): number {
        const usage = this.miningMap.size;
        this.miningMap.clear();
        return usage;
    }
    public findAll(): MiningOperation[] {
        return [...this.miningMap.values()]
    }
    // eslint-disable-next-line prettier/prettier
    public createMiningOperation(miningOperation: MiningOperation): MiningOperation {
        this.miningMap.set(miningOperation.id, miningOperation);
        return miningOperation;
    }
    // eslint-disable-next-line prettier/prettier
    public updateMiningOperation(miningOperation: MiningOperation): MiningOperation {
        const oldMining = this.miningMap.get(miningOperation.id)
        this.miningMap.set(miningOperation.id, miningOperation)
        return oldMining
    }
}
