// - CORE
import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { ChangeDetectionStrategy } from '@angular/core';
// - DOMAIN
import { AppPanelComponent } from '../app-panel/app-panel.component';
import { ICollaboration } from '@domain/interfaces/ICollaboration.interface';
import { NCVariant } from '@env/NeoComVariants';
import { platformConstants } from '@env/platform-constants';

@Component({
    selector: 'viewer-panel',
    templateUrl: './viewer-panel.component.html',
    styleUrls: ['./viewer-panel.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class ViewerPanelComponent {
    @Input() nodes2render: ICollaboration[] = [];
    @Input() downloadtitle: string;
    @Input() downloader: AppPanelComponent;
    @Input() variant: string = platformConstants.DEFAULT_VARIANT;

    public isDownloading(): boolean {
        console.log('>[ViewerPanelComponent.isDownloading]')
        if (null != this.downloader) return this.downloader.isDownloading();
        else return true;
    }
    public getNodes2Render(): ICollaboration[] {
        console.log('><[ViewerPanelComponent.getNodes2Render]> count: ' + this.nodes2render.length)
        return this.nodes2render;
    }
}
