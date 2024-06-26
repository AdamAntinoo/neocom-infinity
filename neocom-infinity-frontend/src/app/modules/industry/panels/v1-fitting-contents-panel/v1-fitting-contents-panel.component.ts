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
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component'
import { BackendService } from '@app/services/backend.service'
import { HALResolver } from '@app/services/HALResolver.service'
import { FittingBuildConfigurationDto } from '@domain/industry/dto/FittingBuildConfigurationDto.dto'
import { NCVariant } from '@env/NeoComVariants'
import { FittingBuildContentDto } from '@domain/industry/dto/FittingBuildContentDto.dto'
import { FittingGroup } from '@domain/industry/FittingGroup.domain'
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface'

@Component({
    selector: 'v1-fitting-contents-panel',
    templateUrl: './v1-fitting-contents-panel.component.html',
    styleUrls: ['./v1-fitting-contents-panel.component.scss']
})
export class V1FittingContentsPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() contents: FittingBuildContentDto[] = []
    private slotGroups: FittingGroup[] = []
    private fittinContents : ICollaboration[]=[]
    constructor() {
        super()
        // Initialize the list of fitting groups.
        this.slotGroups=[]
        this.slotGroups.push(new FittingGroup().setId('HIGH-SLOTS').setName('High Slots').setWeight(100))
        this.slotGroups.push(new FittingGroup().setId('MED-SLOTS').setName('Med Slots').setWeight(200))
        this.slotGroups.push(new FittingGroup().setId('LOW-SLOTS').setName('Low Slots').setWeight(300))
        this.slotGroups.push(new FittingGroup().setId('RIG-SLOTS').setName('Rigs').setWeight(400))
        this.slotGroups.push(new FittingGroup().setId('CARGO-BAY').setName('Cargo').setWeight(500))
    }

    public ngOnInit(): void {
        this.setVariant(NCVariant.FITTING_CONFIGURATION)
        this.refresh()
    }
    // - I R E F R E S H A B L E
    public clean(): void {
        this.slotGroups = []
        this.dataModelRoot = []
    }
    /**
     * Generates the list of collaborated nodes from the list of contents. The list of contents is a list of all the items and actions for a Fitting. Items should be classified depending on the slot group or cargo container where they are located. Then that containers will also collaborate to the list of nodes to render but doing it on an pre ordered way.
     */
    public refresh(): void {
        for (const fittingContent of this.contents) {
            this.addToSlotGroup(fittingContent.getLocationGroup(), fittingContent)
        }
        for (const group of this.sortByWeight(this.slotGroups))
            this.fittinContents.push(group)
        this.completeDowload(this.fittinContents)
    }
    private addToSlotGroup(slotGroup: string, item: FittingBuildContentDto): void {
        let hit = undefined
        for (const group of this.slotGroups) {
            if (group.getId() === slotGroup) hit = group
        }
        if (undefined == hit) {
            hit = new FittingGroup().setId(slotGroup).setName(slotGroup).setWeight(900)
            this.slotGroups.push(hit)
        }
        hit.addContent(item)
    }
    private sortByWeight(input: FittingGroup[]): FittingGroup[] {
        return input.sort((element1, element2) =>
            0 - (element2.weight > element1.weight ? 1 : -1)
        )
    }
}
