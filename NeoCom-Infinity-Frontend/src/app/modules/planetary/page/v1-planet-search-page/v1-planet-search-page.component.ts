import { Component, OnInit } from '@angular/core';
import { V1SelectedPlanetsPanelComponent } from '../../panel/v1-selected-planets-panel/v1-selected-planets-panel.component';

@Component({
  selector: 'v1-planet-search-page',
  templateUrl: './v1-planet-search-page.component.html',
  styleUrls: ['./v1-planet-search-page.component.scss']
})
export class V1PlanetSearchPageComponent  {
    @ViewChild(V1SelectedPlanetsPanelComponent) selectedPlanetsComponent: V1SelectedPlanetsPanelComponent | undefined
    // @ViewChild(V1T2ClassificationPanelComponent) availableT2ResourcesComponent: V1T2ClassificationPanelComponent | undefined
    // @ViewChild(V1SelectedPlanetaryResourcesPanelComponent) selectedT2ResourcesComponent: V1SelectedPlanetaryResourcesPanelComponent | undefined
    public self: V1PlanetSearchPageComponent | undefined

    constructor(
        protected dataService: DataService,
        protected planetaryDataService: PlanetaryDataService) {
        super();
        this.self = this
    }

    public processEvent(): void {
        if (this.selectedPlanetsComponent) this.selectedPlanetsComponent.refresh()
        if (this.availableT2ResourcesComponent) this.availableT2ResourcesComponent.refresh()
        if (this.availableT2ResourcesComponent) this.availableT2ResourcesComponent.refresh()
    }
}
