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
import { PlanetaryDataService } from '@app/services/planetary-data.service';

@Component({
    selector: 'npi-v1-selected-planets-panel',
    templateUrl: './v1-selected-planets-panel.component.html',
    styleUrls: ['./v1-selected-planets-panel.component.scss']
})
export class V1SelectedPlanetsPanelComponent implements OnInit {
    @Input() store: V1PlanetaryPageComponent | undefined
    public selectedPlanets: PlanetaryDataRecord[] = []

    constructor(protected planetaryDataService: PlanetaryDataService) { }

    public ngOnInit(): void {
        this.refresh()
    }
    public refresh(): void {
        this.selectedPlanets = this.planetaryDataService.getSelectedPlanets()
    }
    // - I N T E R A C T I O N S
    public getSelectedPlanets(): PlanetaryDataRecord[] {
        if (this.selectedPlanets.length > 0) return this.selectedPlanets
        else return []
    }
    public onDrop(drop: any) {
        console.log('>[V1SelectedPlanetsPanelComponent.onDrop]> Drop: ' + JSON.stringify(drop))
        if (drop.dragData instanceof PlanetaryDataRecord) {
            this.selectedPlanets.push(drop.dragData)
            this.planetaryDataService.updateSelectedPlanets(this.selectedPlanets)
            if (null != this.store) this.store.processEvent()
        }
        console.log('<[V1SelectedPlanetsPanelComponent.onDrop]')
    }
}
