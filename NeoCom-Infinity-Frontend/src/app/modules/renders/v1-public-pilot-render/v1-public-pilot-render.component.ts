// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - SERVICES
// - DOMAIN
import { RenderComponent } from '../../shared/renders/render/render.component';
import { Pilot } from '@app/domain/Pilot.domain';
import { PilotV2 } from '@domain/character/PilotV2.domain';
import { platformConstants } from '@env/platform-constants';
import { PublicPilotV1 } from '@domain/character/PublicPilotV1.domain';

@Component({
  selector: 'v1-public-pilot',
  templateUrl: './v1-public-pilot-render.component.html',
  styleUrls: ['./v1-public-pilot-render.component.scss']
})
export class V1PublicPilotRenderComponent {
    @Input() node: PublicPilotV1
    @Input() variant: string

    public getNode(): PublicPilotV1 {
        return this.node as PublicPilotV1
    }
    public getUniqueId(): string {
        if (this.node) return this.getNode().pilotId + ''
        else return '-'
    }
    public getPilotIcon(): string {
        if (this.node) return this.getNode().url4Icon
        else return platformConstants.DEFAULT_AVATAR_PLACEHOLDER
    }
    public getAncestryData(): string {
        if (this.node)
            return this.getNode().raceData.name + ' - ' +
                this.getNode().ancestryData.name + ' - ' +
                this.getNode().bloodlineData.name
        else return '-'
    }
}
