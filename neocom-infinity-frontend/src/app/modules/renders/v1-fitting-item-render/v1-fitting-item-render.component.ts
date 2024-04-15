// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { EveItemDto } from 'neocom-domain/EveItemDto.dto';
import { FittingItem } from '@domain/FittingItem.domain';
import { BuildActionDto } from '@domain/industry/dto/BuildActionDto.dto';
import { FittingBuildConfigurationDto } from '@domain/industry/dto/FittingBuildConfigurationDto.dto';
import { MarketOrderDto } from '@domain/industry/dto/MarketOrderDto.dto';
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
    @Input() id: string
    // - G E T T E R S
    public getNode(): FittingItemHAL {
        return this.node as FittingItemHAL
    }
    public getUniqueId(): string {
        return this.id
    }
    public getName(): string {
        if (this.node) return this.getNode().getName()
    }
    public getURLIcon(): string {
        if (this.node) return this.getNode().getURLIcon()
    }
    public getModuleGroup(): string {
        if (this.node) return this.getNode().getModuleGroup()
    }
    public getTech(): string {
        return 'Tech I'
    }
}
