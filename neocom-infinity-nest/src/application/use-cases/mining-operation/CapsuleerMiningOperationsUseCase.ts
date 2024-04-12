import { EsiToken } from "@Infra/adapter/inbound/EsiToken.interface";
import { ESISecureDataServiceHALGeneratorAdapter } from "@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservice.halgenerator.adapter";
import { Injectable } from "@nestjs/common";
import { V1MiningOperationDto } from "neocom-domain/dto/V1MiningOperationDto.dto";
import { GetCharactersCharacterIdMining200Ok } from "application/domain/esi-model/models";
import { ESIDataServicesPort } from "application/ports/EsiDataServices.port";
import { Optional } from "typescript-optional";
import { V1MiningResourceDto } from "neocom-domain/dto/V1MiningResourceDto.dto";
import { MiningOperationConverter } from "@Infra/adapter/outbound/ESISecureDataServices/converter/MiningOperationConverter";
import { transformSync } from "@babel/core";

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

    public async getMiningOperations(input: MiningOperationsUseCaseInput): Promise<V1MiningOperationDto[]> {
        const esiMiningOperations: GetCharactersCharacterIdMining200Ok[] = await this.dataServices.miningOperations.getMiningOperations(
            input.capsuleerId, input.jwt
        )
        return this.aggregateMiningOperations(esiMiningOperations)
    }
    private aggregateMiningOperations(esiMiningOperations: GetCharactersCharacterIdMining200Ok[]): Promise<V1MiningOperationDto[]> {
        return new Promise<V1MiningOperationDto[]>((resolve) => {
            const transformedOperations: V1MiningOperationDto[] = []
            esiMiningOperations.forEach((miningAction: GetCharactersCharacterIdMining200Ok) => {
                // - find the operation where to store the mining action.
                const operationOptional: Optional<V1MiningOperationDto> = this.findOperation(miningAction, transformedOperations)
                if (operationOptional.isPresent()) {
                    operationOptional.get().addMiningResource(new MiningOperationConverter<GetCharactersCharacterIdMining200Ok, V1MiningResourceDto>()
                        .convert(miningAction)
                    )
                }
                else {
                    // - add new Mining Operation
                    const addition: V1MiningOperationDto = new V1MiningOperationDto.Builder()
                        .withId(this.generateIdentifierForMiningAction(miningAction))
                        .withDate(miningAction.date)
                        .withSolarSystem(miningAction.solar_system_id)
                        .build()
                    addition.addMiningResource(new MiningOperationConverter<GetCharactersCharacterIdMining200Ok, V1MiningResourceDto>()
                        .convert(miningAction)
                    )
                    // Add the new item to the collection
                    transformedOperations.push(addition)
                }
            })
            resolve(transformedOperations)
        })
    }
    private findOperation(miningAction: GetCharactersCharacterIdMining200Ok, operationsList: V1MiningOperationDto[]): Optional<V1MiningOperationDto> {
        for (var operation of operationsList) {
            const newIdentifier: string = this.generateIdentifierForMiningAction(miningAction)
            if (newIdentifier === operation.id)
                return Optional.of(operation)
        }
        return Optional.empty()
    }
    private generateIdentifierForMiningAction(miningAction: GetCharactersCharacterIdMining200Ok): string {
        return miningAction.date + '/' + miningAction.solar_system_id
    }
}
