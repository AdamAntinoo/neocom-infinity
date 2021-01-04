// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { OnDestroy } from '@angular/core'
import { Input } from '@angular/core'
import { Subscription } from 'rxjs'
// - DOMAIN
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component'
import { IRefreshable } from '@innovative/domain/interfaces/core/IRefreshable.interface'
import { platformConstants } from '@env/platform-constants'
import { ValidateAuthorizationTokenResponse } from '@app/domain/dto/ValidateAuthorizationTokenResponse'
import { Pilot } from '@app/domain/Pilot.domain'
import { Corporation } from '@app/domain/Corporation.domain'
import { ServerStatus } from '@app/domain/ServerStatus.domain'
import { Fitting } from '@app/domain/Fitting.domain'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer'
import { NCVariant } from '@env/NeoComVariants'
import { IsolationService } from '@innovative/services/isolation.service'
import { IndustryService } from '@app/modules/industry/service/IndustryService.service'
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain'
import { HttpErrorResponse } from '@angular/common/http'
import { environment } from '@env/environment'
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto'
import { ESIUniverseDataService } from '@app/services/ESIUniverseData.service'

@Component({
    selector: 'v1-available-blueprints-panel',
    templateUrl: './v1-available-blueprints-panel.component.html',
    styleUrls: ['./v1-available-blueprints-panel.component.scss']
})
export class V1AvailableBlueprintsPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    private processedBlueprintTransformer: ResponseTransformer

    constructor(
        protected isolationService: IsolationService,
        protected universeService: ESIUniverseDataService,
        protected industryService: IndustryService) {
        super()
        this.processedBlueprintTransformer = new ResponseTransformer()
            .setDescription('Do response transformation to "ProcessedBlueprint".')
            .setTransformation((entrydata: any): ProcessedBlueprint[] => {
                let results: ProcessedBlueprint[] = []
                if (entrydata instanceof Array) {
                    for (let key in entrydata)
                        results.push(new ProcessedBlueprintDto(entrydata[key]).transform(this.universeService))
                } else
                    results.push(new ProcessedBlueprintDto(entrydata[key]).transform(this.universeService))
                return results
            })
    }
    public ngOnInit(): void {
        console.log(">[V1AvailableBlueprintsPanelComponent.ngOnInit]")
        this.setVariant(NCVariant.AVAILABLE_BLUEPRINTS)
        super.ngOnInit()
        console.log("<[V1AvailableBlueprintsPanelComponent.ngOnInit]")
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
    private accessBlueprintList(): void {
        console.log(">[V1AvailableBlueprintsPanelComponent.accessKnownSystems]")
        this.backendConnections.push(
            this.industryService.apiv1_GetProcessedBlueprints(this.processedBlueprintTransformer)
                .subscribe((response: ProcessedBlueprint[]) => {
                    const blueprintList = this.sortBlueprintByName(response)
                    console.log('-[V1AvailableBlueprintsPanelComponent.accessBlueprintList]> Nodes downloaded: ' +
                        blueprintList.length)
                    this.completeDowload(blueprintList) // Notify the completion of the download.
                }, (error) => {
                    console.log('-[V1KnownSystemsPanelComponent.accessKnownSystems.exception]> Error message: ' +
                        JSON.stringify(error.error))
                    if (environment.showexceptions)
                        if (error instanceof HttpErrorResponse)
                            this.isolationService.processException(error)
                })
        )
        console.log("<[V1AvailableBlueprintsPanelComponent.accessKnownSystems]")
    }
    private sortBlueprintByName(inputs: ProcessedBlueprint[]): ProcessedBlueprint[] {
        return inputs.sort((element1, element2) =>
            0 - (element2.getName() > element1.getName() ? -1 : 1)
        )
    }
}
