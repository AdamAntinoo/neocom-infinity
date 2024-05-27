import { V1AssetDto } from "../dto/V1.Asset.dto"

export interface CharacterServiceInterface {
	getCharactersCharacterIdBlueprints(token: string): Promise<V1AssetDto[]>
	getCharactersCharacterIdAssets(token: string): Promise<V1AssetDto[]>
}
