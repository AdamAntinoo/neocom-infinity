import { Component, Input, OnInit } from '@angular/core';
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component';
import { V1MiningOperationsPageComponent } from '../../pages/V1MiningOperationsPage';

@Component({
    selector: 'v1-miningoperations-panel',
    templateUrl: './v1-miningoperations-panel.component.html',
    styleUrls: ['./v1-miningoperations-panel.component.scss']
})
export class V1MiningOperationsPanelComponent extends AppPanelComponent implements OnInit {
    @Input() container: V1MiningOperationsPageComponent

    constructor() {
        super()
    }

    ngOnInit(): void {
    }

}
