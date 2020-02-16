import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-panel',
    templateUrl: './app-panel.component.html',
    styleUrls: ['./app-panel.component.scss']
})
export class AppPanelComponent implements OnInit {
    protected downloading: boolean = false;
    private variant: string = '-DEFAULT-';

    constructor() { }

    ngOnInit() {
    }
    public setVariant(variant: string): void {
        this.variant = variant;
    }
}
