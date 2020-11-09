// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { BuildActionDao } from '@domain/industry/BuildActionDao.dao';
import { FittingBuildConfiguration } from '@domain/industry/FittingBuildConfiguration.domain';

@Component({
    selector: 'v1-fitting-item',
    templateUrl: './v1-fitting-item-render.component.html',
    styleUrls: ['./v1-fitting-item-render.component.scss']
})
export class V1FittingItemRenderComponent {
    @Input() node: BuildActionDao
    
    public getNode(): BuildActionDao {
        return this.node
    }
    public getName(): string {
        return this.getNode().getModuleName()
    }
    public getTech(): string {
        return this.getNode().getTech()
    }
    public getURLIcon(): string {
        return this.getNode().getURLIcon()
    }
    public getStationName(): string {
        return this.getNode().getStationName()
    }
    public getPrice(): number {
        return this.getNode().getPrice()
    }
    public getDistanceHops() : number{
        return this.getNode().getHops()
    }
}
