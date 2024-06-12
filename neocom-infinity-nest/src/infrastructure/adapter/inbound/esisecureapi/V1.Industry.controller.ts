import { Controller, Get } from '@nestjs/common'
import { JwtService } from '@nestjs/jwt'

import { Cookies } from '@Infra/config/Cookie.decorator'
import { Headers } from '@Infra/config/Header.decorator'
import { AuthenticationTokenValidator } from '../validator/AuthenticationTokenValidator'
import { V1MiningOperationDto, V2ProcessedBlueprintDto } from 'neocom-domain'
import { IndustryServiceInterface } from 'neocom-domain'
import { CapsuleerMiningOperationsUseCase } from '@App/use-cases/esi-secure/CapsuleerMiningOperationsUseCase'
import { EsiSecureUseCaseInputConstructor } from '@App/use-cases/esi-secure/constructors/EsiSecureUseCaseInput.constuctor'

@Controller('/api/v3/neocom/character')
export class V1IndustryController implements IndustryServiceInterface {
	constructor(
		private readonly getCapsuleerMiningOperationsUseCase: CapsuleerMiningOperationsUseCase,
		private readonly jwtService: JwtService,
	) {}

	@Get('miningoperations')
	public async getMiningOperations4Pilot(
		@Cookies('ESI-DATA-SERVICES') token: string,
		// eslint-disable-next-line @typescript-eslint/no-unused-vars
		_sessionid: string,
	): Promise<V1MiningOperationDto[]> {
		return this.getCapsuleerMiningOperationsUseCase.getMiningOperations(
			new EsiSecureUseCaseInputConstructor().construct(token, new AuthenticationTokenValidator(this.jwtService).validate(token)),
		)
	}
	@Get('/api/v2/neonest/industry/pilots/manufacture/blueprints/session/{sessionid}')
	public async getProcessedBlueprints4Pilot(
		// eslint-disable-next-line @typescript-eslint/no-unused-vars
		@Headers('X-NeoCom-Authorization') neocomToken: string,
		// eslint-disable-next-line @typescript-eslint/no-unused-vars
		_sessionid: string,
	): Promise<V2ProcessedBlueprintDto[]> {
		return []
	}

	// getMiningOperations4Pilot(token: string),sessionid: string: Promise<V1MiningOperationDto[]>
	// getProcessedBlueprints4Pilot(neocomToken: string): Promise<V2ProcessedBlueprintDto[]>
}
