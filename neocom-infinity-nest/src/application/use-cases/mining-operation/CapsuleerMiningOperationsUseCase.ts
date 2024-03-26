import { Injectable } from "@nestjs/common";
import { ESIDataServicesPort } from "@App/ports/ESIDataServices.port";
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";

@Injectable()
export class CapsuleerMiningOperationsUseCase {
    constructor(private readonly dataServices: ESIDataServicesPort) { }

    public async getMiningOperations(characterId: number): Promise<V2MiningOperation[]> {
        return this.dataServices.miningOperations.getMiningOperations(characterId)
    }
}
