// - CORE
import { Component } from '@angular/core';
import { ViewChild } from '@angular/core';
import { KnownSystem } from '@domain/planetary/KnownSystem.domain';
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface';
// import { V1TargetAdditionalDataPanelComponent } from '../../panel/v1-target-additional-data-panel/v1-target-additional-data-panel.component';

@Component({
    selector: 'v1-manufacture-research-page',
    templateUrl: './v1-manufacture-research-page.component.html',
    styleUrls: ['./v1-manufacture-research-page.component.scss']
})
export class V1ManufactureResearchPageComponent {
    // @ViewChild(V1TargetAdditionalDataPanelComponent) additionalData: V1TargetAdditionalDataPanelComponent
    public self: V1ManufactureResearchPageComponent
    public hoveringTarget: ICollaboration

    constructor() {
        this.self = this
    }
    // - A P I
    public activateTarget(target: ICollaboration): void {
        console.log('>Canging target')
        if (target) {
            this.hoveringTarget = target
            // this.additionalData.refresh()
        }
    }
}
