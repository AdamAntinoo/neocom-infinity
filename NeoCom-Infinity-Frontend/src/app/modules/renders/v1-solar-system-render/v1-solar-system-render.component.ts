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
import { LookupSolarSystem } from '@app/modules/planetary/domain/LookupSolarSystem.domain';

@Component({
  selector: 'v1-solar-system',
  templateUrl: './v1-solar-system-render.component.html',
  styleUrls: ['./v1-solar-system-render.component.scss']
})
export class V1SolarSystemRenderComponent extends V2NodeContainerRenderComponent {
    public getNode(): LookupSolarSystem {
        return this.node as LookupSolarSystem
    }
    public getUniqueId(): number{
        return 0
    }
    public getName(): string {
        if (this.node) return this.getNode().getName()
        else return '-'
    }
}
