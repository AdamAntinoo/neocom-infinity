// - CORE
import { Component } from '@angular/core';
// - RENDERS
import { NodeContainerRenderComponent } from '../node-container-render/node-container-render.component';
// - DOMAIN
import { GroupContainer } from '@domain/GroupContainer.domain';
import { EVariant } from '@domain/interfaces/EPack.enumerated';

@Component({
    selector: 'v1-group-container',
    templateUrl: './v1-group-container-render.component.html',
    styleUrls: ['./v1-group-container-render.component.scss']
})
export class V1GroupContainerRenderComponent extends NodeContainerRenderComponent {
    //   private  colorScheme: string = 'panel-white';  // The name of the panel color style to be rendered.

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
    // public getColorSchemePanelStyle(): string {
    //     return 'panel-red';
    // }
    // public getPanelStyle(): string {
    //     return this.colorScheme;
    // }
    // public getExpandedPanelStyle(): string {
    //     return this.colorScheme /*+ '-expanded'*/
    // }
    // public getSelectedPanelStyle(): string {
    //     return this.colorScheme + '-selected'
    // }
    // public getExpandedSelectedPanelStyle(): string {
    //     return this.getExpandedPanelStyle() + ' ' + this.getSelectedPanelStyle();
    // }
    public getVariant(): string {
        return EVariant.DEFAULT;
    }
}
