import { Component, OnInit } from '@angular/core';
import { PlanetaryDataService } from '@app/modules/planetary/service/PlanetaryData.service';
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';
import { GeneratedResource } from '@domain/planetary/generated-resource';
import { PlanetaryResource } from '@domain/planetary/planetary-resource';
import { PlatformConstants } from '@env/PlatformConstants';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';

@Component({
    selector: 'v1-output-resource',
    templateUrl: './v1-output-resource-render.component.html',
    styleUrls: ['./v1-output-resource-render.component.scss']
})
export class V1OutputResourceRenderComponent extends NodeContainerRenderComponent {
    constructor(protected planetService: PlanetaryDataService) {
        super();
    }

    public getNode(): GeneratedResource {
        return this.node as GeneratedResource
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
        else return NeoComConstants.DEFAULT_ICON_PLACEHOLDER
    }
    public isSelected(): boolean {
        return this.getNode().isSelected()
    }
    public getPlanetIcon(): string {
        return '/assets/res-planetary/' + this.planetService.getPlanetIconByType(this.getNode().getPlanetClass().toLowerCase())
    }
    public getPlanetName(): string {
        if (this.node) return this.getNode().getPlanetName()
        else return '-'
    }
    public getPlanetClass(): string {
        if (this.node) return this.getNode().getPlanetClass().toUpperCase()
        else return '-'
    }
}
