import { AuthenticationServicesPort } from "@App/ports/AuthenticationServices.port";
import { CapsuleerMiningOperationsUseCase } from "@App/use-cases/mining-operation/CapsuleerMiningOperationsUseCase";
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";
import { Cookies } from "@Infra/network/cookie.decorator";
import { Controller, Get, Param } from "@nestjs/common";

@Controller('/nin/v1/character/')
export class V1MiningOperationsController {
    constructor(
        private readonly getCapsuleerMiningOperationsUseCase: CapsuleerMiningOperationsUseCase,
        private readonly securityAdapter: AuthenticationServicesPort
    ) { }

    @Get(':characterId/miningoperations')
    public async getMiningOperationsForCharacter(@Cookies('Authentication') token: string, @Param() params: any): Promise<V2MiningOperation[]> {
        console.log('token->' + token)
        if (this.securityAdapter.authentication.validateToken(token))
            if (this.securityAdapter.authentication.checkCapsuleer(params.characterId))
                return this.getCapsuleerMiningOperationsUseCase.getMiningOperations(params.characterId)
    }
}
