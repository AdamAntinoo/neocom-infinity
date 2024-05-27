import { Observable } from "rxjs"
import { AxiosResponse } from 'axios';
import { GetCharactersCharacterIdAssets200Ok, GetCharactersCharacterIdBlueprints200Ok } from "../esi-api/model/models"

export interface CharacterServiceInterface {
	getCharactersCharacterIdBlueprints(
		characterId: number,
		datasource?: "tranquility",
		ifNoneMatch?: string,
		page?: number,
		token?: string
	): Observable<AxiosResponse<Array<GetCharactersCharacterIdBlueprints200Ok>>>
	getCharactersCharacterIdAssets(
		characterId: number,
		datasource?: "tranquility",
		ifNoneMatch?: string,
		page?: number,
		token?: string
	): Observable<AxiosResponse<Array<GetCharactersCharacterIdAssets200Ok>>>
}
