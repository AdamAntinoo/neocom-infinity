import { Injectable } from "@nestjs/common";
import { ESIDataServicesPort } from "@App/ports/ESIDataServices.port";
import { V2MiningOperation } from 'neocom-domain'

@Injectable()
export class CapsuleerMiningOperationsUseCase {
    constructor(private readonly dataServices: ESIDataServicesPort) { }

    public async getMiningOperations(characterId: number): Promise<V2MiningOperation[]> {
        return this.dataServices.miningOperations.getMiningOperations(characterId)
    }
}
