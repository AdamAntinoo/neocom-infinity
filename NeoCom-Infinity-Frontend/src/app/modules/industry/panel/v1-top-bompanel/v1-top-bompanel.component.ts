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
import { V1ManufactureResearchPageComponent } from '../../pages/v1-manufacture-research-page/v1-manufacture-research-page.component'
import { BOMResource, BOMResourceBuilder } from '../../domain/V1BOMResource.domain'
import { BuildActionDto } from '@domain/industry/dto/BuildActionDto.dto'

@Component({
    selector: 'v1-top-bompanel',
    templateUrl: './v1-top-bompanel.component.html',
    styleUrls: ['./v1-top-bompanel.component.scss']
})
export class V1TopBOMPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() container: V1ManufactureResearchPageComponent
    private bomGroups: BOMGroup[]

    public ngOnInit(): void {
        console.log(">[V1TopBOMPanelComponent.ngOnInit]");
        this.startDownloading();
        this.setVariant(NCVariant.MANUFACTURE_RESEARCH)
        this.refresh();
        console.log("<[V1TopBOMPanelComponent.ngOnInit]");
    }

    // - I V I E W E R
    public fireSelectionChanged(): void {
        this.container.activateTarget(this.target)
    }

    // - I R E F R E S H A B L E
    public clean(): void {
        this.bomGroups = []
    }
    /**
     * With the blueprint indetifier that is available as a parameter this method can access the backend and get the expanded Bill of Materials for the selected blueprint.
     * Apart from the list of materials it should get the current actions defined for current pilot manufacture oprtions. With this list we can detect items that should be BUY or MOVED or BUILT.
     * The BOM items are grouped on sets that should be rendered in order and that have specidic headers and constraints.
     */
    public refresh(): void {
        this.clean()
        const precursorsGroup: BOMGroup = new BOMGroupBuilder().withLabel('Precursors').build()
        precursorsGroup.addResource(new BOMResourceBuilder({
            typeId: 655,
            name: 'Epithal',
            quantity: 1,
            price: 1100000
        }).withBuildAction(new BuildActionDto({ actionType: 'BUILD' }))
            .build())
        const mineralsGroup: BOMGroup = new BOMGroupBuilder().withLabel('Minerals').build()
        mineralsGroup.addResource(new BOMResourceBuilder({
            typeId: 11399,
            name: 'Morphite',
            quantity: 104,
            price: 52910
        }).withBuildAction(new BuildActionDto({ actionType: 'BUY' }))
            .build())
        const planetaryGroup: BOMGroup = new BOMGroupBuilder().withLabel('Planetary Resources').build()
        planetaryGroup.addResource(new BOMResourceBuilder({
            typeId: 3828,
            name: 'Construction Blocks',
            quantity: 223,
            price: 6899
        }).withBuildAction(new BuildActionDto({ actionType: 'BUY' }))
            .build())
        const moonGroup: BOMGroup = new BOMGroupBuilder().withLabel('Advanced Moon Materials').build()
        moonGroup.addResource(new BOMResourceBuilder({
            typeId: 655,
            name: 'Ion Thruster',
            quantity: 75,
            price: 46290
        }).withBuildAction(new BuildActionDto({ actionType: 'BUY' }))
            .build())
        moonGroup.addResource(new BOMResourceBuilder({
            typeId: 11535,
            name: 'Magnetometric Sensor Cluster',
            quantity: 134,
            price: 38400
        }).withBuildAction(new BuildActionDto({ actionType: 'BUY' }))
            .build())

        this.bomGroups.push(precursorsGroup)
        this.bomGroups.push(planetaryGroup)
        this.bomGroups.push(mineralsGroup)
        this.bomGroups.push(moonGroup)
        this.completeDowload(this.bomGroups)
    }
}
