// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - SERVICES
// - DOMAIN
import { RenderComponent } from '../../shared/renders/render/render.component';
import { Pilot } from '@app/domain/Pilot.domain';
import { PilotV2 } from '@domain/character/PilotV2.domain';
import { PlatformConstants } from '@env/PlatformConstants';
import { PublicPilotV1 } from '@domain/character/PublicPilotV1.domain';
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';
import { LookupRegion } from '@app/modules/planetary/domain/LookupRegion.domain';
import { LoyaltyCorporationV1 } from '@app/modules/loyalty/domain/LoyaltyCorporationV1.domain';
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';

@Component({
    selector: 'v1-loyalty-corporation',
    templateUrl: './v1-loyalty-corporation-render.component.html',
    styleUrls: ['./v1-loyalty-corporation-render.component.scss']
})
export class V1LoyaltyCorporationRenderComponent extends V2NodeContainerRenderComponent {
    public getNode(): LoyaltyCorporationV1 {
        return this.node as LoyaltyCorporationV1;
    }
    public getUniqueId(): string {
        if (this.node) return this.getNode().id + ''
        else return '-'
    }
    public getCorporationIcon(): string {
        if (this.node) return this.getNode().getCorporationIconUrl()
        else return NeoComConstants.DEFAULT_ICON_PLACEHOLDER
    }
    public isSelected(): boolean {
        if (this.node) return this.getNode().isSelected()
        else return false
    }
}
