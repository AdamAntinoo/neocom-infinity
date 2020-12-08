// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { moveItemInArray } from '@angular/cdk/drag-drop';
import { transferArrayItem } from '@angular/cdk/drag-drop';
// - APP
// - DOMAIN
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
import { PlanetaryDataRecord } from '@domain/planetary/planetary-data-record';
import { PlanetaryDataService } from '../../service/PlanetaryData.service';

@Component({
	selector: 'npi-v1-known-planets-panel',
	templateUrl: './v1-known-planets-panel.component.html',
	styleUrls: ['./v1-known-planets-panel.component.scss']
})
export class V1KnownPlanetsPanelComponent extends BackgroundEnabledComponent implements OnInit {
	public planetList: PlanetaryDataRecord[] = []

	constructor(protected planetaryDataService: PlanetaryDataService) {
		super();
	}

	public ngOnInit(): void {
		this.refresh();
	}
	private clear(): void { }
	private refresh(): void {
		this.clear()
		this.backendConnections.push(
			// this.planetaryDataService.apiGetPlanetPIInformation().subscribe((dataList) => {
			// 	console.log('Processing planet data')
			// 	for (let index = 0; index < dataList.length; index++) {
			// 		const element = dataList[index];
			// 		const planetData: PlanetaryDataRecord = new PlanetaryDataRecord(element)
			// 		this.planetList.push(planetData)
			// 	}
			// })
		)
	}
	// - I N T E R A C T I O N S
	public getPlanetList(): PlanetaryDataRecord[] {
		if (this.planetList.length > 0) return this.planetList
		else return []
	}
}
