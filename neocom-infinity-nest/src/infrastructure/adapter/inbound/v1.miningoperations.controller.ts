import { CapsuleerMiningOperationsUseCase } from "@App/use-cases/mining-operation/CapsuleerMiningOperationsUseCase";
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";
import { Cookies } from "@Infra/network/cookie.decorator";
import { Controller, Get, Param } from "@nestjs/common";

@Controller('/nin/v1/character/')
export class V1MiningOperationsController {
    constructor(private readonly getCapsuleerMiningOperationsUseCase: CapsuleerMiningOperationsUseCase) { }

    @Get(':characterId/miningoperations')
    public async getMiningOperationsForCharacter(@Cookies('name') token: string,@Param() params: any): Promise<V2MiningOperation[]> {
        return this.getCapsuleerMiningOperationsUseCase.getMiningOperations(params.characterId)
    }
}
