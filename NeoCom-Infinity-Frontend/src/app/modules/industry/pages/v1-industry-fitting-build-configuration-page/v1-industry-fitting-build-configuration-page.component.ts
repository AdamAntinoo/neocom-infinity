// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { Input } from '@angular/core'
import { ViewChild } from '@angular/core'
import { environment } from '@env/environment'
import { HttpErrorResponse } from '@angular/common/http'
// - ROUTER
import { ActivatedRoute } from '@angular/router'
// - INNOVATIVE
import { IsolationService } from '@innovative/services/isolation.service'
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component'
import { IRefreshable } from '@innovative/domain/interfaces/core/IRefreshable.interface'
// - SERVICES
import { BackendService } from '@app/services/backend.service'
import { ResponseTransformer } from '@app/services/support/ResponseTransformer'
import { V1FittingConfigurationPanelComponent } from '../../panels/v1-fitting-configuration-panel/v1-fitting-configuration-panel.component'

@Component({
    selector: 'v1-industry-fitting-build-configuration-page',
    templateUrl: './v1-industry-fitting-build-configuration-page.component.html',
    styleUrls: ['./v1-industry-fitting-build-configuration-page.component.scss']
})
export class V1IndustryFittingBuildConfigurationPageComponent extends BackgroundEnabledComponent implements OnInit, IRefreshable {
    @ViewChild(V1FittingConfigurationPanelComponent) savedConfiguration : V1FittingConfigurationPanelComponent
    @ViewChild(V1FittingConfigurationPanelComponent) targetConfiguration : V1FittingConfigurationPanelComponent
    private identity: number

    constructor(
        protected route: ActivatedRoute,
        protected isolationService: IsolationService,
        protected backendService: BackendService
    ) { super() }

    // - L I F E C Y C L E
    public ngOnInit(): void {
        // Process route parameters
        this.backendConnections.push(
            this.route.params.subscribe(params => {
                this.identity = +params['fittingId']
            })
        )
        this.refresh()
    }
    // - I N T E R A C T I O N
    public getIdentityParameter(): number {
        return this.identity
    }
    // - I R E F R E S H A B L E
    public clean(): void {
        throw new Error('Method not implemented.')
    }
    public refresh(): void {
        this.backendConnections.push( // Download the Fitting Build definition
            this.backendService.apiIndustryGetFittingDefinition_v1(this.identity, new ResponseTransformer()
                .setDescription('Does nothing since the response expected is a list of HAL links.')
                .setTransformation((entrydata: any): any => {
                    return entrydata
                }))
                .subscribe((response: any) => {
                    this.savedConfiguration.sendConfigurationReference( response.savedBuildData.href);
                    this.targetConfiguration.sendConfigurationReference( response.savedBuildData.href);
                    // const coilList = this.sortCoildByMaterialColor(response.getCoils())
                    // console.log('-[V1CoilsPanelComponent.downloadCoils]> Nodes downloaded: ' + coilList.length)
                    // this.completeDowload(coilList) // Notify the completion of the download.
                }, (error) => {
                    console.log('-[V1CoilsPanelComponent.downloadCoils.exception]> Error message: ' + JSON.stringify(error.error))
                    if (environment.showexceptions)
                        if (error instanceof HttpErrorResponse)
                            this.isolationService.processException(error)
                })
        )
    }
}
