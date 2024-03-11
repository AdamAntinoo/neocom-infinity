import { UUID, randomUUID } from 'crypto';
import { AssetEsi } from '@Domain/dto/ESIAsset.esi';
import { MiningOperationState } from './MiningOperationState';

export class MiningOperation {
  public readonly id: UUID;
  public readonly characterId: number;
  public state: MiningOperationState;
  public startDate: Date;
  public endDate: Date;
  public readonly baselineAssetList: AssetEsi[];
  public readonly deltaAssetList: AssetEsi[];

  constructor(fields: object = {}) {
    Object.assign(this, fields);
    this.id = randomUUID();
  }
  public startOperation() {
    this.state = MiningOperationState.OPEN
    this.startDate = new Date();
  }
}
