import { V1AssetDto } from "../dto/V1.Asset.dto"
import { V1BlueprintDto } from "../dto/V1.Blueprint.dto"

export interface CharacterServiceInterface {
	getCharactersCharacterIdBlueprints(token: string): Promise<V1BlueprintDto[]>
	getCharactersCharacterIdAssets(token: string): Promise<V1AssetDto[]>
}
