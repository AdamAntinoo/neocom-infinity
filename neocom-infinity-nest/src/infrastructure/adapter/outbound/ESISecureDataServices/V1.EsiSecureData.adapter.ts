import { Observable } from 'rxjs'

export class V1ESISecureDataAdapter {
	getCharacterAssets(NEOCOM_TOKEN: string): Observable<Array<V1AssetDto>> {
		const url = `https://esi.evetech.net/latest/characters/{character_id}/assets/`
		// Replace {character_id} with actual character ID extraction logic from NEOCOM_TOKEN
		return this.httpService
			.get<Array<V1AssetDto>>(url, {
				headers: { Authorization: `Bearer ${NEOCOM_TOKEN}` },
				params: extraHttpRequestParams,
			})
			.pipe(map(response => response.data))
	}
}
