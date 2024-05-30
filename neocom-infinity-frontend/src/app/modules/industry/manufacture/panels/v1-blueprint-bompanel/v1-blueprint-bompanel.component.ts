// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { OnDestroy } from '@angular/core'
import { Input } from '@angular/core'
import { Subscription } from 'rxjs'
// - DOMAIN
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component'
import { V1BlueprintListPageComponent } from '../../../../../industry/pages/v1-blueprint-list-page/v1-blueprint-list-page.component'
import { BOMGroup, BOMGroupBuilder } from '@app/modules/industry/domain/V1BOMGroup.domain'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { NCVariant } from '@env/NeoComVariants'
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto'
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain'
import { BOMResourceBuilder } from '@app/modules/industry/domain/V1BOMResource.domain'
import { BuildActionDto } from '@domain/industry/dto/BuildActionDto.dto'
import { Resource } from '@app/modules/industry/domain/Resource.domain'
import { Resource2BOMResourceConverter } from '@app/modules/industry/converter/Resource2BOMResource.converter'
import { IndustryResource } from '@app/modules/industry/domain/V1IndustryResource.domain'

@Component({
	selector: 'v1-blueprint-bompanel',
	templateUrl: './v1-blueprint-bompanel.component.html',
	styleUrls: ['./v1-blueprint-bompanel.component.scss'],
})
export class V1BlueprintBOMPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
	@Input() container: V1BlueprintListPageComponent
	@Input() blueprint: ProcessedBlueprint
	private bomGroups: BOMGroup[]

	public ngOnInit(): void {
		console.log('>[V1TopBOMPanelComponent.ngOnInit]')
		this.startDownloading()
		this.setVariant(NCVariant.BLUEPRINT_BOM)
		this.refresh()
		console.log('<[V1TopBOMPanelComponent.ngOnInit]')
	}
	// - I R E F R E S H A B L E
	public clean(): void {
		this.bomGroups = []
	}
	/**
	 * Process the list of resources required for the selected blueprint manufacture process and group them into the group categories.
	 */
	public refresh(): void {
		this.clean()
		const converter = new Resource2BOMResourceConverter()
		const resources: IndustryResource[] = []
		for (const resource of this.blueprint.bom) {
			resources.push(converter.convert(resource))
		}
		this.completeDowload(resources)
		// const precursorsGroup: BOMGroup = new BOMGroupBuilder().withLabel('Precursors').build()
		//         precursorsGroup.addResource(new BOMResourceBuilder({
		//             typeId: 655,
		//             name: 'Epithal',
		//             quantity: 1,
		//             price: 1100000
		//         }).withBuildAction(new BuildActionDto({ actionType: 'BUILD' }))
		//             .build())
		//         const mineralsGroup: BOMGroup = new BOMGroupBuilder().withLabel('Minerals').build()
		//         mineralsGroup.addResource(new BOMResourceBuilder({
		//             typeId: 11399,
		//             name: 'Morphite',
		//             quantity: 104,
		//             price: 52910
		//         }).withBuildAction(new BuildActionDto({ actionType: 'BUY' }))
		//             .build())
		//         const planetaryGroup: BOMGroup = new BOMGroupBuilder().withLabel('Planetary Resources').build()
		//         planetaryGroup.addResource(new BOMResourceBuilder({
		//             typeId: 3828,
		//             name: 'Construction Blocks',
		//             quantity: 223,
		//             price: 6899
		//         }).withBuildAction(new BuildActionDto({ actionType: 'BUY' }))
		//             .build())
		//         const moonGroup: BOMGroup = new BOMGroupBuilder().withLabel('Advanced Moon Materials').build()
		//         moonGroup.addResource(new BOMResourceBuilder({
		//             typeId: 655,
		//             name: 'Ion Thruster',
		//             quantity: 75,
		//             price: 46290
		//         }).withBuildAction(new BuildActionDto({ actionType: 'BUY' }))
		//             .build())
		//         moonGroup.addResource(new BOMResourceBuilder({
		//             typeId: 11535,
		//             name: 'Magnetometric Sensor Cluster',
		//             quantity: 134,
		//             price: 38400
		//         }).withBuildAction(new BuildActionDto({ actionType: 'BUY' }))
		//             .build())

		//         this.bomGroups.push(precursorsGroup)
		//         this.bomGroups.push(planetaryGroup)
		//         this.bomGroups.push(mineralsGroup)
		//         this.bomGroups.push(moonGroup)
		//         this.completeDowload(this.bomGroups)
	}
}
