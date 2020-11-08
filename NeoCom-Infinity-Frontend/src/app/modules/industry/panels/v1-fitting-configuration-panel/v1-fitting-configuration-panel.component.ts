import { Component, Input, OnInit } from '@angular/core';

@Component({
    selector: 'v1-fitting-configuration-panel',
    templateUrl: './v1-fitting-configuration-panel.component.html',
    styleUrls: ['./v1-fitting-configuration-panel.component.scss']
})
export class V1FittingConfigurationPanelComponent implements OnInit {
    @Input() title: string
    constructor() { }

    ngOnInit(): void {
    }
    public getTitle(): string {
        return this.title.toUpperCase()
    }
}
