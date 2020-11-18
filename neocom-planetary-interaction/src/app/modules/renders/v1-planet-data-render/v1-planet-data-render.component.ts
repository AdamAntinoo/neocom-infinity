import { Component, Input, OnInit } from '@angular/core';
import { PlanetaryDataRecord } from '@domain/planetary-data-record';
import { PlanetaryResource } from '@domain/planetary-resource';

@Component({
	selector: 'npi-v1-planet-data',
	templateUrl: './v1-planet-data-render.component.html',
	styleUrls: ['./v1-planet-data-render.component.scss']
})
export class V1PlanetDataRenderComponent {
	@Input() planet: PlanetaryDataRecord | undefined

	public getPlanetName(): string {
		if (this.planet)
			return this.planet.getPlanetName() + ' ' + this.planet.getPlanetSuffix()
		else return '-'
	}
	public getPlanetClass(): string {
		if (this.planet)
			return this.planet.getPlanetType().toUpperCase()
		else return '-'
	}
	public getPlanetResources(): PlanetaryResource[] {
		if (this.planet) return this.planet.getResources()
		else return []
	}
}
