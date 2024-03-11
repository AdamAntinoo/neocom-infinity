import { v4 as uuidv4 } from 'uuid'
import { MiningOperationState } from './MiningOperationState';

export class MiningOperation {
    public readonly id: string
    public readonly characterId: number;
    public state: MiningOperationState;
    public startDate: Date;
    public endDate: Date;
    // public readonly baselineAssetList: AssetEsi[];
    // public readonly deltaAssetList: AssetEsi[];

    constructor(fields: object = {}) {
        Object.assign(this, fields);
        this.id = uuidv4();
    }
    public startOperation() {
        this.state = MiningOperationState.OPEN
        this.startDate = new Date();
    }
}
