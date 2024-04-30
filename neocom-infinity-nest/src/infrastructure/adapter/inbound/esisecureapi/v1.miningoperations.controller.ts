import { Controller, Get } from '@nestjs/common'
import { JwtService } from '@nestjs/jwt'
import { Cookies } from '@Infra/config/Cookie.decorator'
import { CapsuleerMiningOperationsUseCase } from 'application/use-cases/mining-operation/CapsuleerMiningOperationsUseCase'
import { EsiSecureApi } from '@Infra/api/esiSecure.api'
import { AuthenticationTokenValidator } from '../validator/AuthenticationTokenValidator'
import { MiningOperationsUseCaseInputConstructor } from '../MiningOperationsUseCaseInputConstructor'
import { V1MiningOperationDto } from 'neocom-domain'
import { COOKIE_DEFINITIONS } from 'neocom-shared'

@Controller('/nin/v1/character/')
export class V1MiningOperationsController implements EsiSecureApi {
	constructor(
		private readonly getCapsuleerMiningOperationsUseCase: CapsuleerMiningOperationsUseCase,
		private readonly jwtService: JwtService,
	) {}

	@Get('miningoperations')
	public async getMiningOperations(@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string): Promise<V1MiningOperationDto[]> {
		return this.getCapsuleerMiningOperationsUseCase.getMiningOperations(
			new MiningOperationsUseCaseInputConstructor().construct(token, new AuthenticationTokenValidator(this.jwtService).validate(token)),
		)
	}
}
