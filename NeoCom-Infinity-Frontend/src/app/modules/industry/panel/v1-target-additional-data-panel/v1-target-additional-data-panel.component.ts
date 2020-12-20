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
import { IRefreshable } from '@innovative/domain/interfaces/core/IRefreshable.interface'
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

@Component({
    selector: 'v1-target-additional-data-panel',
    templateUrl: './v1-target-additional-data-panel.component.html',
    styleUrls: ['./v1-target-additional-data-panel.component.scss']
})
export class V1TargetAdditionalDataPanelComponent implements IRefreshable {
    @Input() target: IndustryResource
    public clean(): void {
        throw new Error('Method not implemented.')
    }
    public refresh(): void {
        this.activate()
    }

    public activate(): boolean {
        if (this.target) return true
        else return false
    }
}
