// - CORE
import { Component } from '@angular/core';
import { Input } from '@angular/core';
// - DOMAIN
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core';
import { HALNode } from '@domain/hal/HALNode.hal';
import { IViewer } from '@domain/interfaces/IViewer.interface';
import { NeoCom } from '@domain/NeoCom.domain';

@Component({
    selector: 'v2-node-container',
    templateUrl: './v2-node-container-render.component.html',
    styleUrls: ['./v2-node-container-render.component.scss']
})
export class V2NodeContainerRenderComponent extends BackgroundEnabledComponent {
    @Input() container: IViewer;
    @Input() node: NeoCom | HALNode;
    @Input() variant: string = '-DEFAULT-';
    @Input() index: number = 1;
    @Input() selectOnHover: boolean = false

    // - G E T T E R S
    public getNode(): NeoCom {
        return this.node;
    }
    public getVariant(): string {
        return this.variant;
    }
    /**
     * Pass the container panel the node that is being entered so if there is additional data it can be exported to another panel.
     * @param target target node that is being entered by the cursor.
     */
    public mouseEnter(target: any) {
        // if (this.selectOnHover) this.container.addSelection(target as ISelectable);
    }
    public toggleExpanded(): void {
        if (null != this.node) {
            if (this.node.isExpandable()) {
                this.node.toggleExpanded()
                this.container.notifyDataChanged();
            }
        }
    }
    // public select(): void {
    //     this.node.select()
    //     this.container.addSelection(this.node)
    // }
    // public unselect(): void {
    //     this.node.unselect()
    //     this.container.subtractSelection(this.node)
    // }
    public isExpanded(): boolean {
        if (null != this.node)
            return this.node.isExpanded();
    }
    public isActive(): boolean {
        if (null != this.node) return this.node.isActive();
        else return true;
    }
    public isEmpty(target?: any): boolean {
        if (null == target) return true;
        if (Object.keys(target).length == 0) return true;
        return false;
    }
}
