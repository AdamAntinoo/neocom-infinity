// - CORE
import { Component, Input } from '@angular/core';
import { OnInit } from '@angular/core';
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { moveItemInArray } from '@angular/cdk/drag-drop';
import { transferArrayItem } from '@angular/cdk/drag-drop';
// - APP
import { DataService } from '@app/services/data-service.service';
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core';
// - DOMAIN
import { PlanetaryDataRecord } from '@domain/planetary-data-record';
import { V1PlanetaryPageComponent } from '../../pages/v1-planetary-page/v1-planetary-page.component';
import { GeneratedResource } from '@domain/generated-resource';
import { PlanetaryDataService } from '@app/services/planetary-data.service';

@Component({
    selector: 'npi-v1-selected-planetary-resources-panel',
    templateUrl: './v1-selected-planetary-resources-panel.component.html',
    styleUrls: ['./v1-selected-planetary-resources-panel.component.scss']
})
export class V1SelectedPlanetaryResourcesPanelComponent {
    @Input() store: V1PlanetaryPageComponent | undefined
    public selectedResources: GeneratedResource[] = []

    constructor(protected planetaryDataService: PlanetaryDataService) { }

    public getSelectedResources(): GeneratedResource[] {
        if (this.selectedResources.length > 0) return this.selectedResources
        else return []
    }
    public onDrop(drop: any) {
        console.log('>[V1SelectedPlanetaryResourcesPanelComponent.onDrop]> Drop: ' + JSON.stringify(drop))
        if (drop.dragData instanceof GeneratedResource) {
            this.selectedResources.push(drop.dragData)
            this.planetaryDataService.updateResourceList(this.selectedResources)
            if (this.store) this.store.processEvent()
        }
        console.log('<[V1SelectedPlanetaryResourcesPanelComponent.onDrop]')
    }
}
