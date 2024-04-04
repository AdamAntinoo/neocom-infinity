// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { OnDestroy } from '@angular/core'
import { Input } from '@angular/core'
import { Subscription } from 'rxjs'
// - DOMAIN
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component';
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { NCVariant } from '@env/NeoComVariants'
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto'
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain'
import { BOMResourceBuilder } from '@app/modules/industry/domain/V1BOMResource.domain'
import { BuildActionDto } from '@domain/industry/dto/BuildActionDto.dto'
import { Resource } from '@app/modules/industry/domain/Resource.domain'
import { Resource2BOMResourceConverter } from '@app/modules/industry/converter/Resource2BOMResource.converter'
import { IndustryResource } from '@app/modules/industry/domain/V1IndustryResource.domain'
import { LookupSolarSystem } from '../../domain/LookupSolarSystem.domain'
import { PlanetaryDataService } from '../../service/PlanetaryData.service'
import { LookupRegion } from '../../domain/LookupRegion.domain'

@Component({
    selector: 'v1-enter-planet-data-page',
    templateUrl: './v1-enter-planet-data-page.component.html',
    styleUrls: ['./v1-enter-planet-data-page.component.scss']
})
export class V1EnterPlanetDataPageComponent extends AppPanelComponent implements OnInit, IRefreshable {
    public self: V1EnterPlanetDataPageComponent
    public searchText: string
    public regions: LookupRegion[] = []
    private solarSystems: LookupSolarSystem[] = []

    constructor(protected planetaryDataService: PlanetaryDataService) {
        super();
        this.self = this
    }

    public ngOnInit(): void {
        console.log(">[V1EnterPlanetDataPageComponent.ngOnInit]");
        this.startDownloading();
        this.setVariant(NCVariant.SOLAR_SYSTEM_LOOKUP)
        this.refresh();
        console.log("<[V1EnterPlanetDataPageComponent.ngOnInit]");
    }
    // - I R E F R E S H A B L E
    public clean(): void {
        this.regions = []
        this.solarSystems = []
    }
    /**
     * Process the list of resources required for the selected blueprint manufacture process and group them into the group categories.
     */
    public refresh(): void {
        this.clean()
        this.backendConnections.push(
            this.planetaryDataService.apiv1_GetAllSolarSystems()
                .subscribe((regions) => {
                    this.regions = regions
                    this.completeDowload(this.planetaryDataService.accessLookupFilteredSystems(''))
                    // Classify the systems into their Regions
                    // const regionsClassification = new Map<string, LookupRegion>()
                    // for (const system of solarSystems) {
                    //     let regionHit : LookupRegion= regionsClassification.get(system.getRegion())
                    //     if ( null== regionHit)regionHit=new LookupRegion()

                    // }
                    // this.solarSystems = solarSystems
                })
        )
    }
    public filterActive(): boolean {
        if (this.searchText) return (this.searchText.length > 2)
        else return false
    }
}
