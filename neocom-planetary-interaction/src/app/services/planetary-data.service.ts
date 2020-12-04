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
export class PlanetaryDataService {
    private planetIconTranslationTable: Map<string, string> = new Map<string, string>()
    private selectedPlanets: PlanetaryDataRecord[] = []
    private selectedResources: GeneratedResource[] = []

    constructor() {
        // Initialize planetary data conversion structures.
        this.planetIconTranslationTable.set('barren', 'planet_barren_102_128_3.png')
        this.planetIconTranslationTable.set('gas', 'planet_gas_103_128_3.png')
        this.planetIconTranslationTable.set('ice', 'planet_ice_103_128_1.png')
        this.planetIconTranslationTable.set('lava', 'planet_lava_102_128_1.png')
        this.planetIconTranslationTable.set('oceanic', 'planet_oceanic_104_128_2.png')
        this.planetIconTranslationTable.set('plasma', 'planet_plasma_103_128_2.png')
        this.planetIconTranslationTable.set('storm', 'planet_storm_102_128_2.png')
        this.planetIconTranslationTable.set('temperate', 'planet_temperate_102_128_4.png')
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
    public getSelectedPlanets(): PlanetaryDataRecord[] {
        return this.selectedPlanets
    }
    public getSelectedResources(): GeneratedResource[] {
        return this.selectedResources
    }
}