import { V1MiningOperationsAdapterService } from '@adapter/outbound/mining-operations/V1.mining-operations.adapter'
import { Component, Input, OnInit } from '@angular/core'
import { V1BlueprintListPageComponent } from '@app/industry/pages/v1-blueprint-list-page/v1-blueprint-list-page.component'
import { V1MiningOperationsPageComponent } from '@app/industry/pages/v1-mining-operations-page/V1MiningOperationsPage'
import { AppStoreService } from '@app/services/appstore.service'
import { BlueprintToCategoryListUseCase } from '@app/usecases/BlueprintToCategoryList.usecase'
import { V1BlueprintCategoryContainer } from '@app/industry/domain/v1.BlueprintCategory.container'
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

	constructor(protected readonly appStore: AppStoreService, private readonly useCase: BlueprintToCategoryListUseCase) {
		super()
	}
	public ngOnInit(): void {
		this.refresh()
	}

	public clean(): void {}
	public refresh(): void {
		this.clean()
		this.backendConnections.push(
			this.useCase.execute({ token: '-now-defined-as-a-cookie' }).subscribe(categories => {
				console.log('download completed')
				this.completeDowload(categories)
                const nodes = this.getNodes2Render()
                console.log('nodes->' + JSON.stringify(nodes))
			}),
		)
	}
}
