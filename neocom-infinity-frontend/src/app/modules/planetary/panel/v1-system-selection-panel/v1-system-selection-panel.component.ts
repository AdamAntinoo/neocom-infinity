// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { OnDestroy } from '@angular/core'
import { Input } from '@angular/core'
import { Subscription } from 'rxjs'
// - DOMAIN
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { NCVariant } from '@env/NeoComVariants'
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto'
import { ProcessedBlueprint } from '@app/industry/domain/V1ProcessedBlueprint.domain'
import { BOMResourceBuilder } from '@app/modules/industry/domain/V1BOMResource.domain'
import { BuildActionDto } from '@domain/industry/dto/BuildActionDto.dto'
import { Resource } from '@app/modules/industry/domain/Resource.domain'
import { Resource2BOMResourceConverter } from '@app/modules/industry/converter/Resource2BOMResource.converter'
import { IndustryResource } from '@app/modules/industry/domain/V1IndustryResource.domain'
import { LookupSolarSystem } from '../../domain/LookupSolarSystem.domain'
import { PlanetaryDataService } from '../../service/PlanetaryData.service'
import { LookupRegion } from '../../domain/LookupRegion.domain'
import { V1EnterPlanetDataPageComponent } from '../../page/v1-enter-planet-data-page/v1-enter-planet-data-page.component'
import { INamed } from '@innovative/domain/interfaces/INamed.interface'

@Component({
	selector: 'v1-system-selection-panel',
	templateUrl: './v1-system-selection-panel.component.html',
	styleUrls: ['./v1-system-selection-panel.component.scss'],
})
export class V1SystemSelectionPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
	// public self: V1EnterPlanetDataPageComponent
	public systemFilter: string
	public regions: LookupRegion[] = []
	private solarSystems: LookupSolarSystem[] = []

	constructor(protected planetaryDataService: PlanetaryDataService) {
		super()
		// this.self = this
	}

	public ngOnInit(): void {
		console.log('>[V1SystemSelectionPanelComponent.ngOnInit]')
		this.startDownloading()
		this.setVariant(NCVariant.SOLAR_SYSTEM_LOOKUP)
		this.refresh()
		console.log('<[V1SystemSelectionPanelComponent.ngOnInit]')
	}
	// - I R E F R E S H A B L E
	public clean(): void {
		this.regions = []
		this.solarSystems = []
	}
	/**
	 * Process the list of resources required for the selected blueprint manufacture process and group them into the group categories.
	 */
	public refresh(): void {
		this.clean()
		this.backendConnections.push(
			this.planetaryDataService.apiv1_GetAllSolarSystems().subscribe(regions => {
				this.regions = this.sortByName(regions) as LookupRegion[]
				this.completeDowload(this.planetaryDataService.accessLookupFilteredSystems(''))
			}),
		)
	}
	public filterActive(): boolean {
		if (this.systemFilter) return this.systemFilter.length > 2
		else return false
	}
	private sortByName(nameList: INamed[]): INamed[] {
		return nameList.sort((element1, element2) => 0 - (element2.getName() < element1.getName() ? -1 : 1))
	}
}
