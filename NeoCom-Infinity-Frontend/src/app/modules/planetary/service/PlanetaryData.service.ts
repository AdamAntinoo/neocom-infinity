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

@Injectable({
    providedIn: 'root'
})
export class PlanetaryDataService {
    private APIV1: string
    private APIV2: string
    private planetIconTranslationTable: Map<string, string> = new Map<string, string>()
    private r0T1ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()
    private t1R0ResourceConversion: Map<string, PlanetaryResource> = new Map<string, PlanetaryResource>()
    private selectedPlanets: PlanetaryDataRecord[] = []
    private selectedResources: GeneratedResource[] = []

    constructor(protected httpService: BackendHttpWrapper) {
        this.APIV1 = environment.serverName + environment.apiVersion1
        this.APIV2 = environment.serverName + environment.apiVersion2
        // Initialize planetary data conversion structures.
        this.initPlanetIcons()
        this.initPlanetaryDependencyMaps()
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


    public getPlanetIconByType(type: string): string {
        const hit = this.planetIconTranslationTable.get(type)
        if (null != hit) return hit
        else return 'defaulticonplaceholder.png'
    }
    public clean(): void {
        this.selectedPlanets = []
        this.selectedResources = []
    }
    public updateSelectedPlanets(newSelectedPlanetList: PlanetaryDataRecord[]): void {
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
    public getSelectedPlanets(): PlanetaryDataRecord[] {
        return this.selectedPlanets
    }
    public getSelectedResources(): GeneratedResource[] {
        return this.selectedResources
    }
}
