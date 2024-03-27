import { Component, Input, OnInit } from '@angular/core';
import { PlanetaryDataService } from '@app/services/planetary-data.service';
import { PlanetaryDataRecord } from '@domain/planetary-data-record';
import { PlanetaryResource } from '@domain/planetary-resource';

@Component({
	selector: 'npi-v1-planet-data',
	templateUrl: './v1-planet-data-render.component.html',
	styleUrls: ['./v1-planet-data-render.component.scss']
})
export class V1PlanetDataRenderComponent {
	@Input() planet: PlanetaryDataRecord | undefined

	constructor(protected planetService: PlanetaryDataService) { }

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
	protected getPlanetType () : string {
		if (this.planet) return   this.planet.getPlanetType()
		else return 'not-found'
	}
	public getPlanetIcon(): string {
		return '/assets/resources-ui/drawable/' + this.planetService.getPlanetIconByType(this.getPlanetType().toLowerCase())
	}
}
