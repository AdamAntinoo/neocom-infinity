// - CORE
import { Component } from '@angular/core';
import { ViewChild } from '@angular/core';
import { KnownSystem } from '@domain/planetary/KnownSystem.domain';
import { V1KnownPlanetsPanelComponent } from '../../panel/v1-known-planets-panel/v1-known-planets-panel.component';
import { V1OutputResourcesPanelComponent } from '../../panel/v1-output-resources-panel/v1-output-resources-panel.component';
import { V1SelectedPlanetsPanelComponent } from '../../panel/v1-selected-planets-panel/v1-selected-planets-panel.component';
import { PlanetaryDataService } from '../../service/PlanetaryData.service';

@Component({
    selector: 'v1-resource-research-page',
    templateUrl: './v1-resource-research-page.component.html',
    styleUrls: ['./v1-resource-research-page.component.scss']
})
export class V1ResourceResearchPageComponent {
    @ViewChild(V1KnownPlanetsPanelComponent) knownPlanetsComponent: V1KnownPlanetsPanelComponent
    @ViewChild(V1SelectedPlanetsPanelComponent) selectedPlanetsComponent: V1SelectedPlanetsPanelComponent 
    @ViewChild(V1OutputResourcesPanelComponent) outputResourcesComponent: V1OutputResourcesPanelComponent 
    public self: V1ResourceResearchPageComponent
    public selectedSystem: KnownSystem

    constructor(protected planetaryDataService: PlanetaryDataService) {
        // super();
        this.self = this
    }
    // - A P I
    public setSelectedSystem(system: KnownSystem): void {
        console.log('>[V1ResourceResearchPageComponent.setSelectedSystem]> System: ' + system.getName())
        this.selectedSystem = system
        if (this.knownPlanetsComponent) this.knownPlanetsComponent.refresh()
    }

    public processEvent(): void {
        if (this.selectedPlanetsComponent) this.selectedPlanetsComponent.refresh()
        if (this.outputResourcesComponent) this.outputResourcesComponent.refresh()
        // if (this.availableT2ResourcesComponent) this.availableT2ResourcesComponent.refresh()
    }
}
