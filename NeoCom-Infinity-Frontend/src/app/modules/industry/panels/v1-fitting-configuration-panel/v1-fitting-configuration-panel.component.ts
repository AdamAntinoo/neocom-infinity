// - CORE
import { Component } from '@angular/core'
import { Input } from '@angular/core'
// - INNOVATIVE
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
// - COMPONENTS
import { HALResolver } from '@app/services/HALResolver.service'
import { FittingBuildConfigurationDto } from '@domain/industry/dto/FittingBuildConfigurationDto.dto'
import { FittingBuildContentDto } from '@domain/industry/dto/FittingBuildContentDto.dto'
import { FittingInfoDto } from '@domain/industry/dto/FittingInfoDto.dto'

@Component({
    selector: 'v1-fitting-configuration-panel',
    templateUrl: './v1-fitting-configuration-panel.component.html',
    styleUrls: ['./v1-fitting-configuration-panel.component.scss']
})
export class V1FittingConfigurationPanelComponent extends BackgroundEnabledComponent implements IRefreshable {
    @Input() title: string
    private link: string
    public fittingData: FittingBuildConfigurationDto

    constructor(protected halResolver: HALResolver) {
        super()
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
    public getUniqueId(): string {
        if (this.fittingData)
            return this.fittingData.getFittingId()
        else return '-'
    }
    public getFittingInfo(): FittingInfoDto {
        if (this.fittingData) return this.fittingData.getFittingInfo()
        else return undefined
    }
    public getFittingContents(): FittingBuildContentDto[] {
        return this.fittingData.getContents()
    }
    // - I R E F R E S H A B L E
    public clean(): void {
        this.fittingData = undefined
    }
    public refresh(): void {
        // if (null != this.link)
        //     this.backendConnections.push( // Download the Fitting Build definition
        //         this.halResolver.resolve(this.link)
        //             .subscribe((response: any) => {
        //                 this.fittingData = new FittingBuildConfigurationDto(response)
        //                 // if (this.fittingData.isHalCompliant()) this.fittingData.injectResolver(this.halResolver)
        //             })
        //     )
    }
}
