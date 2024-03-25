import { Injectable } from "@nestjs/common";
import { EsiDataServicesPort } from "@App/ports/EsiDataServices.port";
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";

@Injectable()
export class CapsuleerMiningOperationsUseCase {
    constructor(private readonly dataServices: EsiDataServicesPort) { }

    public async getMiningOperations(characterId: number): Promise<V2MiningOperation[]> {
        return this.dataServices.miningOperations.getMiningOperations(characterId)
    }
}
