import { V2MiningOperation } from 'neocom-domain'
import { IEsiMiningSecureService } from "./IEsiMiningSecureService.port";

export abstract class ESIDataServicesPort {
    abstract miningOperations: IEsiMiningSecureService<V2MiningOperation>
}
