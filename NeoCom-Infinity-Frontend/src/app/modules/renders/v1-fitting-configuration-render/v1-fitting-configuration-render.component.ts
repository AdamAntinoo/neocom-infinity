// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { BuildActionDao } from '@domain/industry/BuildActionDao.dao';
import { FittingBuildConfiguration } from '@domain/industry/FittingBuildConfiguration.domain';

@Component({
    selector: 'v1-fitting-configuration',
    templateUrl: './v1-fitting-configuration-render.component.html',
    styleUrls: ['./v1-fitting-configuration-render.component.scss']
})
export class V1FittingConfigurationRenderComponent {
    @Input() node: FittingBuildConfiguration

    // - G E T T E R S   &   S E T T E R S
    public getNode(): FittingBuildConfiguration {
        return this.node
    }
    public getFittingName(): string {
        if (this.getNode()) {
            return this.getNode().getFittingName()
        } else return '-DOWNLOADING-'
    }
    public getHullURLIcon(): string {
        return this.getNode().getHullURLIcon()
    }
    public getHullClass(): string {
        return this.getNode().getHullClass()
    }
    public getHullGroup(): string {
        return this.getNode().getHullGroup()
    }
    public getHullTypeId(): number {
        return this.getNode().getHullTypeId()
    }
    public getHullName(): string {
        return 'HULL'
    }
    public getHullTech(): string {
        return this.getNode().getHullTech()
    }
    public getHullPrice(): number {
        return this.getNode().getHullPrice()
    }
    public getMarketStation(): string {
        return this.getNode().getMarketStation()
    }
    public getHullHops () : number {
        return this.getNode().getHullHops()
    }
    public getHullHopTime(): number {
        return this.getNode().getHullHopTime()
    }

    // - I N T E R A C T I O N S
    public getFittingItems(group: string): BuildActionDao[] {
        return [this.getNode().getFittingItem(group, 0), this.getNode().getFittingItem(group, 0)]
    }
}
