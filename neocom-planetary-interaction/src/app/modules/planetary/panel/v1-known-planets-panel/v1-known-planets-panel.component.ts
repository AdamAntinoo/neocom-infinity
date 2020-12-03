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
	selector: 'npi-v1-known-planets-panel',
	templateUrl: './v1-known-planets-panel.component.html',
	styleUrls: ['./v1-known-planets-panel.component.scss']
})
export class V1KnownPlanetsPanelComponent extends BackgroundEnabledComponent implements OnInit {
	public planetList: PlanetaryDataRecord[] = []

	constructor(protected dataService: DataService) {
		super();
	}

	public ngOnInit(): void {
		this.refresh();
	}
	private clear(): void { }
	private refresh(): void {
		this.clear()
		this.backendConnections.push(
			this.dataService.apiGetPlanetPIInformation().subscribe((dataList) => {
				console.log('Processing planet data')
				for (let index = 0; index < dataList.length; index++) {
					const element = dataList[index];
					const planetData: PlanetaryDataRecord = new PlanetaryDataRecord(element)
					this.planetList.push(planetData)
				}
			})
		)
	}
	// - I N T E R A C T I O N S
	public getPlanetList(): PlanetaryDataRecord[] {
		if (this.planetList.length > 0) return this.planetList
		else return []
	}
	// - D R A G   I N T E R A C T I O N S
	public drop(event: CdkDragDrop<PlanetaryDataRecord[]>) {
		if (event.previousContainer === event.container) {
			moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
		} else {
			transferArrayItem(event.previousContainer.data,
				event.container.data,
				event.previousIndex,
				event.currentIndex);
		}
	}
}
