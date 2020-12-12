// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { Input } from '@angular/core';
import { V1KnownSystemsPanelComponent } from '@app/modules/planetary/panel/v1-known-systems-panel/v1-known-systems-panel.component';
import { SystemDto } from '@domain/planetary/dto/System.dto';
import { KnownSystem } from '@domain/planetary/KnownSystem.domain';
import { IViewer } from '@innovative/domain/interfaces/IViewer.interface';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';

@Component({
    selector: 'v1-planetary-system',
    templateUrl: './v1-planetary-system-render.component.html',
    styleUrls: ['./v1-planetary-system-render.component.scss']
})
export class V1PlanetarySystemRenderComponent extends NodeContainerRenderComponent {
    // - I N T E R A C T I O N S
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
    public getSecurity(): string {
        if (this.node) return this.getNode().securityClass + ' - ' + this.getNode().securityLevel
        else return '-'
    }
    public getPlanetCount(): string {
        if (this.node) return this.getNode().planetCount + ''
        else return '-'
    }
    public planetInfoCount () : number{
        return 1
    }

    public selectSystem(): void {
        console.log('>[V1PlanetarySystemRenderComponent.selectSystem]> Name: ' + this.getNode().getName())
        const container = this.container as V1KnownSystemsPanelComponent
        container.setSelectedSystem(this.getNode())
        console.log('<[V1PlanetarySystemRenderComponent.selectSystem]')
    }
}
