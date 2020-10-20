// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - SERVICES
import { IsolationService } from '@app/platform/isolation.service';
// - DOMAIN
import { RenderComponent } from '../../shared/renders/render/render.component';
import { Pilot } from '@app/domain/Pilot.domain';
import { PilotV2 } from '@domain/PilotV2.domain';

@Component({
    selector: 'v2-pilot',
    templateUrl: './v2-pilot-render.component.html',
    styleUrls: ['./v2-pilot-render.component.scss']
})
export class V2PilotRenderComponent implements OnInit {
    @Input() node: PilotV2
    @Input() variant: string

    constructor() { }

    ngOnInit(): void {
    }
    public getAncestryData(): string {
        return this.node.raceData[name] + ' - ' + this.node.ancestryData[name] + ' - ' + this.node.bloodlineData[name]
    }
    public getLastLocation():string{
        return this.node.getLastKnownLocation()
    }
    // public getPilotIcon () : string{
    //     return this.node.
    // }
}
