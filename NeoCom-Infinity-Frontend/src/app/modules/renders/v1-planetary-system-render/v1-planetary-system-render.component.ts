// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { Input } from '@angular/core';
import { SystemDto } from '@domain/planetary/dto/System.dto';
import { KnownSystem } from '@domain/planetary/KnownSystem.domain';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';

@Component({
    selector: 'v1-planetary-system',
    templateUrl: './v1-planetary-system-render.component.html',
    styleUrls: ['./v1-planetary-system-render.component.scss']
})
export class V1PlanetarySystemRenderComponent extends NodeContainerRenderComponent {
    public getNode(): KnownSystem {
        return this.node as KnownSystem
    }
    public getUniqueId(): number {
        return this.getNode().getUniqueId()
    }
    public getSystemName(): string {
        if (this.node) return this.getNode().getName()
        else return '-'
    }
    public getLocation(): string {
        if (this.node) return this.getNode().regionName + ' > ' + this.getNode().constellationName
        else return '-'
    }
    public getSecurity () : string {
        if (this.node) return this.getNode().securityClass + ' - ' + this.getNode().securityLevel
        else return '-'
   }
}
