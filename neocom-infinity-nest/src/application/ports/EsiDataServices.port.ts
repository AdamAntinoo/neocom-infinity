import { GetCharactersCharacterIdMining200Ok } from 'neocom-domain'
import { V1AssetDto } from 'neocom-domain'

export interface ESIDataServicesPort {
	getMiningOperations(characterId: number, token: string): Promise<GetCharactersCharacterIdMining200Ok[]>
	getCharactersCharacterIdAssets(characterId: number, token: string): Promise<V1AssetDto>
}
