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
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core'
import { IRefreshable } from '@bit/innovative.innovative.innovative-core'
// - COMPONENTS
import { AppPanelComponent } from '@shared/panels/app-panel/app-panel.component'
import { BackendService } from '@app/services/backend.service'
import { HALResolver } from '@app/services/HALResolver.service'
import { FittingBuildConfiguration } from '@domain/industry/FittingBuildConfiguration.domain'
import { NCVariant } from '@env/NeoComVariants'

@Component({
    selector: 'v1-fitting-configuration-panel',
    templateUrl: './v1-fitting-configuration-panel.component.html',
    styleUrls: ['./v1-fitting-configuration-panel.component.scss']
})
export class V1FittingConfigurationPanelComponent extends AppPanelComponent implements IRefreshable {
    @Input() title: string
    private link: string
    public fittingData: FittingBuildConfiguration
    
    constructor(protected halResolver: HALResolver) {
        super()
        this.setVariant(NCVariant.FITTING_CONFIGURATION.toString())
    }

    // - I N T E R A C T I O N S
    public getTitle(): string {
        return this.title.toUpperCase()
    }
    public sendConfigurationReference(link: string): void {
        if (null != link) {
            this.link = link
            this.refresh()
        }
    }
    // - I R E F R E S H A B L E
    public clean(): void {
        throw new Error('Method not implemented.')
    }
    public refresh(): void {
        if (null != this.link)
            this.backendConnections.push( // Download the Fitting Build definition
                this.halResolver.resolve(this.link)
                    .subscribe((response: any) => {
                        this.fittingData = new FittingBuildConfiguration(response)
                        this.dataModelRoot.push(this.fittingData)
                        this.completeDowload()
                    })
            )
    }
}
