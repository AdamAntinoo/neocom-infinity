// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { BuildActionDao } from '@domain/industry/dao/BuildActionDao.dao';
import { FittingBuildConfigurationDao } from '@domain/industry/dao/FittingBuildConfigurationDao.dao';

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
        return 'NAME'
    }
    public getTech(): string {
        return 'Tech I'
    }
    public getURLIcon(): string {
        return 'default'
    }
    public getStationName(): string {
        return this.getNode().getStationName()
    }
    public getPrice(): number {
        return this.getNode().getPrice()
    }
    public getDistanceHops() : number{
        return 2
    }
}
