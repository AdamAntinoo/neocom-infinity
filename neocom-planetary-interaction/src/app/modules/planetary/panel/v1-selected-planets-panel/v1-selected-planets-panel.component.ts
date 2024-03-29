// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { moveItemInArray } from '@angular/cdk/drag-drop';
import { transferArrayItem } from '@angular/cdk/drag-drop';
// - APP
import { DataService } from '@app/services/data-service.service';
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core';
// - DOMAIN
import { PlanetaryDataRecord } from '@domain/planetary-data-record';

@Component({
	selector: 'npi-v1-selected-planets-panel',
	templateUrl: './v1-selected-planets-panel.component.html',
	styleUrls: ['./v1-selected-planets-panel.component.scss']
})
export class V1SelectedPlanetsPanelComponent implements OnInit {
	public selectedPlanets: PlanetaryDataRecord[] = []
	constructor() { }

	public ngOnInit(): void {
		this.refresh()
	}
	public refresh(): void {
		// Load the list of already selected planets. This is stored on local storage.

	}
	// - I N T E R A C T I O N S
	public getSelectedPlanets(): PlanetaryDataRecord[] {
		if (this.selectedPlanets.length > 0) return this.selectedPlanets
		else return []
	}
	public onDrop(drop: any) {
        console.log('>[V1SelectedPlanetsPanelComponent.onDrop]> Drop: ' + JSON.stringify(drop))
        if (drop.dragData instanceof PlanetaryDataRecord) this.selectedPlanets.push(drop.dragData)
        // if (drop.dragData instanceof Model) this.request.addContent(drop.dragData)
        console.log('<>>[V1NewRequestPanelComponent.onDrop]')
    }
}
