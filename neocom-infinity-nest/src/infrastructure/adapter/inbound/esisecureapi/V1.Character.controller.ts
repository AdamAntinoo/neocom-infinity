import { Cookies } from '@Infra/adapter/security/Cookie.decorator'
import { Controller, Get, Param } from '@nestjs/common'
import { JwtService } from '@nestjs/jwt'
import { CharacterServiceInterface, V1AssetDto } from 'neocom-domain'
import { COOKIE_DEFINITIONS } from 'neocom-domain'
import { AuthenticationTokenValidator } from '../validator/AuthenticationTokenValidator'
import { EsiSecureUseCaseInputConstructor } from '@App/use-cases/esi-secure/constructors/EsiSecureUseCaseInput.constuctor'
import { CapsuleerAssetsUseCase } from '@App/use-cases/esi-secure/CapsuleerAssets.usecase'
import { CapsuleerBlueprintsUseCase, CapsuleerBlueprintsUseCaseInput } from '@App/use-cases/esi-secure/CapsuleerBlueprints.usecase'
import { V1BlueprintDto } from 'neocom-domain'

@Controller('/api/v3/neocom/character')
export class V1CharacterController implements CharacterServiceInterface {
	constructor(
		private readonly getCapsuleerAssetsUseCase: CapsuleerAssetsUseCase,
		private readonly getCapsuleerBlueprintsUseCase: CapsuleerBlueprintsUseCase,
		private readonly jwtService: JwtService,
	) {}

	@Get('blueprints/session/:sessionid')
	public async getCharactersCharacterIdBlueprints(
		@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string,
		// eslint-disable-next-line @typescript-eslint/no-unused-vars
		@Param('sessionid') _sessionid: string,
	): Promise<V1BlueprintDto[]> {
		return this.getCapsuleerBlueprintsUseCase.getBlueprints(
			new EsiSecureUseCaseInputConstructor<CapsuleerBlueprintsUseCaseInput>().construct(
				token,
				new AuthenticationTokenValidator(this.jwtService).validate(token),
			),
		)
	}

	@Get('assets')
	public async getCharactersCharacterIdAssets(
		@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string,
		// eslint-disable-next-line @typescript-eslint/no-unused-vars
		@Param('sessionid') _sessionid: string,
	): Promise<V1AssetDto[]> {
		return this.getCapsuleerAssetsUseCase.getAssets(
			new EsiSecureUseCaseInputConstructor().construct(token, new AuthenticationTokenValidator(this.jwtService).validate(token)),
		)
	}
}
