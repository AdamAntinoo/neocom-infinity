// - CORE
import { Component } from '@angular/core';
import { ViewChild } from '@angular/core';
import { V1SelectedPlanetsPanelComponent } from '../../panel/v1-selected-planets-panel/v1-selected-planets-panel.component';
import { PlanetaryDataService } from '../../service/PlanetaryData.service';

@Component({
  selector: 'v1-resource-research-page',
  templateUrl: './v1-resource-research-page.component.html',
  styleUrls: ['./v1-resource-research-page.component.scss']
})
export class V1ResourceResearchPageComponent  {
    @ViewChild(V1SelectedPlanetsPanelComponent) selectedPlanetsComponent: V1SelectedPlanetsPanelComponent | undefined
    // @ViewChild(V1T2ClassificationPanelComponent) availableT2ResourcesComponent: V1T2ClassificationPanelComponent | undefined
    // @ViewChild(V1SelectedPlanetaryResourcesPanelComponent) selectedT2ResourcesComponent: V1SelectedPlanetaryResourcesPanelComponent | undefined
    public self: V1ResourceResearchPageComponent | undefined

    constructor(protected planetaryDataService: PlanetaryDataService) {
        // super();
        this.self = this
    }

    public processEvent(): void {
        if (this.selectedPlanetsComponent) this.selectedPlanetsComponent.refresh()
        // if (this.availableT2ResourcesComponent) this.availableT2ResourcesComponent.refresh()
        // if (this.availableT2ResourcesComponent) this.availableT2ResourcesComponent.refresh()
    }
}
