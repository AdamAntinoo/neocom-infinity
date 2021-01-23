// - CORE
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { map } from 'rxjs/operators'
import { environment } from '@env/environment'
import { Subject } from 'rxjs'
// - SERVICES
import { BackendHttpWrapper } from '@app/services/backend.httpwrapper'
// - DOMAIN
import { GeneratedResource } from '@domain/planetary/generated-resource'
import { PlanetaryDataRecord } from '@domain/planetary/planetary-data-record'
import { PlanetaryResource } from '@domain/planetary/planetary-resource'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer'
import { constants } from 'fs'
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain'
import { KnownSystem } from '@domain/planetary/KnownSystem.domain'
import { PlanetaryData } from '@domain/planetary/PlanetaryData.domain'
import { BackendService } from '@app/services/backend.service'
import { UniverseHttpWrapper } from '@app/services/universe.httpwrapper'
import { HALResolver } from '@app/services/HALResolver.service'
import { IsolationService } from '@innovative/services/isolation.service'
import { LookupSolarSystem } from '../domain/LookupSolarSystem.domain'
import { LookupRegion } from '../domain/LookupRegion.domain'
import { INamed } from '@innovative/domain/interfaces/INamed.interface'

@Injectable({
    providedIn: 'root'
})
export class PlanetaryDataService extends BackendService {
    private PLANETARYV1: string
    private RESOURCES: string
    // private APIV2: string
    private planetIconTranslationTable: Map<string, string> = new Map<string, string>()
    private planetRawResourcesTable: Map<string, PlanetaryResource[]> = new Map<string, PlanetaryResource[]>()
    private r0T1ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()
    private t1R0ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()
    private planet2T2ResourceMap: Map<string, PlanetaryResource[]> = new Map<string, PlanetaryResource[]>()
    private lookupDownloaded: boolean = false
    // private lookupRegions: Subject<LookupRegion[]> = new Subject<LookupRegion[]>()
    private lookupRegions: LookupRegion[] = []
    private lookupSystems: LookupSolarSystem[] = []

    private selectedPlanets: PlanetaryData[] = []
    private selectedResources: GeneratedResource[] = []

