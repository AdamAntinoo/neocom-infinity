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
import { V1ResourceResearchPageComponent } from '../../page/v1-resource-research-page/v1-resource-research-page.component';

@Component({
    selector: 'v1-selected-planets-panel',
    templateUrl: './v1-selected-planets-panel.component.html',
    styleUrls: ['./v1-selected-planets-panel.component.scss']
})
export class V1SelectedPlanetsPanelComponent implements OnInit {
    @Input() store: V1ResourceResearchPageComponent 
    public selectedPlanets: PlanetaryData[] = []

    constructor(protected planetaryDataService: PlanetaryDataService) { }

    public ngOnInit(): void {
        this.refresh()
    }
    public refresh(): void {
        this.selectedPlanets = this.planetaryDataService.getSelectedPlanets()
    }
    // - I N T E R A C T I O N S
    public getSelectedPlanets(): PlanetaryData[] {
        if (this.selectedPlanets.length > 0) return this.selectedPlanets
        else return []
    }
    public onDrop(drop: any) {
        console.log('>[V1SelectedPlanetsPanelComponent.onDrop]> Drop: ' + JSON.stringify(drop))
        if (drop.dragData instanceof PlanetaryData) {
            console.log('>[V1SelectedPlanetsPanelComponent.onDrop]> Dropping a planet')
            this.selectedPlanets.push(drop.dragData)
            this.planetaryDataService.updateSelectedPlanets(this.selectedPlanets)
            if (null != this.store) this.store.processEvent()
        }
        console.log('<[V1SelectedPlanetsPanelComponent.onDrop]')
    }
}
