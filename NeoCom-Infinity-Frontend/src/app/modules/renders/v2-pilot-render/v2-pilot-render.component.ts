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
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';

@Component({
    selector: 'v2-pilot',
    templateUrl: './v2-pilot-render.component.html',
    styleUrls: ['./v2-pilot-render.component.scss']
})
export class V2PilotRenderComponent {
    @Input() node: PilotV2
    @Input() variant: string

    public getNode(): PilotV2 {
        return this.node as PilotV2
    }
    public getUniqueId(): string {
        if (this.node) return this.getNode().pilotId + ''
        else return '-'
    }
    public getPilotIcon(): string {
        if (this.node) return this.getNode().url4Icon
        else return NeoComConstants.DEFAULT_AVATAR_PLACEHOLDER
    }
    public getAncestryData(): string {
        if (this.node)
            return this.getNode().raceData.name + ' - ' +
                this.getNode().ancestryData.name + ' - ' +
                this.getNode().bloodlineData.name
        else return '-'
    }
    public getLastLocation(): string {
        return this.node.getLastKnownLocation()
    }
    // public getPilotIcon () : string{
    //     return this.node.
    // }
}
