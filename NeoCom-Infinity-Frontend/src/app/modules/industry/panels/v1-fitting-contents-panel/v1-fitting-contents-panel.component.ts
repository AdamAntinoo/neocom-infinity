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
import { FittingBuildConfigurationDao } from '@domain/industry/dao/FittingBuildConfigurationDao.dao'
import { NCVariant } from '@env/NeoComVariants'
import { FittingBuildContentDao } from '@domain/industry/dao/FittingBuildContentDao.dao'

@Component({
    selector: 'v1-fitting-contents-panel',
    templateUrl: './v1-fitting-contents-panel.component.html',
    styleUrls: ['./v1-fitting-contents-panel.component.scss']
})
export class V1FittingContentsPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() contents: FittingBuildContentDao[] = []
    private slotGroups: Map<string, FittingBuildContentDao[]> = new Map<string, FittingBuildContentDao[]>()
    constructor() {
        super()
    }

    public ngOnInit(): void {
        this.setVariant(NCVariant.FITTING_CONFIGURATION)
        this.refresh()
    }
    // - I R E F R E S H A B L E
    public clean(): void {
        this.slotGroups = new Map<string, FittingBuildContentDao[]>()
        this.dataModelRoot = []
    }
    /**
     * Generates the list of collaborated nodes from the list of contents. The list of contents is a list of all the items and actions for a Fitting. Items should be classified depending on the slot group or cargo container where they are located. Then that containers will also collaborate to the list of nodes to render but doing it on an pre ordered way.
     */
    public refresh(): void {
        for (const fittingContent of this.contents) {
            this.addToSlotGroup(fittingContent.getLocationGroup(), fittingContent)
        }
        for (const group of this.slotGroups.values())
            for (const item of group)
                this.dataModelRoot.push(item)
                this.completeDowload()
    }
    private addToSlotGroup(slotGroup: string, item: FittingBuildContentDao): void {
        let hit = this.slotGroups.get(slotGroup)
        if (null == hit) hit = []
        hit.push(item)
        this.slotGroups.set(slotGroup, hit)
    }
}
