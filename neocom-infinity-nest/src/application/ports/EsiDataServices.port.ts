import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";
import { IEsiSecureService } from "./IEsiSecureService.port";

export abstract class EsiDataServicesPort {
    abstract miningOperations: IEsiSecureService<V2MiningOperation>
}
