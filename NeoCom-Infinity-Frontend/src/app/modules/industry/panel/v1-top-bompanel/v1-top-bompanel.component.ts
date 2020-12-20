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
import { AppPanelComponent } from '@shared/core/app-panel/app-panel.component'
import { BackendService } from '@app/services/backend.service'
import { HALResolver } from '@app/services/HALResolver.service'
import { FittingBuildConfigurationDto } from '@domain/industry/dto/FittingBuildConfigurationDto.dto'
import { NCVariant } from '@env/NeoComVariants'
import { FittingBuildContentDto } from '@domain/industry/dto/FittingBuildContentDto.dto'
import { FittingGroup } from '@domain/industry/FittingGroup.domain'
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface'

@Component({
    selector: 'v1-top-bompanel',
    templateUrl: './v1-top-bompanel.component.html',
    styleUrls: ['./v1-top-bompanel.component.scss']
})
export class V1TopBOMPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    private bomGroups : any[]
    public ngOnInit(): void {
        this.setVariant(NCVariant.FITTING_CONFIGURATION)
        this.refresh()
    }
    // - I R E F R E S H A B L E
    public clean(): void {
        this.bomGroups=[]
    }
    /**
     * With the blueprint indetifier that is available as a parameter this method can access the backend and get the expanded Bill of Materials for the selected blueprint.
     * Apart from the list of materials it should get the current actions defined for current pilot manufacture oprtions. With this list we can detect items that should be BUY or MOVED or BUILT.
     * The BOM items are grouped on sets that should be rendered in order and that have specidic headers and constraints.
     */
    public refresh(): void {
        this.clean()
        const precursorsGroup = {label: 'Precursors', contents : []}
this.bomGroups.push(precursorsGroup)
    }
}
