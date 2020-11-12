// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { BuildActionDao } from '@domain/industry/dao/BuildActionDao.dao';
import { FittingBuildConfigurationDao } from '@domain/industry/dao/FittingBuildConfigurationDao.dao';
import { FittingGroup } from '@domain/industry/FittingGroup.domain';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';

@Component({
    selector: 'v1-fitting-group',
    templateUrl: './v1-fitting-group-render.component.html',
    styleUrls: ['./v1-fitting-group-render.component.scss']
})
export class V1FittingGroupRenderComponent extends V2NodeContainerRenderComponent {
    // - G E T T E R S
    public getNode(): FittingGroup {
        return this.node as FittingGroup
    }
    public  getUniqueId () : string{
        return this.getNode().getId()
    }
    public getName(): string {
        return this.node['name']
    }
}
