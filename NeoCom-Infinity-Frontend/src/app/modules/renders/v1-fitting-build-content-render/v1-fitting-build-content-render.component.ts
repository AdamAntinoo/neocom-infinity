// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { Observable } from 'rxjs';
// - SERVICES
import { HALResolver } from '@app/services/HALResolver.service';
// - DOMAIN
import { FittingItem } from '@domain/FittingItem.domain';
import { FittingBuildContentDao } from '@domain/industry/dao/FittingBuildContentDao.dao';
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';
import { EveItemDao } from '@domain/core/dao/EveItemDao.dao';
import { FittingItemHAL } from '@domain/industry/hal/FittingItemHAL.hal';

@Component({
    selector: 'v1-fitting-build-content',
    templateUrl: './v1-fitting-build-content-render.component.html',
    styleUrls: ['./v1-fitting-build-content-render.component.scss']
})
export class V1FittingBuildContentRenderComponent extends V2NodeContainerRenderComponent {
    constructor(protected resolver: HALResolver) { super() }

    public getNode(): FittingBuildContentDao {
        return this.node as FittingBuildContentDao
    }
    public getUniqueId(): string {
        return this.getNode().getId()
    }
    public getFittingItem(): FittingItem {
        return this.getNode().getFittingItem()
    }
}
