// - CORE
import { Component } from '@angular/core'
import { ViewChild } from '@angular/core'
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain'
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto'
import { AppStoreService } from '@app/services/appstore.service'
import { IsolationService } from '@innovative/services/isolation.service'
import { V1AvailableBlueprintsPanelComponent } from '../../../modules/industry/manufacture/panels/v1-available-blueprints-panel/v1-available-blueprints-panel.component'

@Component({
	selector: 'v1-blueprint-list-page',
	templateUrl: './v1-blueprint-list-page.component.html',
	styleUrls: ['./v1-blueprint-list-page.component.scss'],
})
export class V1BlueprintListPageComponent {
	// @ViewChild(V1AvailableBlueprintsPanelComponent) availableBlueprints: V1AvailableBlueprintsPanelComponent
	public self: V1BlueprintListPageComponent
	public selectedBlueprint: ProcessedBlueprint

	constructor(protected isolationService: IsolationService, protected appStore: AppStoreService) {
		this.self = this
	}

	// - I N T E R A C T I O N S
	public getPilotId(): number {
		try {
			return this.appStore.getPilotId()
		} catch (exception) {
			this.isolationService.processException(exception)
			return undefined
		}
	}
	/**
	 * Receives events from the contained blueprint list component that will change the current blueprint selection. The selection of a blueprint will trigger the detailed panel. A new selection on the same blueprint will close the panel while a selection on another blueprint will change the panel contents to the new detailed blueprint data.
	 * @param target the selected blueprint on the blueprint list component.
	 */
	public signalBlueprintSelection(target: ProcessedBlueprint): void {
		if (target) {
			console.log('-[V1BlueprintListPageComponent.signalSelection]> Select')
			this.selectedBlueprint = target
		} else {
			console.log('-[V1BlueprintListPageComponent.signalSelection]> UnSelect')
			this.selectedBlueprint = undefined
		}
	}
}
