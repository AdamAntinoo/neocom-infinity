import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { Input } from '@angular/core'
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { NCVariant } from '@env/NeoComVariants'
import { IsolationService } from '@innovative/services/isolation.service'
import { IndustryService } from '@app/modules/industry/service/IndustryService.service'
import { HttpErrorResponse } from '@angular/common/http'
import { environment } from '@env/environment'
import { NeoComCredential } from '@domain/NeoComCredential.domain'
import { NeoComException } from '@innovative/domain/NeoComException'
import { ProcessedBlueprintsToLocationListUseCase } from '@app/use-cases/ProcessedBlueprintsToLocationList.usecase'
import { V1BlueprintListPageComponent } from '@app/industry/pages/v1-blueprint-list-page/v1-blueprint-list-page.component'
import { V2ProcessedBlueprint } from '@app/industry/domain/v2.ProcessedBlueprint.domain'
import { STORAGE_LINKS } from '@env/PlatformConstants'

@Component({
	selector: 'v2-available-blueprints-panel',
	templateUrl: './v2-available-blueprints-panel.component.html',
	styleUrls: ['./v2-available-blueprints-panel.component.scss'],
})
export class V2AvailableBlueprintsPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
	@Input() container: V1BlueprintListPageComponent

	private blueprintList: V2ProcessedBlueprint[] = []

	constructor(
		protected isolationService: IsolationService,
		protected industryService: IndustryService,
		private readonly useCase: ProcessedBlueprintsToLocationListUseCase,
	) {
		super()
	}
	public ngOnInit(): void {
		console.log('>[V2AvailableBlueprintsPanelComponent.ngOnInit]')
		this.setVariant(NCVariant.AVAILABLE_BLUEPRINTS)
		super.ngOnInit()
		console.log('<[V2AvailableBlueprintsPanelComponent.ngOnInit]')
	}

	// - I V I E W E R
	/**
	 * When the user hovers over a blueprint item on the list there is an event and that blueprint node element is received on this method. On this moment we should open a second panel and disply the BOM for the target blueprint.
	 * @param target the node hovered.
	 */
	public enterSelected(target: any): void {
		console.log('-[enterSelected.enterSelected]')
		if (target instanceof V2ProcessedBlueprint) {
			if (this.container) this.container.signalBlueprintSelection(target)
		}
	}
	/**
	 * When the selection changes this method is calles. Use the message to notify the page of the current selected blueprint if any.
	 */
	public fireSelectionChanged(): void {
		const targetList = this.getSelection()
		if (targetList && targetList.length > 0) {
			const target = targetList[0]
			if (target) {
				if (target instanceof V2ProcessedBlueprint) this.container.signalBlueprintSelection(target)
			} else this.container.signalBlueprintSelection(undefined)
		}
	}

	// - R E F R E S H A B L E
	public clean(): void {}
	public refresh(): void {
		console.log('>[V2AvailableBlueprintsPanelComponent.refresh]')
		this.clean()
		this.accessProcessedBueprints()
		console.log('<[V2AvailableBlueprintsPanelComponent.refresh]')
	}
	/**
	 * Download the list of blueprints, converting them into a list of <code>ProcessedBlueprint</code> that can be rendered to show the manufacture costs and its comparison to the manufactured item cost.
	 * During blueprint processing there are pending events that complete when the blueprint item is downloaded. This should fire some sort of refresh event that should redraw the page.
	 */
	private accessProcessedBueprints(): void {
		this.backendConnections.push(
			this.useCase.execute({ token: '-now-defined-as-a-cookie', pilotId: this.getPilotId() }).subscribe(
				blueprints => {
					console.log('-download completed')
					this.blueprintList = this.sortBlueprintByCostIndex(blueprints)
					console.log('-[V2AvailableBlueprintsPanelComponent.accessProcessedBueprints]> Nodes downloaded: ' + this.blueprintList.length)
					this.completeDowload(blueprints)
					const nodes = this.getNodes2Render()
					console.log('-nodes->' + JSON.stringify(nodes))
				},
				error => {
					console.log('-[V2AvailableBlueprintsPanelComponent.accessProcessedBueprints.exception]> Error message: ' + JSON.stringify(error.error))
					if (environment.showexceptions) if (error instanceof HttpErrorResponse) this.isolationService.processException(error)
				},
			),
		)
	}
	private sortBlueprintByCostIndex(inputs: V2ProcessedBlueprint[]): V2ProcessedBlueprint[] {
		return inputs.sort((element1, element2) => 0 - (element2.index > element1.index ? -1 : 1))
	}
	public getPilotId(): number {
		return this.getCredential().getAccountId()
	}
	private getCredential(): NeoComCredential {
		const credentialJson = this.isolationService.getFromSession(STORAGE_LINKS.CREDENTIAL_KEY)
		console.log('Dashboard.getCredential>Credential at session: ' + credentialJson)
		if (null == credentialJson)
			throw new NeoComException()
				.withTitle('Rendering Dashboard Page. No Credential Found.')
				.withMessage('Unable to display Pilot data. There is no credential available to access data.')
				.withCause('Unexpected Exception. At this point then should exist a local session valid credential.')
		else {
			const credential = new NeoComCredential(JSON.parse(credentialJson))
			return credential
		}
	}
}
