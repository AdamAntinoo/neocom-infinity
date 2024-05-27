import { Cookies } from '@Infra/config/Cookie.decorator'
import { Controller } from '@nestjs/common'
import { V1AssetDto } from 'neocom-domain'
import { CharacterServiceInterface } from 'neocom-domain/character.interface.api'
import { COOKIE_DEFINITIONS } from 'neocom-shared'

@Controller('/api/v3/neocom/character/miningoperations')
export class V1CharacterController implements CharacterServiceInterface {
	public async getCharactersCharacterIdBlueprints(@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string): Promise<V1AssetDto[]> {
		console.log(token)
		throw new Error('Method not implemented.')
	}
	public async getCharactersCharacterIdAssets(@Cookies(COOKIE_DEFINITIONS.ESI_COOKIE_NAME) token: string): Promise<V1AssetDto[]> {
		console.log(token)
		throw new Error('Method not implemented.')
	}
}
