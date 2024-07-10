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

@Component({
    selector: 'v1-region',
    templateUrl: './v1-region-render.component.html',
    styleUrls: ['./v1-region-render.component.scss']
})
export class V1RegionRenderComponent extends V2NodeContainerRenderComponent {
    public getNode(): LookupRegion {
        return this.node as LookupRegion
    }
    public getUniqueId(): string{
        return 0+''
    }
    public getName(): string {
        if (this.node) return this.getNode().name
        else return '-'
    }
    public getSystemsCount():number{
        if(this.node)return this.getNode().getSystemsCount()
        else return 0
    }
}
