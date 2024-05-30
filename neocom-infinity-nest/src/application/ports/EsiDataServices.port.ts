import { GetCharactersCharacterIdAssets200Ok, GetCharactersCharacterIdBlueprints200Ok, GetCharactersCharacterIdMining200Ok } from 'neocom-domain'

export abstract class ESIDataServicesPort {
	public abstract getMiningOperations(characterId: number, token: string): Promise<GetCharactersCharacterIdMining200Ok[]>
	public abstract getCharactersCharacterIdAssets(characterId: number, token: string): Promise<GetCharactersCharacterIdAssets200Ok[]>
	public abstract getCharactersCharacterIdBlueprints(characterId: number, token: string): Promise<GetCharactersCharacterIdBlueprints200Ok[]>
}
