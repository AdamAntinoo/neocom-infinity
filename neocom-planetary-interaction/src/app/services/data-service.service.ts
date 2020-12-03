import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClientWrapperService } from '@bit/innovative.innovative.innovative-core/dist/innovative-core/services/httpclientwrapper.service';
import { GeneratedResource } from '@domain/generated-resource';
import { PlanetaryDataRecord } from '@domain/planetary-data-record';
import { PlanetaryResource } from '@domain/planetary-resource';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class DataService {
	private DATAV1: string
	private t12RT0ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()
	private r02T1ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()
	private planet2T2ResourceMap: Map<string, PlanetaryResource[]> = new Map<string, PlanetaryResource[]>()

	constructor(protected http: HttpClient) {
		this.DATAV1 = environment.dataLocation
		// Initialize the R0 -> T1 conversion table
		this.r02T1ResourceConversion.set('Aqueous Liquids R0', new PlanetaryResource({ resourceName: 'Water T1' }))
		this.r02T1ResourceConversion.set('Base Metals R0', new PlanetaryResource({ resourceName: 'Reactive Metals T1' }))
		this.r02T1ResourceConversion.set('Carbon Compounds R0', new PlanetaryResource({ resourceName: 'Biofuels T1' }))
		this.r02T1ResourceConversion.set('Microorganisms R0', new PlanetaryResource({ resourceName: 'Bacteria T1' }))
		this.r02T1ResourceConversion.set('Noble Metals R0', new PlanetaryResource({ resourceName: 'Precious Metals T1' }))

		this.t12RT0ResourceConversion.set('Water T1', new PlanetaryResource({ resourceName: 'Aqueous Liquids R0' }))
		this.t12RT0ResourceConversion.set('Reactive Metals T1', new PlanetaryResource({ resourceName: 'Base Metals R0' }))
		this.t12RT0ResourceConversion.set('Biofuels T1', new PlanetaryResource({ resourceName: 'Carbon Compounds R0' }))
		this.t12RT0ResourceConversion.set('Bacteria T1', new PlanetaryResource({ resourceName: 'Microorganisms R0' }))
		this.t12RT0ResourceConversion.set('Precious Metals T1', new PlanetaryResource({ resourceName: 'Noble Metals R0' }))
		this.t12RT0ResourceConversion.set('Toxic Metals T1', new PlanetaryResource({ resourceName: 'Heavy Metals R0' }))
		this.t12RT0ResourceConversion.set('Chiral Structures T1', new PlanetaryResource({ resourceName: 'Non-CS Crystals R0' }))
		this.t12RT0ResourceConversion.set('Plasmoids T1', new PlanetaryResource({ resourceName: 'Suspended Plasma R0' }))
		this.t12RT0ResourceConversion.set('Electrolytes T1', new PlanetaryResource({ resourceName: 'Ionic Solutions R0' }))
		this.t12RT0ResourceConversion.set('Oxygen T1', new PlanetaryResource({ resourceName: 'Noble Gas R0' }))
		this.t12RT0ResourceConversion.set('Oxidizing Compound T1', new PlanetaryResource({ resourceName: 'Reactive Gas R0' }))
		this.t12RT0ResourceConversion.set('Proteins T1', new PlanetaryResource({ resourceName: 'Complex Organisms R0' }))
		this.t12RT0ResourceConversion.set('Biomass T1', new PlanetaryResource({ resourceName: 'Plantik Colonies R0' }))
		this.t12RT0ResourceConversion.set('Silicon T1', new PlanetaryResource({ resourceName: 'Felsic Magma R0' }))
		this.t12RT0ResourceConversion.set('Industrial Fibers T1', new PlanetaryResource({ resourceName: 'Autotrophs R0' }))
		// Initialize the Planet->T2 possible resource list
		this.planet2T2ResourceMap.set('barren', [
			new PlanetaryResource({
				resourceName: 'Biocells T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Biofuels T1' }),
					new PlanetaryResource({ resourceName: 'Precious Metals T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Mechanical Parts T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
					new PlanetaryResource({ resourceName: 'Precious Metals T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Nanites T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
					new PlanetaryResource({ resourceName: 'Bacteria T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Test Cultures T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Water T1' }),
					new PlanetaryResource({ resourceName: 'Bacteria T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Water-Cooled CPU T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
					new PlanetaryResource({ resourceName: 'Water T1' })
				]
			})
		])
		this.planet2T2ResourceMap.set('plasma', [
			new PlanetaryResource({
				resourceName: 'Construction Blocks T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
					new PlanetaryResource({ resourceName: 'Toxic Metals T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Consumer Electronics T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Toxic Metals T1' }),
					new PlanetaryResource({ resourceName: 'Chiral Structures T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Enriched Uranium T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Toxic Metals T1' }),
					new PlanetaryResource({ resourceName: 'Precious Metals T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Mechanical Parts T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
					new PlanetaryResource({ resourceName: 'Precious Metals T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Transmitter T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Chiral Structures T1' }),
					new PlanetaryResource({ resourceName: 'Plasmoids T1' })
				]
			})
		])
		this.planet2T2ResourceMap.set('gas', [
			new PlanetaryResource({
				resourceName: 'Coolant T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Water T1' }),
					new PlanetaryResource({ resourceName: 'Electrolytes T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Oxides T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Oxygen T1' }),
					new PlanetaryResource({ resourceName: 'Oxidizing Compound T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Synthetic Oil T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Electrolytes T1' }),
					new PlanetaryResource({ resourceName: 'Oxygen T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Water-Cooled CPU T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Water T1' }),
					new PlanetaryResource({ resourceName: 'Reactive Metals T1' })
				]
			})
		])
		this.planet2T2ResourceMap.set('oceanic', [
			new PlanetaryResource({
				resourceName: 'Fertilizer T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Proteins T1' }),
					new PlanetaryResource({ resourceName: 'Bacteria T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Genetically Enhanced Livestock T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Proteins T1' }),
					new PlanetaryResource({ resourceName: 'Biomass T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Livestock T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Biofuels T1' }),
					new PlanetaryResource({ resourceName: 'Proteins T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Test Cultures T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Water T1' }),
					new PlanetaryResource({ resourceName: 'Bacteria T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Viral Agent T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Bacteria T1' }),
					new PlanetaryResource({ resourceName: 'Biomass T1' })
				]
			})
		])
		this.planet2T2ResourceMap.set('lava', [
			new PlanetaryResource({
				resourceName: 'Consumer Electronics T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Toxic Metals T1' }),
					new PlanetaryResource({ resourceName: 'Chiral Structures T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Miniature Electronics T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Silicon T1' }),
					new PlanetaryResource({ resourceName: 'Chiral Structures T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Transmitter T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Chiral Structures T1' }),
					new PlanetaryResource({ resourceName: 'Plasmoids T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Mechanical Parts T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
					new PlanetaryResource({ resourceName: 'Precious Metals T1' })
				]
			})
		])
		this.planet2T2ResourceMap.set('temperate', [
			new PlanetaryResource({
				resourceName: 'Fertilizer T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Proteins T1' }),
					new PlanetaryResource({ resourceName: 'Bacteria T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Livestock T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Biofuels T1' }),
					new PlanetaryResource({ resourceName: 'Proteins T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Polytextiles T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Industrial Fibers T1' }),
					new PlanetaryResource({ resourceName: 'Biofuels T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Test Cultures T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Water T1' }),
					new PlanetaryResource({ resourceName: 'Bacteria T1' })
				]
			})
		])
		this.planet2T2ResourceMap.set('ice', [
			new PlanetaryResource({
				resourceName: 'Supertensile Plastics T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Oxygen T1' }),
					new PlanetaryResource({ resourceName: 'Biomass T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Test Cultures T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Water T1' }),
					new PlanetaryResource({ resourceName: 'Bacteria T1' })
				]
			}),
			new PlanetaryResource({
				resourceName: 'Viral Agent T2',
				dependencies: [
					new PlanetaryResource({ resourceName: 'Bacteria T1' }),
					new PlanetaryResource({ resourceName: 'Biomass T1' })
				]
			})
		])
	}
	public apiGetPlanetPIInformation(): Observable<any> {
		const request = this.DATAV1 + 'knownPlanetaryData.json'
		return this.wrapHttpRESOURCECall(request)
	}
	public getT1Resource4R0(resource: PlanetaryResource): PlanetaryResource {
		const hit = this.r02T1ResourceConversion.get(resource.getName())
		if (hit) return hit.setLevel(resource.getLevel())
		else throw new Error('The R0 resource ' + resource.getName() + ' was not found on the conversion list.')
	}
	public getR0Resource4T1(resource: PlanetaryResource): PlanetaryResource {
		const hit = this.t12RT0ResourceConversion.get(resource.getName())
		if (hit) return hit.setLevel(resource.getLevel())
		else throw new Error('The T1 resource ' + resource.getName() + ' was not found on the conversion list.')
	}
	public getT2Resources4Planet(planet: PlanetaryDataRecord): GeneratedResource[] {
		const hit = this.planet2T2ResourceMap.get(planet.getPlanetType().toLowerCase())
		if (hit) {
			const t2Resource: GeneratedResource[] = []
			for (let t2r of hit)
				t2Resource.push(new GeneratedResource(t2r).setPlanet(planet))  // Duplicate the resource to be added to the list
			for (const resource of t2Resource) {
				console.log('>getT2Resources4Planet> Processing resource: ' + resource.getName() + " - " + resource.getDependencies().length)
				resource.setLevel(0.0)
				for (const dependency of resource.getDependencies()) {
					const r0Index = this.getR0Resource4T1(dependency)
					const r0 = planet.getResource(r0Index.getName())
					if (r0) {
						const level = r0.getLevel()
						resource.setLevel(resource.getLevel() + level)
					}
				}
			}
			return t2Resource
		}
		else throw new Error('The T2 resource ' + planet.getPlanetName() + ' map was not found on the conversion list.')
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
