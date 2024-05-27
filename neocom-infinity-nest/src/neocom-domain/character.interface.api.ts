import { V1AssetDto } from 'neocom-domain'

export interface CharacterServiceInterface {
	getCharactersCharacterIdBlueprints(token: string): Promise<V1AssetDto[]>
	getCharactersCharacterIdAssets(token: string): Promise<V1AssetDto[]>
}
