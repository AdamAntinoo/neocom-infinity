import { Observable } from "rxjs"
import { GetCharactersCharacterIdAssets200Ok, GetCharactersCharacterIdBlueprints200Ok } from "../esi-api/model/models"

export interface CharacterServiceInterface {
	getCharactersCharacterIdBlueprints(token: string): Observable<GetCharactersCharacterIdBlueprints200Ok[]>
	getCharactersCharacterIdAssets(token: string): Observable<GetCharactersCharacterIdAssets200Ok[]>
}
