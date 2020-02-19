// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - DOMAIN
import { EVariant } from '@app/domain/interfaces/EPack.enumerated';

@Component({
    selector: 'app-panel',
    templateUrl: './app-panel.component.html',
    styleUrls: ['./app-panel.component.scss']
})
export class AppPanelComponent /*implements OnInit */ {
    @Input() self: AppPanelComponent;
    @Input() variant: string | EVariant = '-DEFAULT-';
    protected downloading: boolean = false;
    // private variant: string | EVariant = '-DEFAULT-';

    constructor() {
        this.self = this;
    }

    // ngOnInit() {    }
    public setVariant(variant: string | EVariant): void {
        this.variant = variant;
    }
}
