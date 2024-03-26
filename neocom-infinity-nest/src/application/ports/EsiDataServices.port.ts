import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";
import { IEsiMiningSecureService } from "./IEsiMiningSecureService.port";

export abstract class ESIDataServicesPort {
    abstract miningOperations: IEsiMiningSecureService<V2MiningOperation>
}
