import { Component, Input, OnInit } from '@angular/core';
import { V1MiningOperationsPageComponent } from '../../pages/v1-mining-operations-page/V1MiningOperationsPage';
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface';
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component';
import { V1MiningOperationsAdapterService } from '@adapter/outbound/mining-operations/V1.mining-operations.adapter';
import { AppStoreService } from '@app/services/appstore.service';

@Component({
    selector: 'v1-miningoperations-panel',
    templateUrl: './v1-miningoperations-panel.component.html',
    styleUrls: ['./v1-miningoperations-panel.component.scss']
})
export class V1MiningOperationsPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() container: V1MiningOperationsPageComponent

    constructor(
        protected readonly appStore: AppStoreService,
        private readonly miningOperationsAdapter: V1MiningOperationsAdapterService) {
        super()
    }
    public ngOnInit(): void {
        this.refresh()
    }

    public clean(): void { }
    public refresh(): void {
        this.clean()
        const characterId: number = this.appStore.getPilotId()
        this.backendConnections.push(this.miningOperationsAdapter.downloadMiningOperationsForCharacter(characterId)
            .subscribe(miningOperations => {
                console.log('download completed')
                this.completeDowload(miningOperations)
            }))
    }
}
