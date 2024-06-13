import { Controller, Get } from '@nestjs/common'
import { JwtService } from '@nestjs/jwt'

import { Cookies } from '@Infra/adapter/security/Cookie.decorator'
import { Headers } from '@Infra/adapter/security/Header.decorator'
import { AuthenticationTokenValidator } from '../validator/AuthenticationTokenValidator'
import { COOKIE_DEFINITIONS, HEADER_NAMES, V1MiningOperationDto, V2ProcessedBlueprintDto } from 'neocom-domain'
import { IndustryServiceInterface } from 'neocom-domain'
import { CapsuleerMiningOperationsUseCase } from '@App/use-cases/esi-secure/CapsuleerMiningOperationsUseCase'
import { EsiSecureUseCaseInputConstructor } from '@App/use-cases/esi-secure/constructors/EsiSecureUseCaseInput.constuctor'
import { ProcessedBlueprintsUseCase } from '@App/use-cases/esi-secure/ProcessedBlueprints.usecase'

@Controller('/api/v3/neonest/industry')
export class V1IndustryController implements IndustryServiceInterface {
	constructor(
		private readonly getCapsuleerMiningOperationsUseCase: CapsuleerMiningOperationsUseCase,
		private readonly getProcessedBlueprintsUseCase: ProcessedBlueprintsUseCase,
		private readonly jwtService: JwtService,
	) {}
	@Get('miningoperations')
	public async getMiningOperations4Pilot(
		@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string,
		// eslint-disable-next-line @typescript-eslint/no-unused-vars
		_sessionid: string,
	): Promise<V1MiningOperationDto[]> {
		return this.getCapsuleerMiningOperationsUseCase.getMiningOperations(
			new EsiSecureUseCaseInputConstructor().construct(token, new AuthenticationTokenValidator(this.jwtService).validate(token)),
		)
	}
	@Get('/pilots/manufacture/blueprints/session/{sessionid}')
	public async getProcessedBlueprints4Pilot(
		// eslint-disable-next-line @typescript-eslint/no-unused-vars
		@Headers(HEADER_NAMES.NEOCOM_TOKEN_HEADER_NAME) neocomToken: string,
		// eslint-disable-next-line @typescript-eslint/no-unused-vars
		_sessionid: string,
	): Promise<V2ProcessedBlueprintDto[]> {
		return this.getProcessedBlueprintsUseCase.execute({
			pilotId: this.extractCapsuleerId(new AuthenticationTokenValidator(this.jwtService).validate(neocomToken).sub),
		})
	}
	private extractCapsuleerId(subject: string): number {
		const id: number = Number(subject.split(':')[2])
		return id
	}
}
