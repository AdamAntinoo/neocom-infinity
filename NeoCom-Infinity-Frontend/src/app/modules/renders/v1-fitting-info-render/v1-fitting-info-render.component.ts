// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { FittingBuildContentDto } from '@domain/industry/dto/FittingBuildContentDto.dto';
import { FittingBuildConfigurationDto } from '@domain/industry/dto/FittingBuildConfigurationDto.dto';
import { FittingInfoDto } from '@domain/industry/dto/FittingInfoDto.dto';

@Component({
    selector: 'v1-fitting-info',
    templateUrl: './v1-fitting-info-render.component.html',
    styleUrls: ['./v1-fitting-info-render.component.scss']
})
export class V1FittingInfoRenderComponent {
    @Input() info: FittingInfoDto
    // - G E T T E R S   &   S E T T E R S
    public getNode(): FittingInfoDto {
        return this.info
    }
    public getFittingName(): string {
        if (this.getNode()) {
            return this.getNode().fitting.name
        } else return '-DOWNLOADING-'
    }
    public getHullClass(): string {
        return this.getNode().hull.getHullClass()
    }
    public getHullGroup(): string {
        return this.getNode().fitting.getHullGroup()
    }

    public getHullURLIcon(): string {
        return this.getNode().fitting.shipHull['urlforItem']
    }
    public getHullTypeId(): number {
        return this.getNode().hull.getHullTypeId()
    }
    // public getHullName(): string {
    //     return this.getNode().fitting.shipHull['name']
    // }
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
}
