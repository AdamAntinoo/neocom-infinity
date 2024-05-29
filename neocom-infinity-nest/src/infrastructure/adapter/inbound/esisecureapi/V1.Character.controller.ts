import { Cookies } from '@Infra/config/Cookie.decorator'
import { Controller, Get } from '@nestjs/common'
import { JwtService } from '@nestjs/jwt'
import { V1AssetDto } from 'neocom-domain'
import { CharacterServiceInterface } from 'neocom-domain'
import { COOKIE_DEFINITIONS } from 'neocom-shared'
import { AuthenticationTokenValidator } from '../validator/AuthenticationTokenValidator'
import { EsiSecureUseCaseInputConstructor } from '@App/use-cases/esisecure/constructors/EsiSecureUseCaseInput.constuctor'
import { CapsuleerAssetsUseCase } from '@App/use-cases/esisecure/CapsuleerAssets.usecase'
import { CapsuleerBlueprintsUseCase, CapsuleerBlueprintsUseCaseInput } from '@App/use-cases/esisecure/CapsuleerBlueprints.usecase'
import { V1BlueprintDto } from 'neocom-domain'

@Controller('/api/v3/neocom/character')
export class V1CharacterController implements CharacterServiceInterface {
	constructor(
		private readonly getCapsuleerAssetsUseCase: CapsuleerAssetsUseCase,
		private readonly getCapsuleerBlueprintsUseCase: CapsuleerBlueprintsUseCase,
		private readonly jwtService: JwtService,
	) {}

	@Get('blueprints')
	public async getCharactersCharacterIdBlueprints(@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string): Promise<V1BlueprintDto[]> {
		return this.getCapsuleerBlueprintsUseCase.getBlueprints(
			new EsiSecureUseCaseInputConstructor<CapsuleerBlueprintsUseCaseInput>().construct(
				token,
				new AuthenticationTokenValidator(this.jwtService).validate(token),
			),
		)
	}

	@Get('assets')
	public async getCharactersCharacterIdAssets(@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string): Promise<V1AssetDto[]> {
		return this.getCapsuleerAssetsUseCase.getAssets(
			new EsiSecureUseCaseInputConstructor().construct(token, new AuthenticationTokenValidator(this.jwtService).validate(token)),
		)
	}
}
