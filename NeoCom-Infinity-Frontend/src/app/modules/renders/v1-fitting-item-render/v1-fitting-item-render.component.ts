// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { EveItemDao } from '@domain/core/dao/EveItemDao.dao';
import { FittingItem } from '@domain/FittingItem.domain';
import { BuildActionDao } from '@domain/industry/dao/BuildActionDao.dao';
import { FittingBuildConfigurationDao } from '@domain/industry/dao/FittingBuildConfigurationDao.dao';
import { FittingItemHAL } from '@domain/industry/hal/FittingItemHAL.hal';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';
import { Observable } from 'rxjs';
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';

@Component({
    selector: 'v1-fitting-item',
    templateUrl: './v1-fitting-item-render.component.html',
    styleUrls: ['./v1-fitting-item-render.component.scss']
})
export class V1FittingItemRenderComponent extends V2NodeContainerRenderComponent {
    // - G E T T E R S
    public getHalNode(): FittingItemHAL {
        return this.node as FittingItemHAL
    }
    // public getName(): Promise<string> {
    //     console.log('>[V1FittingItemRenderComponent.getName]>Name: ' + this.getHalNode().getName())
    //     // return 'hh' //this.getNode().getName()
    //     return this.getHalNode().getName()
    // }
    public async getName(): Promise<string> {
        console.log('>[V1FittingItemRenderComponent.getName]>Name: ' + this.getHalNode().getName())
        // return 'hh' //this.getNode().getName()
        return await this.getHalNode().getName() +"jkhkjlhkj"
    }
    public getTech(): string {
        return 'Tech I'
    }
    // public async getItem(): Promise<EveItemDao> {
    //     return this.getHalNode().getItem()
    // }
    // public async getURLIcon(): Promise<string> {
    //     const item = await this.getItem()
    //     if (item) return item.getURLIcon()
    // }
    public getStationName(): string {
        return 'hh' //this.getNode().getName()
    }
    public getPrice(): number {
        return 123
    }
    public getDistanceHops(): number {
        return 2
    }
}
