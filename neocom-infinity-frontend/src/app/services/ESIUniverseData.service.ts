// - CORE
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { map } from 'rxjs/operators'
import { environment } from '@env/environment'
// - HTTP PACKAGE
import { ESIUniverseHttpWrapper } from './ESUniverse.HttpWrapper.service'
// - DOMAIN
import { UniverseSystem } from '@domain/esi/UniverseSystem.esi'
import { UniversePlanet } from '@domain/esi/UniversePlanet.esi'
import { UniverseType } from '@domain/esi/UniverseType.esi'

@Injectable({
	providedIn: 'root',
})
/** @deprecated */
export class ESIUniverseDataService {
	private ESIDATA: string
	private ESIUNIVERSE: string

	constructor(protected httpService: ESIUniverseHttpWrapper) {
		/** @ deprecated
		 */
		this.ESIDATA = environment.esiData
		this.ESIUNIVERSE = environment.esiData + 'universe/'
	}

	// - M A R K E T
	public apiMarketSearchRegionOrders(regionId: number, typeId: number): Observable<any[]> {
		const request = this.ESIDATA + 'markets/' + regionId + '/orders/?datasource=tranquility&order_type=all&page=1&type_id=' + typeId
		return this.httpService.wrapHttpGETCall(request)
	}

	// - S E A R C H
	public apiUniverseSearchSystem(targetName: string): Observable<UniverseSystem> {
		const request = this.ESIDATA + 'search/?categories=solar_system&strict=false&search=' + targetName + this.addEsiQueryParameters()
		return this.httpService.wrapHttpGETCall(request).pipe(
			map((data: any) => {
				const response = new UniverseSystem(data)
				return response
			}),
		)
	}
	// - U N I V E R S E
	public apiEsiUniverseSystemsData(systemId: number): Observable<UniverseSystem> {
		const request = this.ESIUNIVERSE + 'systems/' + systemId + this.addEsiQueryParameters()
		return this.httpService.wrapHttpGETCall(request).pipe(
			map((data: any) => {
				const response = new UniverseSystem(data)
				return response
			}),
		)
	}
	public apiEsiUniversePlanetsData(planetId: number): Observable<UniversePlanet> {
		const request = this.ESIUNIVERSE + 'planets/' + planetId + this.addEsiQueryParameters()
		return this.httpService.wrapHttpGETCall(request).pipe(
			map((data: any) => {
				const response = new UniversePlanet(data)
				return response
			}),
		)
	}
	public apiEsiUniverseTypesData(typeId: number): Observable<UniverseType> {
		const request = this.ESIUNIVERSE + 'types/' + typeId + this.addEsiQueryParameters()
		return this.httpService.wrapHttpGETCall(request).pipe(
			map((data: any) => {
				const response = new UniverseType(data)
				return response
			}),
		)
	}
	private addEsiQueryParameters(): string {
		return '?datasource=tranquility&language=en-us'
	}
}
