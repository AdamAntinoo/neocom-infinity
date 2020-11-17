import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClientWrapperService } from '@bit/innovative.innovative.innovative-core/dist/innovative-core/services/httpclientwrapper.service';
import { PlanetaryDataRecord } from '@domain/planetary-data-record';
import { PlanetaryResource } from '@domain/planetary-resource';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class DataService {
	private DATAV1: string
	private r02T1ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()
	private planet2T2ResourceMap: Map<string, PlanetaryResource[]> = new Map<string, PlanetaryResource[]>()

	constructor(protected http: HttpClient) {
		this.DATAV1 = environment.dataLocation
		// Initialize the R0 -> T1 conversion table
		this.r02T1ResourceConversion.set('Aqueous Liquids R0', new PlanetaryResource({ resourceName: 'Water T1' }))
		this.r02T1ResourceConversion.set('Base Metals R0', new PlanetaryResource({ resourceName: 'Reactive Metals T1' }))
		this.r02T1ResourceConversion.set('Carbon Compounds R0', new PlanetaryResource({ resourceName: 'Biofuels T1' }))
		this.r02T1ResourceConversion.set('Micro Organisms R0', new PlanetaryResource({ resourceName: 'Bacteria T1' }))
		this.r02T1ResourceConversion.set('Noble Metals R0', new PlanetaryResource({ resourceName: 'Precious Metals T1' }))
		// Initialize the Planet->T2 possible resource list
		this.planet2T2ResourceMap.set('barren', [
			new PlanetaryResource( { 
				resourceName: 'Biocells T2',
			dependencies: [
				new PlanetaryResource( {resourceName: 'Biofuels T1'}),
				new PlanetaryResource( {resourceName: 'Precious Metals T1'})
			]}),
			new PlanetaryResource(
				 { resourceName: 'Mechanical Parts T2',
				 dependencies: [
					 new PlanetaryResource( {resourceName: 'Reactive Metals T1'}),
					 new PlanetaryResource( {resourceName: 'Precious Metals T1'})
				 ]}),
			new PlanetaryResource( { resourceName: 'Nanites T2'}),
			new PlanetaryResource( { resourceName: 'Test Cultures T2'}),
			new PlanetaryResource( { resourceName: 'Water-Cooled CPU T2'})
		])
	}
	public apiGetPlanetPIInformation(): Observable<any> {
		const request = this.DATAV1 + 'knownPlanetaryData.json'
		return this.wrapHttpRESOURCECall(request)
		// .pipe(map((data: any) => {
		//     // console.log(">[BackendService.apiIndustryGetFittingDefinition_v1]> Transformation: " +
		//     //     transformer.description)
		//     // const response = transformer.transform(data) as Fitting[]
		//     return data
		// }))
	}
	public getT1Resource4R0(resource: PlanetaryResource): PlanetaryResource {
		const hit = this.r02T1ResourceConversion.get(resource.getName())
		if (hit) return hit.setLevel(resource.getLevel())
		else throw new Error('The R0 resource was not found on the conversion list.')
	}
	public getT2Resources4Planet(planet: PlanetaryDataRecord): PlanetaryResource[] {
		const hit = this.planet2T2ResourceMap.get(planet.getPlanetType().toLowerCase())
		if (hit) {
			for (const resource of hit) {
				resource.setLevel(0.0)
				for (const dependency of resource.getDependencies()) {
					resource.setLevel(resource.getLevel() + dependency.getLevel())
				}
			}
			return hit as PlanetaryResource[]
		} 
		else throw new Error('The T2 resource map was not found on the conversion list.')
	}
	/**
   * Reads a JSON formatted resource. There is no specific convertion to a types class and so can be done on the caller method.
   * @param request the location of the resource file to be read. The resource starts on the /assets/properties location.
   */
	public wrapHttpRESOURCECall(request: string): Observable<any> {
		console.log("><[HttpClientWrapperService.wrapHttpRESOURCECall]> request: " + request);
		return this.http.get(request);
	}
}
