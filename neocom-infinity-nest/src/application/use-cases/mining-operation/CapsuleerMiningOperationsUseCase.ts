import { EsiToken } from "@Infra/adapter/inbound/EsiToken.interface";
import { Injectable } from "@nestjs/common";
import { ESIDataServicesPort } from "application/ports/EsiDataServices.port";
import { V2MiningOperation } from 'neocom-domain'

export interface MiningOperationsUseCaseInput {
    jwt: string // Original encoded token to be passed to ESI
    token: EsiToken // Decoded token to be used on the service
    capsuleerId: number // Extracted capsuleer id
}
declare namespace CapsuleerMiningOperationsUseCase {
    export type Request = MiningOperationsUseCaseInput
}

@Injectable()
export class CapsuleerMiningOperationsUseCase {
    constructor(private readonly dataServices: ESIDataServicesPort) { }

    public async getMiningOperations(input: MiningOperationsUseCaseInput): Promise<V2MiningOperation[]> {
        return this.dataServices.miningOperations.getMiningOperations(input.capsuleerId, input.jwt)
    }
}
