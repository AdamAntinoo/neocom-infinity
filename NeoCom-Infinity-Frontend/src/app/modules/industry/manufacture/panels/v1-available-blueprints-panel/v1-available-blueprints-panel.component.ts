// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { OnDestroy } from '@angular/core'
import { Input } from '@angular/core'
import { Subscription } from 'rxjs'
// - DOMAIN
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer'
import { NCVariant } from '@env/NeoComVariants'
import { IsolationService } from '@innovative/services/isolation.service'
import { IndustryService } from '@app/modules/industry/service/IndustryService.service'
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain'
import { HttpErrorResponse } from '@angular/common/http'
import { environment } from '@env/environment'
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto'
import { ESIUniverseDataService } from '@app/services/ESIUniverseData.service'
import { V1BlueprintListPageComponent } from '../../pages/v1-blueprint-list-page/v1-blueprint-list-page.component'

@Component({
    selector: 'v1-available-blueprints-panel',
    templateUrl: './v1-available-blueprints-panel.component.html',
    styleUrls: ['./v1-available-blueprints-panel.component.scss']
})
export class V1AvailableBlueprintsPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() container: V1BlueprintListPageComponent

    private processedBlueprintTransformer: ResponseTransformer
    private blueprintList: ProcessedBlueprint[] = []

    constructor(
        protected isolationService: IsolationService,
        protected industryService: IndustryService) {
        super()
        this.processedBlueprintTransformer = new ResponseTransformer()
            .setDescription('Do response transformation to "ProcessedBlueprint".')
            .setTransformation((entrydata: any): ProcessedBlueprint[] => {
                let results: ProcessedBlueprint[] = []
                if (entrydata instanceof Array) {
                    for (let key in entrydata)
                        results.push(new ProcessedBlueprintDto(entrydata[key]).transform(this.industryService))
                } else
                    results.push(new ProcessedBlueprintDto(entrydata).transform(this.industryService))
                return results
            })
    }
    public ngOnInit(): void {
        console.log(">[V1AvailableBlueprintsPanelComponent.ngOnInit]")
        this.setVariant(NCVariant.AVAILABLE_BLUEPRINTS)
        super.ngOnInit()
        console.log("<[V1AvailableBlueprintsPanelComponent.ngOnInit]")
    }

    // - I V I E W E R
    /**
     * When the user hovers over a blueprint item on the list there is an event and that blueprint node element is received on this method. On this moment we should open a second panel and disply the BOM for the target blueprint.
     * @param target the node hovered.
     */
    public enterSelected(target: any): void {
        console.log(">[enterSelected.enterSelected]")
        if (target instanceof ProcessedBlueprint) {
            if (this.container) this.container.signalSelection(target)
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
                if (target instanceof ProcessedBlueprint)
                    this.container.signalSelection(target)
            } else this.container.signalSelection(undefined)
        }
    }

    // - R E F R E S H A B L E
    public clean(): void {
    }
    public refresh(): void {
        console.log(">[V1AvailableBlueprintsPanelComponent.refresh]")
        this.clean()
        this.accessBlueprintList()
        console.log("<[V1AvailableBlueprintsPanelComponent.refresh]")
    }
    /**
     * Download the list of blueprints, converting them into a list of <code>ProcessedBlueprint</code> that can be rendered to show the manufacture costs and its comparison to the manufactured item cost.
     * During blueprint processing there are pending events that complete when the blueprint item is downloaded. This should fire some sort of refresh event that should redraw the page.
     */
    private accessBlueprintList(): void {
        console.log(">[V1AvailableBlueprintsPanelComponent.accessBlueprintList]")
        this.backendConnections.push(
            this.industryService.apiv1_GetProcessedBlueprints(this.processedBlueprintTransformer)
                .subscribe((response: ProcessedBlueprint[]) => {
                    this.blueprintList = this.sortBlueprintByName(response)
                    console.log('-[V1AvailableBlueprintsPanelComponent.accessBlueprintList]> Nodes downloaded: ' +
                        this.blueprintList.length)
                    this.completeDowload(this.blueprintList) // Notify the completion of the download.
                }, (error) => {
                    console.log('-[V1KnownSystemsPanelComponent.accessBlueprintList.exception]> Error message: ' +
                        JSON.stringify(error.error))
                    if (environment.showexceptions)
                        if (error instanceof HttpErrorResponse)
                            this.isolationService.processException(error)
                })
        )
        console.log("<[V1AvailableBlueprintsPanelComponent.accessBlueprintList]")
    }
    private sortBlueprintByName(inputs: ProcessedBlueprint[]): ProcessedBlueprint[] {
        return inputs.sort((element1, element2) =>
            0 - (element2.getName() > element1.getName() ? -1 : 1)
        )
    }
}
