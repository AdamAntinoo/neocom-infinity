import { Controller, Get, Param } from "@nestjs/common";
import { JwtService } from '@nestjs/jwt';
import { V2MiningOperation } from 'neocom-domain'
import { Cookies } from "@Infra/network/cookie.decorator";
import { CapsuleerMiningOperationsUseCase } from 'application/use-cases/mining-operation/CapsuleerMiningOperationsUseCase';
import { EsiSecureApi } from '@Infra/api/esiSecure.api';
import { AuthenticationTokenValidator } from "../validator/AuthenticationTokenValidator";
import { MiningOperationsUseCaseInputConstructor } from "../MiningOperationsUseCaseInputConstructor";
import { V1MiningOperationDto } from "neocom-domain/dto/V1MiningOperationDto.dto";

@Controller('/nin/v1/character/')
export class V1MiningOperationsController implements EsiSecureApi {
    constructor(
        private readonly getCapsuleerMiningOperationsUseCase: CapsuleerMiningOperationsUseCase,
        private readonly jwtService: JwtService
    ) { }

    @Get('miningoperations')
    public async getMiningOperations(@Cookies('Authentication') token: string): Promise<V1MiningOperationDto[]> {
        console.log('token->' + token)
        return this.getCapsuleerMiningOperationsUseCase.getMiningOperations(
            new MiningOperationsUseCaseInputConstructor().construct(token,
                new AuthenticationTokenValidator(this.jwtService).validate(token)
            )
        )
    }
}
