import { Component, OnInit } from '@angular/core';
import { PlanetaryResource } from '@domain/planetary/planetary-resource';
import { PlatformConstants } from '@env/PlatformConstants';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';

@Component({
    selector: 'v1-planetary-resource',
    templateUrl: './v1-planetary-resource-render.component.html',
    styleUrls: ['./v1-planetary-resource-render.component.scss']
})
export class V1PlanetaryResourceRenderComponent extends NodeContainerRenderComponent {
    public getNode(): PlanetaryResource {
        return this.node as PlanetaryResource
    }
    public getUniqueId(): number {
        if (this.node) return this.getNode().getTypeId()
        else return 0
    }
    public getName(): string {
        if (this.node) return this.getNode().getName()
        else return '-'
    }
    public getLevel(): string {
        if (this.node) return this.getNode().getLevel() + ''
        else return '[-]'
    }
    public getLevelValue(): number {
        if (this.node) return this.getNode().getLevel()
        else return 0
    }
    public getResourceIcon(): string {
        if (this.node) return this.getNode().getURLIcon()
        else return PlatformConstants.DEFAULT_ICON_PLACEHOLDER
    }
}
