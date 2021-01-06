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
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
// - COMPONENTS
import { BackendService } from '@app/services/backend.service'
import { HALResolver } from '@app/services/HALResolver.service'
import { FittingBuildConfigurationDto } from '@domain/industry/dto/FittingBuildConfigurationDto.dto'
import { NCVariant } from '@env/NeoComVariants'
import { FittingBuildContentDto } from '@domain/industry/dto/FittingBuildContentDto.dto'
import { FittingGroup } from '@domain/industry/FittingGroup.domain'
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface'
import { BOMGroup, BOMGroupBuilder } from '../../domain/V1BOMGroup.domain'
import { IndustryResource } from '../../domain/V1IndustryResource.domain'
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component'
import { BOMResource } from '../../domain/V1BOMResource.domain'
import { ESIUniverseDataService } from '@app/services/ESIUniverseData.service'

@Component({
    selector: 'v1-target-additional-data-panel',
    templateUrl: './v1-target-additional-data-panel.component.html',
    styleUrls: ['./v1-target-additional-data-panel.component.scss']
})
export class V1TargetAdditionalDataPanelComponent implements IRefreshable {
    @Input() target: BOMResource

    constructor(protected esiDataService: ESIUniverseDataService) { }

    public clean(): void {
        throw new Error('Method not implemented.')
    }
    public refresh(): void {
        this.activate()
    }

    public activate(): boolean {
        if (this.target) {
            if (this.target instanceof BOMResource) return true
        } else return false
    }
    public getActionType(): string {
        if (this.target) {
            console.log('>type: ' + this.target.jsonClass)
            console.log('>Action Type: ' + this.target.getAction().getActionTypeName())
            return this.target.getAction().getActionTypeName()
        } else return '-'
    }
    public getMarketPrice(): number {
        if (this.activate()) {
            this.esiDataService.apiMarketSearchRegionOrders(10000043, this.target.getTypeId())
                .subscribe(marketData => {
                    console.log('>Market data count:' + marketData.length)
                    for (let index = 0; index < marketData.length; index++) {
                        const element = marketData[index];
                        if (element["location_id"] == 60008494) {
                            console.log('> market entry located: ' + element['price'])
                        }
                    }
                    // 60008494
                })
            return 100
        } return 0
    }
}
