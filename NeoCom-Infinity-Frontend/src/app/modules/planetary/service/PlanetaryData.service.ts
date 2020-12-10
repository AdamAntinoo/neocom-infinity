// - CORE
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'
import { environment } from '@env/environment'
// - SERVICES
import { BackendHttpWrapper } from '@app/services/Backend.HttpWrapper.service';
// - DOMAIN
import { GeneratedResource } from '@domain/planetary/generated-resource';
import { PlanetaryDataRecord } from '@domain/planetary/planetary-data-record';
import { PlanetaryResource } from '@domain/planetary/planetary-resource';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
import { constants } from 'fs';
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain';
import { KnownSystem } from '@domain/planetary/KnownSystem.domain';
import { PlanetaryData } from '@domain/planetary/PlanetaryData.domain';

@Injectable({
    providedIn: 'root'
})
export class PlanetaryDataService {
    private APIV1: string
    private APIV2: string
    private planetIconTranslationTable: Map<string, string> = new Map<string, string>()
    private planetRawResourcesTable: Map<string, PlanetaryResource[]> = new Map<string, PlanetaryResource[]>()
    private r0T1ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()
    private t1R0ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()
    private planet2T2ResourceMap: Map<string, PlanetaryResource[]> = new Map<string, PlanetaryResource[]>()
	// private t1R0ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()

    private selectedPlanets: PlanetaryData[] = []
    private selectedResources: GeneratedResource[] = []

