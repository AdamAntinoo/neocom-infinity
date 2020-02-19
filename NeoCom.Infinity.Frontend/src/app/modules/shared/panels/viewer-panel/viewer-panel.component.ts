// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { ChangeDetectionStrategy } from '@angular/core';
// - DOMAIN
import { EVariant } from '@app/domain/interfaces/EPack.enumerated';
import { AppComponent } from '@app/app.component';
import { AppPanelComponent } from '../app-panel/app-panel.component';
import { ICollaboration } from '@domain/interfaces/ICollaboration.interface';

@Component({
    selector: 'viewer-panel',
    templateUrl: './viewer-panel.component.html',
    styleUrls: ['./viewer-panel.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class ViewerPanelComponent {
    @Input() updateSignal: boolean;
    @Input() downloadtitle: string;
    @Input() downloader: AppPanelComponent;
    @Input() variant: EVariant = EVariant.DEFAULT;
    // protected downloading: boolean = true;
    // private variant: string | EVariant = '-DEFAULT-';

    // constructor() { }

    // ngOnInit() {
    // }
    // public setVariant(variant: string | EVariant): void {
    //     this.variant = variant;
    // }
    public isDownloading(): boolean {
        if (null != this.downloader) return this.downloader.isDownloading();
        else return true;
    }
    public getNodes2Render(): ICollaboration[] {
        console.log('><[ViewerPanelComponent.getNodes2Render]')
        let nodes = [];
        if (null != this.downloader) {
            let nodes = this.downloader.getNodes2Render();
            console.log('><[ViewerPanelComponent.getNodes2Render]> count: ' + nodes.length)
        }
        return nodes;
    }
}
