import { Component, Input, OnInit } from '@angular/core';
import { DataService } from '@app/services/data-service.service';
import { PlanetaryDataRecord } from '@domain/planetary-data-record';
import { PlanetaryResource } from '@domain/planetary-resource';

@Component({
	selector: 'npi-v1-planet-t2-resources',
	templateUrl: './v1-planet-t2-resources-render.component.html',
	styleUrls: ['./v1-planet-t2-resources-render.component.scss']
})
export class V1PlanetT2ResourcesRenderComponent implements OnInit {
	@Input() planet: PlanetaryDataRecord | undefined
	public t2Resources: PlanetaryResource[] = []

	constructor(protected dataService: DataService) { }

	public ngOnInit(): void {
		this.calculateResources()
	}
	/**
	 * Given a planet get the generated resources and calculate the T1 and T2 possible resources.
	 * T1 resources are straighforward but T2 should be inferred by composition matching.
	 * For each of the found T2 set the expected production index and the sum of the former T1 resource levels.
	 */
	private calculateResources(): void {
		if (this.planet) {
			const r0Resources: PlanetaryResource[] = this.planet.getResources()
			const t1Resources: PlanetaryResource[] = []
			for (let index = 0; index < r0Resources.length; index++) {
				const element = r0Resources[index]
				const t1Match = this.dataService.getT1Resource4R0(element)
				t1Resources.push(t1Match)
			}
			this.t2Resources = this.dataService.getT2Resources4Planet(this.planet)
		}
	}
}
