// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { FittingInfoDto } from '@domain/industry/dto/FittingInfoDto.dto';

@Component({
    selector: 'v1-fitting-configuration',
    templateUrl: './v1-fitting-configuration-render.component.html',
    styleUrls: ['./v1-fitting-configuration-render.component.scss']
})
export class V1FittingConfigurationRenderComponent {
    @Input() node: FittingInfoDto

    // - G E T T E R S   &   S E T T E R S
    public getNode(): FittingInfoDto {
        return this.node
    }
    public getFittingName(): string {
        if (this.getNode()) {
            return this.getNode().fitting.name
        } else return '-DOWNLOADING-'
    }
    public getHullURLIcon(): string {
        return this.getNode().fitting.shipHull['urlforItem']
    }
    public getHullClass(): string {
        return this.getNode().fitting.shipHull['groupName']
    }
    public getHullGroup(): string {
        return this.getNode().fitting.shipHull['hullGroup']
    }
    public getHullTypeId(): number {
        return this.getNode().fitting.shipHull['shipTypeId']
    }
    public getHullName(): string {
        return this.getNode().fitting.shipHull['name']
    }
    public getHullTech(): string {
        return 'Tech I'
    }
    public getHullPrice(): number {
        return this.getNode().hullAction.getPrice()
    }
    public getMarketStation(): string {
        return this.getNode().hullAction.getStationName()
    }
    public getHullHops(): number {
        return 2
    }
    public getHullHopTime(): number {
        return 3
    }
    // public getFittingContents(): FittingBuildContentDao[] {
    //     return this.getNode().getContents()
    // }
    // - I N T E R A C T I O N S
    // public getFittingItems(group: string): BuildActionDao[] {
    //     return [this.getNode().getFittingItem(group, 0), this.getNode().getFittingItem(group, 0)]
    // }
}