    constructor(protected httpService: BackendHttpWrapper) {
        this.APIV1 = environment.serverName + environment.apiVersion1
        this.APIV2 = environment.serverName + environment.apiVersion2
        // Initialize planetary data conversion structures.
        this.initPlanetIcons()
        this.initPlanetRawResourcesMap()
        this.initPlanetaryDependencyMaps()
        this.initPlanetOutputT2List()
    }
    private initPlanetIcons(): void {
        this.planetIconTranslationTable.set('barren', 'planet_barren_102_128_3.png')
        this.planetIconTranslationTable.set('gas', 'planet_gas_103_128_3.png')
        this.planetIconTranslationTable.set('ice', 'planet_ice_103_128_1.png')
        this.planetIconTranslationTable.set('lava', 'planet_lava_102_128_1.png')
        this.planetIconTranslationTable.set('oceanic', 'planet_oceanic_104_128_2.png')
        this.planetIconTranslationTable.set('plasma', 'planet_plasma_103_128_2.png')
        this.planetIconTranslationTable.set('storm', 'planet_storm_102_128_2.png')
        this.planetIconTranslationTable.set('temperate', 'planet_temperate_102_128_4.png')
    }
    private initPlanetRawResourcesMap(): void {
        this.planetRawResourcesTable.set('barren', [
            new PlanetaryResource({ resourceId: 2268, resourceName: 'Aqueous Liquids R0' }),
            new PlanetaryResource({ resourceId: 2267, resourceName: 'Base Metals R0' }),
            new PlanetaryResource({ resourceId: 2288, resourceName: 'Carbon Compounds R0' }),
            new PlanetaryResource({ resourceId: 2073, resourceName: 'Microorganisms R0' }),
            new PlanetaryResource({ resourceId: 2270, resourceName: 'Noble Metals R0' }),
        ])
    }
    private initPlanetaryDependencyMaps(): void {
        // Initialize the R0 -> T1 conversion table
        this.r0T1ResourceConversion.set('Aqueous Liquids R0', new PlanetaryResource({ resourceName: 'Water T1' }))
        this.r0T1ResourceConversion.set('Base Metals R0', new PlanetaryResource({ resourceName: 'Reactive Metals T1' }))
        this.r0T1ResourceConversion.set('Carbon Compounds R0', new PlanetaryResource({ resourceName: 'Biofuels T1' }))
        this.r0T1ResourceConversion.set('Microorganisms R0', new PlanetaryResource({ resourceName: 'Bacteria T1' }))
        this.r0T1ResourceConversion.set('Noble Metals R0', new PlanetaryResource({ resourceName: 'Precious Metals T1' }))
        // Initialize T1 -> R0 resource dependency table
        this.t1R0ResourceConversion.set('Water T1', new PlanetaryResource({ resourceName: 'Aqueous Liquids R0' }))
        this.t1R0ResourceConversion.set('Reactive Metals T1', new PlanetaryResource({ resourceName: 'Base Metals R0' }))
        this.t1R0ResourceConversion.set('Biofuels T1', new PlanetaryResource({ resourceName: 'Carbon Compounds R0' }))
        this.t1R0ResourceConversion.set('Bacteria T1', new PlanetaryResource({ resourceName: 'Microorganisms R0' }))
        this.t1R0ResourceConversion.set('Precious Metals T1', new PlanetaryResource({ resourceName: 'Noble Metals R0' }))
        this.t1R0ResourceConversion.set('Toxic Metals T1', new PlanetaryResource({ resourceName: 'Heavy Metals R0' }))
        this.t1R0ResourceConversion.set('Chiral Structures T1', new PlanetaryResource({ resourceName: 'Non-CS Crystals R0' }))
        this.t1R0ResourceConversion.set('Plasmoids T1', new PlanetaryResource({ resourceName: 'Suspended Plasma R0' }))
        this.t1R0ResourceConversion.set('Electrolytes T1', new PlanetaryResource({ resourceName: 'Ionic Solutions R0' }))
        this.t1R0ResourceConversion.set('Oxygen T1', new PlanetaryResource({ resourceName: 'Noble Gas R0' }))
        this.t1R0ResourceConversion.set('Oxidizing Compound T1', new PlanetaryResource({ resourceName: 'Reactive Gas R0' }))
        this.t1R0ResourceConversion.set('Proteins T1', new PlanetaryResource({ resourceName: 'Complex Organisms R0' }))
        this.t1R0ResourceConversion.set('Biomass T1', new PlanetaryResource({ resourceName: 'Plantik Colonies R0' }))
        this.t1R0ResourceConversion.set('Silicon T1', new PlanetaryResource({ resourceName: 'Felsic Magma R0' }))
        this.t1R0ResourceConversion.set('Industrial Fibers T1', new PlanetaryResource({ resourceName: 'Autotrophs R0' }))
    }
    /**
     * Initialize the Planet->T2 possible resource list
     */
    private initPlanetOutputT2List():void{
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
    // private initT1ToR0ConversionTable () : void{
    //     this.t1R0ResourceConversion.set('Water T1', new PlanetaryResource({ resourceName: 'Aqueous Liquids R0' }))
	// 	this.t1R0ResourceConversion.set('Reactive Metals T1', new PlanetaryResource({ resourceName: 'Base Metals R0' }))
	// 	this.t1R0ResourceConversion.set('Biofuels T1', new PlanetaryResource({ resourceName: 'Carbon Compounds R0' }))
	// 	this.t1R0ResourceConversion.set('Bacteria T1', new PlanetaryResource({ resourceName: 'Microorganisms R0' }))
	// 	this.t1R0ResourceConversion.set('Precious Metals T1', new PlanetaryResource({ resourceName: 'Noble Metals R0' }))
	// 	this.t1R0ResourceConversion.set('Toxic Metals T1', new PlanetaryResource({ resourceName: 'Heavy Metals R0' }))
	// 	this.t1R0ResourceConversion.set('Chiral Structures T1', new PlanetaryResource({ resourceName: 'Non-CS Crystals R0' }))
	// 	this.t1R0ResourceConversion.set('Plasmoids T1', new PlanetaryResource({ resourceName: 'Suspended Plasma R0' }))
	// 	this.t1R0ResourceConversion.set('Electrolytes T1', new PlanetaryResource({ resourceName: 'Ionic Solutions R0' }))
	// 	this.t1R0ResourceConversion.set('Oxygen T1', new PlanetaryResource({ resourceName: 'Noble Gas R0' }))
	// 	this.t1R0ResourceConversion.set('Oxidizing Compound T1', new PlanetaryResource({ resourceName: 'Reactive Gas R0' }))
	// 	this.t1R0ResourceConversion.set('Proteins T1', new PlanetaryResource({ resourceName: 'Complex Organisms R0' }))
	// 	this.t1R0ResourceConversion.set('Biomass T1', new PlanetaryResource({ resourceName: 'Plantik Colonies R0' }))
	// 	this.t1R0ResourceConversion.set('Silicon T1', new PlanetaryResource({ resourceName: 'Felsic Magma R0' }))
	// 	this.t1R0ResourceConversion.set('Industrial Fibers T1', new PlanetaryResource({ resourceName: 'Autotrophs R0' }))
    // }

    // - C O N F I G U R A T I O N
    public readPlanetaryFeatures(transformer: ResponseTransformer): Observable<NeoComFeature[]> {
        const request = '/assets/properties/planetary-features.json'
        return this.httpService.wrapHttpRESOURCECall(request)
            .pipe(
                map((data) => {
                    const featureList = transformer.transform(data)
                    return featureList
                })
            )
    }

    // - A P I
    /**
     * Get the list of System records with the systems where we have any kind of Planet data available. The returned information is the minumun data required for rendering a list of data to be selected by the user at the UI.
     */
    public apiv1_GetKnownPlanetSystems(transformer: ResponseTransformer): Observable<KnownSystem[]> {
        const request = this.APIV1 + '/planetary/systems'
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                console.log(">[PlanetaryDataService.apiv1_GetKnownPlanetSystems]> Transformation: " + transformer.description)
                const response = transformer.transform(data) as KnownSystem[]
                return response
            }))
    }
    public apiv1_GetPlanets4System(systemId: number, transformer: ResponseTransformer): Observable<PlanetaryData[]> {
        const request = this.APIV1 + '/planetary/planets/' + systemId
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                console.log(">[PlanetaryDataService.apiv1_GetPlanets4System]> Transformation: " + transformer.description)
                const response = transformer.transform(data) as PlanetaryData[]
                return response
            }))
    }

    // - D A T A   A C C E S S 
    public getPlanetResource4PlanetType(planetType: string, resourceIndex: number): PlanetaryResource | undefined{
        const hit = this.planetRawResourcesTable.get(planetType)
        if (hit) return hit[resourceIndex]
        else return undefined
    }

    public getPlanetIconByType(type: string): string {
        const hit = this.planetIconTranslationTable.get(type)
        if (null != hit) return hit
        else return 'defaulticonplaceholder.png'
    }
    public clean(): void {
        this.selectedPlanets = []
        this.selectedResources = []
    }
    public updateSelectedPlanets(newSelectedPlanetList: PlanetaryData[]): void {
        console.log('V1PlanetaryPageComponent.updatePlanetList')
        this.selectedPlanets = newSelectedPlanetList
    }
    public updateResourceList(newSelectedResourceList: GeneratedResource[]): void {
        console.log('V1PlanetaryPageComponent.updateResourceList')
        this.selectedResources = newSelectedResourceList
    }
    /**
     * Load the list of already selected planets. This is stored on local storage.
     */
    public getSelectedPlanets(): PlanetaryData[] {
        return this.selectedPlanets
    }
    public getSelectedResources(): GeneratedResource[] {
        return this.selectedResources
    }
    public getT2Resources4Planet(planet: PlanetaryData): GeneratedResource[] {
		const hit = this.planet2T2ResourceMap.get(planet.getPlanetType().toLowerCase())
		if (hit) {
            console.log('-[getT2Resources4Planet]>Processing type: '+planet.getPlanetType())
			const t2Resource: GeneratedResource[] = []
			for (let t2r of hit)
				t2Resource.push(new GeneratedResource(t2r).setPlanet(planet))  // Duplicate the resource to be added to the list
			for (const resource of t2Resource) {
				console.log('-[getT2Resources4Planet]> Processing resource: ' + resource.getName() + " - " + resource.getDependencies().length)
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
    public getR0Resource4T1(resource: PlanetaryResource): PlanetaryResource {
		const hit = this.t1R0ResourceConversion.get(resource.getName())
		if (hit) return hit.setLevel(resource.getLevel())
		else throw new Error('The T1 resource ' + resource.getName() + ' was not found on the conversion list.')
	}
}