    constructor(
        protected httpUniverseService: UniverseHttpWrapper,
        protected halResolver: HALResolver,
        protected httpService: BackendHttpWrapper,
        protected isolation: IsolationService) {
        super(httpUniverseService, halResolver, httpService, isolation)
        this.PLANETARYV1 = environment.serverName + environment.apiVersion1
        this.RESOURCES = '/assets/properties/'
        // this.APIV2 = environment.serverName + environment.apiVersion2
        // Initialize planetary data conversion structures.
        this.initPlanetIcons()
        this.initPlanetRawResourcesMap()
        this.initPlanetaryDependencyMaps()
        this.initPlanetOutputT2List()
        // this.readSolarSystemCatalog()
        // this.apiv1_GetAllSolarSystems()
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
            new PlanetaryResource({ resourceId: 2270, resourceName: 'Noble Metals R0' })
        ])
        this.planetRawResourcesTable.set('gas', [
            new PlanetaryResource({ resourceId: 2268, resourceName: 'Aqueous Liquids R0' }),
            new PlanetaryResource({ resourceId: 2267, resourceName: 'Base Metals R0' }),
            new PlanetaryResource({ resourceId: 2309, resourceName: 'Ionic Solutions R0' }),
            new PlanetaryResource({ resourceId: 2310, resourceName: 'Noble Gas R0' }),
            new PlanetaryResource({ resourceId: 2311, resourceName: 'Reactive Gas R0' })
        ])
        this.planetRawResourcesTable.set('ice', [
            new PlanetaryResource({ resourceId: 2268, resourceName: 'Aqueous Liquids R0' }),
            new PlanetaryResource({ resourceId: 2272, resourceName: 'Heavy Metals R0' }),
            new PlanetaryResource({ resourceId: 2073, resourceName: 'Microorganisms R0' }),
            new PlanetaryResource({ resourceId: 2310, resourceName: 'Noble Gas R0' }),
            new PlanetaryResource({ resourceId: 2286, resourceName: 'Planktik Colonies R0' })
        ])
        this.planetRawResourcesTable.set('lava', [
            new PlanetaryResource({ resourceId: 2267, resourceName: 'Base Metals R0' }),
            new PlanetaryResource({ resourceId: 2307, resourceName: 'Felsic Magma R0' }),
            new PlanetaryResource({ resourceId: 2272, resourceName: 'Heavy Metals R0' }),
            new PlanetaryResource({ resourceId: 2306, resourceName: 'Non-CS Crystals R0' }),
            new PlanetaryResource({ resourceId: 2308, resourceName: 'Suspended Plasma R0' })
        ])
        this.planetRawResourcesTable.set('oceanic', [
            new PlanetaryResource({ resourceId: 2268, resourceName: 'Aqueous Liquids R0' }),
            new PlanetaryResource({ resourceId: 2288, resourceName: 'Carbon Compounds R0' }),
            new PlanetaryResource({ resourceId: 2287, resourceName: 'Complex Organisms R0' }),
            new PlanetaryResource({ resourceId: 2073, resourceName: 'Microorganisms R0' }),
            new PlanetaryResource({ resourceId: 2286, resourceName: 'Planktik Colonies R0' })
        ])
        this.planetRawResourcesTable.set('plasma', [
            new PlanetaryResource({ resourceId: 2267, resourceName: 'Base Metals R0' }),
            new PlanetaryResource({ resourceId: 2272, resourceName: 'Heavy Metals R0' }),
            new PlanetaryResource({ resourceId: 2270, resourceName: 'Noble Metals R0' }),
            new PlanetaryResource({ resourceId: 2306, resourceName: 'Non-CS Crystals R0' }),
            new PlanetaryResource({ resourceId: 2308, resourceName: 'Suspended Plasma R0' })
        ])
        this.planetRawResourcesTable.set('storm', [
            new PlanetaryResource({ resourceId: 2268, resourceName: 'Aqueous Liquids R0' }),
            new PlanetaryResource({ resourceId: 2267, resourceName: 'Base Metals R0' }),
            new PlanetaryResource({ resourceId: 2309, resourceName: 'Ionic Solutions R0' }),
            new PlanetaryResource({ resourceId: 2310, resourceName: 'Noble Gas R0' }),
            new PlanetaryResource({ resourceId: 2308, resourceName: 'Suspended Plasma R0' })
        ])
        this.planetRawResourcesTable.set('temperate', [
            new PlanetaryResource({ resourceId: 2268, resourceName: 'Aqueous Liquids R0' }),
            new PlanetaryResource({ resourceId: 2305, resourceName: 'Autotrophs R0' }),
            new PlanetaryResource({ resourceId: 2288, resourceName: 'Carbon Compounds R0' }),
            new PlanetaryResource({ resourceId: 2287, resourceName: 'Complex Organisms R0' }),
            new PlanetaryResource({ resourceId: 2073, resourceName: 'Microorganisms R0' })
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
    private initPlanetOutputT2List(): void {
        this.planet2T2ResourceMap.set('barren', [
            new PlanetaryResource({
                resourceId: 229,
                resourceName: 'Biocells T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Biofuels T1' }),
                    new PlanetaryResource({ resourceName: 'Precious Metals T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3689,
                resourceName: 'Mechanical Parts T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
                    new PlanetaryResource({ resourceName: 'Precious Metals T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 2463,
                resourceName: 'Nanites T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
                    new PlanetaryResource({ resourceName: 'Bacteria T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 2319,
                resourceName: 'Test Cultures T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Water T1' }),
                    new PlanetaryResource({ resourceName: 'Bacteria T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 228,
                resourceName: 'Water-Cooled CPU T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
                    new PlanetaryResource({ resourceName: 'Water T1' })
                ]
            })
        ])
        this.planet2T2ResourceMap.set('gas', [
            new PlanetaryResource({
                resourceId: 9832,
                resourceName: 'Coolant T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Water T1' }),
                    new PlanetaryResource({ resourceName: 'Electrolytes T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 2317,
                resourceName: 'Oxides T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Oxygen T1' }),
                    new PlanetaryResource({ resourceName: 'Oxidizing Compound T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3691,
                resourceName: 'Synthetic Oil T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Electrolytes T1' }),
                    new PlanetaryResource({ resourceName: 'Oxygen T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 2328,
                resourceName: 'Water-Cooled CPU T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Water T1' }),
                    new PlanetaryResource({ resourceName: 'Reactive Metals T1' })
                ]
            })
        ])
        this.planet2T2ResourceMap.set('ice', [
            new PlanetaryResource({
                resourceId: 2312,
                resourceName: 'Supertensile Plastics T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Oxygen T1' }),
                    new PlanetaryResource({ resourceName: 'Biomass T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 2319,
                resourceName: 'Test Cultures T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Water T1' }),
                    new PlanetaryResource({ resourceName: 'Bacteria T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3775,
                resourceName: 'Viral Agent T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Bacteria T1' }),
                    new PlanetaryResource({ resourceName: 'Biomass T1' })
                ]
            })
        ])
        this.planet2T2ResourceMap.set('lava', [
            new PlanetaryResource({
                resourceId: 9836,
                resourceName: 'Consumer Electronics T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Toxic Metals T1' }),
                    new PlanetaryResource({ resourceName: 'Chiral Structures T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 9842,
                resourceName: 'Miniature Electronics T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Silicon T1' }),
                    new PlanetaryResource({ resourceName: 'Chiral Structures T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 9840,
                resourceName: 'Transmitter T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Chiral Structures T1' }),
                    new PlanetaryResource({ resourceName: 'Plasmoids T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3689,
                resourceName: 'Mechanical Parts T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
                    new PlanetaryResource({ resourceName: 'Precious Metals T1' })
                ]
            })
        ])
        this.planet2T2ResourceMap.set('oceanic', [
            new PlanetaryResource({
                resourceId: 3693,
                resourceName: 'Fertilizer T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Proteins T1' }),
                    new PlanetaryResource({ resourceName: 'Bacteria T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 15317,
                resourceName: 'Genetically Enhanced Livestock T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Proteins T1' }),
                    new PlanetaryResource({ resourceName: 'Biomass T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3725,
                resourceName: 'Livestock T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Biofuels T1' }),
                    new PlanetaryResource({ resourceName: 'Proteins T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 2319,
                resourceName: 'Test Cultures T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Water T1' }),
                    new PlanetaryResource({ resourceName: 'Bacteria T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3775,
                resourceName: 'Viral Agent T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Bacteria T1' }),
                    new PlanetaryResource({ resourceName: 'Biomass T1' })
                ]
            })
        ])
        this.planet2T2ResourceMap.set('plasma', [
            new PlanetaryResource({
                resourceId: 3828,
                resourceName: 'Construction Blocks T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
                    new PlanetaryResource({ resourceName: 'Toxic Metals T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 9836,
                resourceName: 'Consumer Electronics T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Toxic Metals T1' }),
                    new PlanetaryResource({ resourceName: 'Chiral Structures T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 44,
                resourceName: 'Enriched Uranium T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Toxic Metals T1' }),
                    new PlanetaryResource({ resourceName: 'Precious Metals T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3689,
                resourceName: 'Mechanical Parts T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Reactive Metals T1' }),
                    new PlanetaryResource({ resourceName: 'Precious Metals T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 9840,
                resourceName: 'Transmitter T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Chiral Structures T1' }),
                    new PlanetaryResource({ resourceName: 'Plasmoids T1' })
                ]
            })
        ])
        this.planet2T2ResourceMap.set('storm', [
            new PlanetaryResource({
                resourceId: 9832,
                resourceName: 'Coolant T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Water T1' }),
                    new PlanetaryResource({ resourceName: 'Electrolytes T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 9830,
                resourceName: 'Rocket Fuel T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Electrolytes T1' }),
                    new PlanetaryResource({ resourceName: 'Plasmoids T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 9838,
                resourceName: 'Superconductors T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Water T1' }),
                    new PlanetaryResource({ resourceName: 'Plasmoids T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3691,
                resourceName: 'Synthetic Oil T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Electrolytes T1' }),
                    new PlanetaryResource({ resourceName: 'Oxygen T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 2328,
                resourceName: 'Water-Cooled CPU T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Water T1' }),
                    new PlanetaryResource({ resourceName: 'Reactive Metals T1' })
                ]
            })
        ])
        this.planet2T2ResourceMap.set('temperate', [
            new PlanetaryResource({
                resourceId: 3693,
                resourceName: 'Fertilizer T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Proteins T1' }),
                    new PlanetaryResource({ resourceName: 'Bacteria T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3725,
                resourceName: 'Livestock T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Biofuels T1' }),
                    new PlanetaryResource({ resourceName: 'Proteins T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 3695,
                resourceName: 'Polytextiles T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Industrial Fibers T1' }),
                    new PlanetaryResource({ resourceName: 'Biofuels T1' })
                ]
            }),
            new PlanetaryResource({
                resourceId: 2319,
                resourceName: 'Test Cultures T2',
                dependencies: [
                    new PlanetaryResource({ resourceName: 'Water T1' }),
                    new PlanetaryResource({ resourceName: 'Bacteria T1' })
                ]
            })
        ])
    }
    /**
     * Reads the list of solar systems and composes the solar system list data structure ordered in solar system name alphabetical order.
     */
    private readSolarSystemCatalog(): void {
        const request = this.RESOURCES + 'solar-systems-lookup.json'
        this.httpService.wrapHttpRESOURCECall(request)
            .pipe(map((data: any) => {
                console.log(">[PlanetaryDataService.readSolarSystemCatalog]> Transformation: convert data to LookupSolarSystem")
                const solarSystems: LookupSolarSystem[] = []
                for (let index = 0; index < data.length; index++) {
                    solarSystems.push(new LookupSolarSystem(data[index]))
                }
                // Process the list of solar systems into the Regions
                const regionsClassification = new Map<string, LookupRegion>()
                for (const system of solarSystems) {
                    let regionHit: LookupRegion = regionsClassification.get(system.getRegion())
                    if (null == regionHit) regionHit = new LookupRegion()
                    regionHit.addSystem(system)
                }
                const regions: LookupRegion[] = []
                for (const region of regionsClassification.values())
                    regions.push(region)
                // this.lookupRegions.next(this.sortByName(regions) as LookupRegion[])
                this.lookupSystems = this.sortByName(solarSystems) as LookupSolarSystem[]
            }))
    }
    private sortByName(nameList: INamed[]): INamed[] {
        return nameList.sort((element1, element2) =>
            0 - (element2.getName() > element1.getName() ? -1 : 1)
        )
    }
    private processSolarSystems(systems: LookupSolarSystem[]): void {
        // Process the list of solar systems into the Regions
        const regionsClassification = new Map<string, LookupRegion>()
        for (const system of systems) {
            let regionHit: LookupRegion = regionsClassification.get(system.getRegion())
            if (null == regionHit) {
                regionHit = new LookupRegion({name: system.getRegion()})
                regionsClassification.set(system.getRegion(), regionHit)
            }
            regionHit.addSystem(system)
        }
        const regions: LookupRegion[] = []
        for (const region of regionsClassification.values())
            regions.push(region)
        this.lookupRegions = this.sortByName(regions) as LookupRegion[]
        this.lookupSystems = this.sortByName(systems) as LookupSolarSystem[]
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
    public apiv1_GetAllSolarSystems(): Observable<LookupRegion[]> {
        const request = this.RESOURCES + 'solar-systems-lookup.json'
        return this.httpService.wrapHttpRESOURCECall(request)
            .pipe(map((data: any) => {
                console.log(">[PlanetaryDataService.apiv1_GetAllSolarSystems]> Transformation: convert data to LookupSolarSystem")
                const solarSystems: LookupSolarSystem[] = []
                for (let index = 0; index < data.length; index++) {
                    solarSystems.push(new LookupSolarSystem(data[index]))
                }
                this.processSolarSystems(solarSystems)
                return this.lookupRegions
            }))
    }
    /**
     * Get the list of System records with the systems where we have any kind of Planet data available. The returned information is the minumun data required for rendering a list of data to be selected by the user at the UI.
     */
    public apiv1_GetKnownPlanetSystems(transformer: ResponseTransformer): Observable<KnownSystem[]> {
        const request = this.PLANETARYV1 + '/planetary/systems'
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                console.log(">[PlanetaryDataService.apiv1_GetKnownPlanetSystems]> Transformation: " + transformer.description)
                const response = transformer.transform(data) as KnownSystem[]
                return response
            }))
    }
    public apiv1_GetPlanets4System(systemId: number, transformer: ResponseTransformer): Observable<PlanetaryData[]> {
        const request = this.PLANETARYV1 + '/planetary/planets/' + systemId
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                console.log(">[PlanetaryDataService.apiv1_GetPlanets4System]> Transformation: " + transformer.description)
                const response = transformer.transform(data) as PlanetaryData[]
                return response
            }))
    }

    // - D A T A   A C C E S S 
    // public accessLookupRegions(): Observable<LookupRegion[]> {
    //     if (!this.lookupDownloaded) this.readSolarSystemCatalog()
    //     return this.lookupRegions
    // }
    public accessLookupFilteredSystems(filter: string): LookupSolarSystem[] {
        return this.sortByName(this.lookupSystems) as LookupSolarSystem[]
        // const filteredSystems: LookupSolarSystem[] = []
        // for (const system of this.lookupSystems) {
        //     if (system.getName().includes(filter)) filteredSystems.push(system)
        // }
        // return this.sortByName(filteredSystems) as LookupSolarSystem[]
    }
    public getPlanetResource4PlanetType(planetType: string, resourceIndex: number): PlanetaryResource {
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
            console.log('-[getT2Resources4Planet]>Processing type: ' + planet.getPlanetType())
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
