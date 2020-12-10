import { Component, OnInit } from '@angular/core';
import { PlanetaryDataService } from '@app/modules/planetary/service/PlanetaryData.service';
import { PlanetaryResource } from '@domain/planetary/planetary-resource';
import { PlanetaryData } from '@domain/planetary/PlanetaryData.domain';
import { platformConstants } from '@env/platform-constants';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';

@Component({
    selector: 'v1-planet-data',
    templateUrl: './v1-planet-data-render.component.html',
    styleUrls: ['./v1-planet-data-render.component.scss']
})
export class V1PlanetDataRenderComponent extends NodeContainerRenderComponent {
    constructor(protected planetService: PlanetaryDataService) {
        super();
    }

    public getNode(): PlanetaryData {
        return this.node as PlanetaryData
    }
    public getUniqueId(): number {
        if (this.node) return this.getNode().getPlanetId()
        else return 0
    }
    public getPlanetIcon(): string {
        return '/assets/res-planetary/' + this.planetService.getPlanetIconByType(this.getPlanetType().toLowerCase())
    }
    public getPlanetName(): string {
        if (this.node)
            return this.getNode().getPlanetName() + ' ' + this.getNode().getPlanetSuffix()
        else return '-'
    }
    public getPlanetClass(): string {
        if (this.node)
            return this.getNode().getPlanetType().toUpperCase()
        else return '-'
    }
    public getPlanetResources(): PlanetaryResource[] {
        if (this.node) return this.getNode().getPlanetResources()
        else return []
    }
    protected getPlanetType(): string {
        if (this.node) return this.getNode().getPlanetType()
        else return 'not-found'
    }
}
