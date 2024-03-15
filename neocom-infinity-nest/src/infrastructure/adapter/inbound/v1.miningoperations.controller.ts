import { CapsuleerMiningOperationsUseCase } from "@App/use-cases/mining-operation/CapsuleerMiningOperationsUseCase";
import { EsiMining } from "@App/use-cases/mining-operation/EsiMining";
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";
import { HttpService } from "@nestjs/axios";
import { Controller, Get, Param } from "@nestjs/common";
import { firstValueFrom } from "rxjs";

@Controller('/nin/v1/character/')
export class V1MiningOperationsController {
    constructor(private readonly getCapsuleerMiningOperationsUseCase: CapsuleerMiningOperationsUseCase) { }

    @Get(':characterId/miningoperations')
    public async getMiningOperationsForCharacter(@Param() params: any): Promise<V2MiningOperation[]> {
        return this.getCapsuleerMiningOperationsUseCase.getMiningOperations(params.characterId)
    }
}
