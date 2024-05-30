import { V1MiningOperationsAdapterService } from '@adapter/outbound/mining-operations/V1.mining-operations.adapter'
import { Component, Input, OnInit } from '@angular/core'
import { V1BlueprintListPageComponent } from '@app/industry/pages/v1-blueprint-list-page/v1-blueprint-list-page.component'
import { V1MiningOperationsPageComponent } from '@app/industry/pages/v1-mining-operations-page/V1MiningOperationsPage'
import { AppStoreService } from '@app/services/appstore.service'
import { V1Category } from '@domain/common/v1.Category.domain'
import { EsiCategory } from '@domain/esi/EsiCategory.esi'
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { Observable, delay, of } from 'rxjs'

@Component({
	selector: 'v1-blueprint-categories-panel',
	templateUrl: './v1-blueprint-categories.component.html',
	styleUrls: ['./v1-blueprint-categories.component.scss'],
})
export class V1BlueprintCategoriesPanel extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() container: V1BlueprintListPageComponent

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
        this.backendConnections.push(downloadBlueprintCategories(characterId)
            .subscribe(categories => {
                console.log('download completed')
                this.completeDowload(categories)
            }))
    }
}
function downloadBlueprintCategories(characterId: number) : Observable<V1Category[]> {
    const singleCategory = new V1Category(); // Assuming V1Category can be instantiated like this
    console.log(singleCategory)
    // Set properties of singleCategory as needed
    return of([singleCategory]).pipe(
        delay(1000) // Delay the emission by 1000 milliseconds (1 second)
    );
}
