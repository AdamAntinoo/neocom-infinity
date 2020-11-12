// - CORE
import { Component } from '@angular/core'
import { Input } from '@angular/core'
// - INNOVATIVE
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core'
import { IRefreshable } from '@bit/innovative.innovative.innovative-core'
// - COMPONENTS
import { HALResolver } from '@app/services/HALResolver.service'
import { FittingBuildConfigurationDao } from '@domain/industry/dao/FittingBuildConfigurationDao.dao'
import { FittingBuildContentDao } from '@domain/industry/dao/FittingBuildContentDao.dao'
import { FittingInfoDao } from '@domain/industry/dao/FittingInfoDao.dao'

@Component({
    selector: 'v1-fitting-configuration-panel',
    templateUrl: './v1-fitting-configuration-panel.component.html',
    styleUrls: ['./v1-fitting-configuration-panel.component.scss']
})
export class V1FittingConfigurationPanelComponent extends BackgroundEnabledComponent implements IRefreshable {
    @Input() title: string
    private link: string
    public fittingData: FittingBuildConfigurationDao

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
    public getFittingInfo () : FittingInfoDao{
        if (this.fittingData) return this.fittingData.getFittingInfo()
        else return undefined
    }
    public getFittingContents(): FittingBuildContentDao[] {
        return this.fittingData.getContents()
    }
    // - I R E F R E S H A B L E
    public clean(): void {
        this.fittingData = undefined
    }
    public refresh(): void {
        if (null != this.link)
            this.backendConnections.push( // Download the Fitting Build definition
                this.halResolver.resolve(this.link)
                    .subscribe((response: any) => {
                        this.fittingData = new FittingBuildConfigurationDao(response)
                    })
            )
    }
}
