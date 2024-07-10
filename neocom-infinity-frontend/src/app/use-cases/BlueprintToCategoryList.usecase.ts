import { Injectable, Optional } from '@angular/core'
import { Observable, delay, mergeMap, of } from 'rxjs'

import { V1SecuredProxyAdapter } from '@adapter/outbound/SecuredProxy/v1.SecuredProxy.adapter'
import { V1BlueprintCategoryContainer } from '@app/industry/domain/v1.BlueprintCategory.container'
import { V1BlueprintDto } from 'neocom-domain'
import { V1BlueprintItem } from '@app/industry/domain/v1.BlueprintItem.domain'
import { BackendFactory } from '@adapter/factory/BackendFactory'

export interface BlueprintToCategoryListUseCaseInput {
	token: string
}
@Injectable({
    providedIn: 'root'
})
export class BlueprintToCategoryListUseCase {
	constructor(private readonly secureAdapter: V1SecuredProxyAdapter, private readonly factory: BackendFactory) {}

	public execute(input: BlueprintToCategoryListUseCaseInput): Observable<V1BlueprintCategoryContainer[]> {
		const blueprints = this.secureAdapter.v3_apiNeocomBlueprintsData(input.token).pipe(
			mergeMap(async (blueprints: V1BlueprintDto[]) => {
				const resolveData: V1BlueprintCategoryContainer[] = []
				for (let blueprintDto of blueprints) {
					const blueprint: V1BlueprintItem = await this.resolvePromise(blueprintDto)
					this.addBlueprint(blueprint, resolveData)
				}
				return resolveData
			}),
		)
		return blueprints
	}
	private async resolvePromise(blueprint: V1BlueprintDto): Promise<V1BlueprintItem> {
		return await this.factory.construct(blueprint)
	}
	private addBlueprint(blueprint: V1BlueprintItem, data: V1BlueprintCategoryContainer[]): void {
		if (undefined === this.findCategory(blueprint.type.categoryId, data)) {
			const category = new V1BlueprintCategoryContainer()
			category.categoryId = blueprint.type.categoryId
			category.name = blueprint.type.categoryName
			data.push(category)
			category.addBlueprint(blueprint)
		} else {
			this.findCategory(blueprint.type.categoryId, data).addBlueprint(blueprint)
		}
	}
	private findCategory(categoryId: number, categories: V1BlueprintCategoryContainer[]): V1BlueprintCategoryContainer | undefined {
		for (const category of categories) {
			if (category.categoryId === categoryId) return category
		}
		return undefined
	}
}
