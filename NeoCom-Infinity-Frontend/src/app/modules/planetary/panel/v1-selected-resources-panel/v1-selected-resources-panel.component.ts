// - CORE
import { Component, Input } from '@angular/core';
import { OnInit } from '@angular/core';
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { moveItemInArray } from '@angular/cdk/drag-drop';
import { transferArrayItem } from '@angular/cdk/drag-drop';
// - DOMAIN
import { PlanetaryDataRecord } from '@domain/planetary/planetary-data-record';
import { PlanetaryDataService } from '../../service/PlanetaryData.service';
import { DashboardPageComponent } from '../../page/dashboard-page/dashboard-page.component';
import { PlanetaryData } from '@domain/planetary/PlanetaryData.domain';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';
import { PlanetaryResource } from '@domain/planetary/planetary-resource';
import { GeneratedResource } from '@domain/planetary/generated-resource';
import { V1ResourceResearchPageComponent } from '../../page/v1-resource-research-page/v1-resource-research-page.component';

@Component({
    selector: 'v1-selected-resources-panel',
    templateUrl: './v1-selected-resources-panel.component.html',
    styleUrls: ['./v1-selected-resources-panel.component.scss']
})
export class V1SelectedResourcesPanelComponent {
    @Input() store: V1ResourceResearchPageComponent
    public selectedResources: GeneratedResource[] = []

    constructor(protected planetaryService: PlanetaryDataService) { }

    public getSelectedResources(): GeneratedResource[] {
        if (this.selectedResources.length > 0) return this.selectedResources
        else return []
    }
    public onDrop(drop: any) {
        console.log('>[V1SelectedPlanetaryResourcesPanelComponent.onDrop]> Drop: ' + JSON.stringify(drop))
        if (drop.dragData instanceof GeneratedResource) {
            this.selectedResources.push(drop.dragData)
            this.planetaryService.updateResourceList(this.selectedResources)
            if (this.store) this.store.processEvent()
        }
        console.log('<[V1SelectedPlanetaryResourcesPanelComponent.onDrop]')
    }
}
