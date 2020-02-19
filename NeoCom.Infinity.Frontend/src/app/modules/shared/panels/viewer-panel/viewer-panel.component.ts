// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - DOMAIN
import { EVariant } from '@app/domain/interfaces/EPack.enumerated';
import { AppComponent } from '@app/app.component';
import { AppPanelComponent } from '../app-panel/app-panel.component';
import { ICollaboration } from '@domain/interfaces/ICollaboration.interface';

@Component({
    selector: 'viewer-panel',
    templateUrl: './viewer-panel.component.html',
    styleUrls: ['./viewer-panel.component.scss']
})
export class ViewerPanelComponent /*implements OnInit*/ {
    @Input() downloadtitle: string;
    @Input() downloader: AppPanelComponent;
    @Input() variant: EVariant = EVariant.DEFAULT;
    protected downloading: boolean = true;
    // private variant: string | EVariant = '-DEFAULT-';

    // constructor() { }

    // ngOnInit() {
    // }
    // public setVariant(variant: string | EVariant): void {
    //     this.variant = variant;
    // }
    public getNodes2Render(): ICollaboration[] {
        return [];
    }
}
