// - CORE
import { Component } from '@angular/core';
import { ViewChild } from '@angular/core';
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain';
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto';
import { V1AvailableBlueprintsPanelComponent } from '../../panels/v1-available-blueprints-panel/v1-available-blueprints-panel.component';

@Component({
    selector: 'v1-blueprint-list-page',
    templateUrl: './v1-blueprint-list-page.component.html',
    styleUrls: ['./v1-blueprint-list-page.component.scss']
})
export class V1BlueprintListPageComponent {
    @ViewChild(V1AvailableBlueprintsPanelComponent) availableBlueprints: V1AvailableBlueprintsPanelComponent
    public self: V1BlueprintListPageComponent
    public selectedBlueprint: ProcessedBlueprint

    constructor() {
        this.self = this
    }

     // - I N T E R A C T I O N S
     public getPilotId() : number {
        return 100
    }
   public signalSelection(target: ProcessedBlueprint): void {
        if (target) {
            console.log('-[V1BlueprintListPageComponent.signalSelection]> Select')
            this.selectedBlueprint = target
        } else {
            console.log('-[V1BlueprintListPageComponent.signalSelection]> UnSelect')
            this.selectedBlueprint = undefined
        }
    }
}
