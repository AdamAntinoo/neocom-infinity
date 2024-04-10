import { Component, Input, OnInit } from '@angular/core';
import { V1MiningOperationsPageComponent } from '../../pages/v1-mining-operations-page/V1MiningOperationsPage';
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface';
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component';
import { V1MiningOperationsAdapterService } from '@infra/adapters/outbound/mining-operations/v1-mining-operations-adapter.service';

@Component({
    selector: 'v1-miningoperations-panel',
    templateUrl: './v1-miningoperations-panel.component.html',
    styleUrls: ['./v1-miningoperations-panel.component.scss']
})
export class V1MiningOperationsPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() container: V1MiningOperationsPageComponent

    constructor(private readonly miningOperationsAdapter: V1MiningOperationsAdapterService) {
        super()
    }
    ngOnInit(): void {
        this.refresh()
    }

    clean(): void { }
    refresh(): void {
        this.clean()
        const characterId = 34
        this.backendConnections.push(this.miningOperationsAdapter.downloadMiningOperationsForCharacter(characterId)
            .subscribe(miningOperations => {
                console.log('download completed')
                this.completeDowload(miningOperations)
            }))
    }
}
