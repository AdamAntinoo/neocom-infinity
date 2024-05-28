import { Cookies } from '@Infra/config/Cookie.decorator'
import { Controller, Get } from '@nestjs/common'
import { JwtService } from '@nestjs/jwt'
import { V1AssetDto } from 'neocom-domain'
import { CharacterServiceInterface } from 'neocom-domain'
import { COOKIE_DEFINITIONS } from 'neocom-shared'
import { AuthenticationTokenValidator } from '../validator/AuthenticationTokenValidator'
import { AssetsUseCaseInputConstructor } from '@Application/use-cases/esisecure/constructors/AssetsUseCaseInput.constuctor'
import { CapsuleerAssetsUseCase } from '@Application/use-cases/esisecure/CapsuleerAssets.usecase'
import { CapsuleerBlueprintsUseCase } from '@Application/use-cases/esisecure/CapsuleerBlueprints.usecase'

@Controller('/api/v3/neocom/character')
export class V1CharacterController implements CharacterServiceInterface {
	constructor(
		private readonly getCapsuleerAssetsUseCase: CapsuleerAssetsUseCase,
		private readonly getCapsuleerBlueprintsUseCase: CapsuleerBlueprintsUseCase,
		private readonly jwtService: JwtService,
	) {}

	@Get('blueprints')
	public async getCharactersCharacterIdBlueprints(@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string): Promise<V1AssetDto[]> {
		console.log(token)
		throw new Error('Method not implemented.')
	}

	@Get('assets')
	public async getCharactersCharacterIdAssets(@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string): Promise<V1AssetDto[]> {
		return this.getCapsuleerAssetsUseCase.getAssets(
			new AssetsUseCaseInputConstructor().construct(token, new AuthenticationTokenValidator(this.jwtService).validate(token)),
		)
	}
}
