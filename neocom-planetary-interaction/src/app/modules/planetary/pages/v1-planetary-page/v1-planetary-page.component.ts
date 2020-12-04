// - CORE
import { Component, ViewChild } from '@angular/core';
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core'
import { DataService } from '@app/services/data-service.service';
import { V1SelectedPlanetsPanelComponent } from '../../panel/v1-selected-planets-panel/v1-selected-planets-panel.component';
import { V1T2ClassificationPanelComponent } from '../../panel/v1-t2-classification-panel/v1-t2-classification-panel.component';
import { V1SelectedPlanetaryResourcesPanelComponent } from '../../panel/v1-selected-planetary-resources-panel/v1-selected-planetary-resources-panel.component';
import { PlanetaryDataService } from '@app/services/planetary-data.service';

@Component({
    selector: 'npi-v1-planetary-page',
    templateUrl: './v1-planetary-page.component.html',
    styleUrls: ['./v1-planetary-page.component.scss']
})
export class V1PlanetaryPageComponent extends BackgroundEnabledComponent {
    @ViewChild(V1SelectedPlanetsPanelComponent) selectedPlanetsComponent: V1SelectedPlanetsPanelComponent | undefined
    @ViewChild(V1T2ClassificationPanelComponent) availableT2ResourcesComponent: V1T2ClassificationPanelComponent | undefined
    @ViewChild(V1SelectedPlanetaryResourcesPanelComponent) selectedT2ResourcesComponent: V1SelectedPlanetaryResourcesPanelComponent | undefined
    public self: V1PlanetaryPageComponent | undefined

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
