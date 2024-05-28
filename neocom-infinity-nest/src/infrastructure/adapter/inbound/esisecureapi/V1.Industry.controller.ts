import { Controller, Get } from '@nestjs/common'
import { JwtService } from '@nestjs/jwt'

import { Cookies } from '@Infra/config/Cookie.decorator'
import { AuthenticationTokenValidator } from '../validator/AuthenticationTokenValidator'
import { V1MiningOperationDto } from 'neocom-domain'
import { COOKIE_DEFINITIONS } from 'neocom-shared'
import { IndustryServiceInterface } from 'neocom-domain'
import { CapsuleerMiningOperationsUseCase } from '@App/use-cases/esisecure/CapsuleerMiningOperationsUseCase'
import { MiningOperationsUseCaseInputConstructor } from '@App/use-cases/esisecure/constructors/MiningOperationsUseCaseInputConstructor'

@Controller('/api/v3/neocom/character')
export class V1IndustryController implements IndustryServiceInterface {
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
