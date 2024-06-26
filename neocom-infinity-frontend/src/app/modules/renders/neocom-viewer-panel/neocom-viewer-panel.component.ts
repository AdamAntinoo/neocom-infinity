// - CORE
import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface';
// - DOMAIN
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component';

@Component({
    selector: 'neocom-viewer-panel',
    templateUrl: './neocom-viewer-panel.component.html',
    styleUrls: ['./neocom-viewer-panel.component.scss']
})
export class NeocomViewerPanelComponent {
    @Input() nodes2render: ICollaboration[] = [];
    @Input() downloadtitle: string;
    @Input() downloader: AppPanelComponent;
    @Input() variant: string = '-DEFAULT-';
    @Input() selectOnHover: boolean = false // If true hovered node becomes the selected node.
    public index: number = 1;

    public isDownloading(): boolean {
        if (null != this.downloader) return this.downloader.isDownloading();
        else return true;
    }
    public getNodes2Render(): ICollaboration[] {
        console.log('><[ViewerPanelComponent.getNodes2Render]> count: ' + this.nodes2render.length)
        return this.nodes2render;
    }
    /**
     * This feature is deprecated since it changes values of renders after checked. Review this functionality use.
     */
    public getNextIndex(): number {
        return 1;
    }
    public getVariant(): string {
        return this.variant;
    }
}
