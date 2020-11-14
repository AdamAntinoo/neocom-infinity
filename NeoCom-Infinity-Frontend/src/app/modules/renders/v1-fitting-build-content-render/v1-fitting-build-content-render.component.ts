// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ChangeDetectorRef } from '@angular/core';
// - SERVICES
import { HALResolver } from '@app/services/HALResolver.service';
// - DOMAIN
import { FittingItem } from '@domain/FittingItem.domain';
import { FittingBuildContentDao } from '@domain/industry/dao/FittingBuildContentDao.dao';
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';
import { EveItemDao } from '@domain/core/dao/EveItemDao.dao';
import { FittingItemHAL } from '@domain/industry/hal/FittingItemHAL.hal';
import { MarketOrderDao } from '@domain/industry/dao/MarketOrderDao.dao';

@Component({
    selector: 'v1-fitting-build-content',
    templateUrl: './v1-fitting-build-content-render.component.html',
    styleUrls: ['./v1-fitting-build-content-render.component.scss']
})
export class V1FittingBuildContentRenderComponent extends V2NodeContainerRenderComponent {
    public downloading: boolean = true
    private targetFittingItem: FittingItemHAL

    constructor(
        protected halResolver: HALResolver,
        protected changeDetector: ChangeDetectorRef
    ) { super() }

    public getNode(): FittingBuildContentDao {
        return this.node as FittingBuildContentDao
    }
    public getUniqueId(): string {
        return this.getNode().getId()
    }
    /**
     * Used to start the fitting resolution loop and get a response to complete the downloading.
     */
    public fireFittingResolution(): boolean {
        console.log('>[V1FittingBuildContentRenderComponent.fireFittingResolution]')
        const fittingItem: FittingItemHAL = this.getNode().getFittingItem()
        fittingItem.setResolver(this.halResolver)
        // Check if the item link is resolved
        if (fittingItem.item.isDownloaded) this.targetFittingItem = fittingItem
        else {
            console.log('>[V1FittingBuildContentRenderComponent.fireFittingResolution]>Accessing Item')
            fittingItem.accessItem().then(item => {
                fittingItem.item.target = new EveItemDao(item)
                this.targetFittingItem = fittingItem
                this.downloading = false
                this.changeDetector.markForCheck()
            })
        }
        console.log('<[V1FittingBuildContentRenderComponent.getFittingItem]>FittingItem: ' + this.targetFittingItem)
        return true
    }
    /**
    * The HAL access should wait until the depending links are resolved.
    */
    public getFittingItem(): FittingItemHAL {
        // console.log('>[V1FittingBuildContentRenderComponent.getFittingItem]')
        // const fittingItem: FittingItemHAL = this.getNode().getFittingItem()
        // fittingItem.setResolver(this.halResolver)
        // // Check if the item link is resolved
        // if (fittingItem.item.isDownloaded) this.targetFittingItem = fittingItem
        // else {
        //     console.log('>[V1FittingBuildContentRenderComponent.getFittingItem]>Accessing Item')
        //     fittingItem.accessItem().then(item => {
        //         fittingItem.item.target = new EveItemDao(item)
        //         this.targetFittingItem = fittingItem
        //         this.downloading = false
        //     })
        // }
        // console.log('<[V1FittingBuildContentRenderComponent.getFittingItem]>FittingItem: ' + this.targetFittingItem)
        return this.targetFittingItem
    }
    public getMarketData(): MarketOrderDao {
        if (this.node) return this.getNode().getMarketOrder()
    }
}
