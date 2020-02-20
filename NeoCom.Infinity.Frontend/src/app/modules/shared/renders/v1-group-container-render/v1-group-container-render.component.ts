// - CORE
import { Component } from '@angular/core';
// - RENDERS
import { NodeContainerRenderComponent } from '../node-container-render/node-container-render.component';
// - DOMAIN
import { GroupContainer } from '@domain/GroupContainer.domain';

@Component({
    selector: 'v1-group-container',
    templateUrl: './v1-group-container-render.component.html',
    styleUrls: ['./v1-group-container-render.component.scss']
})
export class V1GroupContainerRenderComponent extends NodeContainerRenderComponent {

    constructor() { super(); }

    public getTitle(): string {
        let group = this.getNode() as GroupContainer;
        return group.getGroupTitle();
    }
    public getGroupIconReference(): string {
        if (null != this.getNode()) {
            let group = this.getNode() as GroupContainer;
            return group.getGroupIconReference();
        }
    }
    public getContentsCount(): number {
        if (null != this.getNode()) {
            let group = this.getNode() as GroupContainer;
            return group.getContentsCount();
        } else return 0;
    }
}
