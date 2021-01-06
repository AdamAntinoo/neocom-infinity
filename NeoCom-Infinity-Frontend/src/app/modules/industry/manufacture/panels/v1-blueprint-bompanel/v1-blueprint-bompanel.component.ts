// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { OnDestroy } from '@angular/core'
import { Input } from '@angular/core'
import { Subscription } from 'rxjs'
// - DOMAIN
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component';
import { V1BlueprintListPageComponent } from '../../pages/v1-blueprint-list-page/v1-blueprint-list-page.component'
import { BOMGroup } from '@app/modules/industry/domain/V1BOMGroup.domain'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { NCVariant } from '@env/NeoComVariants'

@Component({
    selector: 'v1-blueprint-bompanel',
    templateUrl: './v1-blueprint-bompanel.component.html',
    styleUrls: ['./v1-blueprint-bompanel.component.scss']
})
export class V1BlueprintBOMPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() container: V1BlueprintListPageComponent
    private bomGroups: BOMGroup[]

    public ngOnInit(): void {
        console.log(">[V1TopBOMPanelComponent.ngOnInit]");
        this.startDownloading();
        this.setVariant(NCVariant.BLUEPRINT_BOM)
        this.refresh();
        console.log("<[V1TopBOMPanelComponent.ngOnInit]");
    }
    // - I R E F R E S H A B L E
    public clean(): void {
        this.bomGroups = []
    }
    public refresh(): void {
        this.clean()
    }
}
