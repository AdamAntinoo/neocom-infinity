import { GetCharactersCharacterIdAssets200Ok, GetCharactersCharacterIdMining200Ok } from 'neocom-domain'

export interface ESIDataServicesPort {
	getMiningOperations(characterId: number, token: string): Promise<GetCharactersCharacterIdMining200Ok[]>
	getCharactersCharacterIdAssets(characterId: number, token: string): Promise<GetCharactersCharacterIdAssets200Ok[]>
}
