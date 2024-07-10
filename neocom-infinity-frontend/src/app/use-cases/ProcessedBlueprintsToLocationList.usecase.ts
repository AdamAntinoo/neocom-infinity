import { Injectable, Optional } from '@angular/core'
import { Observable, delay, mergeMap, of } from 'rxjs'

import { V1SecuredProxyAdapter } from '@adapter/outbound/SecuredProxy/v1.SecuredProxy.adapter'
import { V1BlueprintCategoryContainer } from '@app/industry/domain/v1.BlueprintCategory.container'
import { V1BlueprintDto } from 'neocom-domain'
import { V1BlueprintItem } from '@app/industry/domain/v1.BlueprintItem.domain'
import { BackendFactory } from '@adapter/factory/BackendFactory'
import { SecureUseCaseInput } from './ISecureUseCaseInput.interface'
import { V1ProcessedBlueprintDto } from 'neocom-domain/v1.ProcessedBlueprint.dto'
import { V2ProcessedBlueprint } from '@app/industry/domain/v2.ProcessedBlueprint.domain'
import { NeoComCredential } from '@domain/NeoComCredential.domain'
import { STORAGE_LINKS } from '@env/PlatformConstants'
import { NeoComException } from '@innovative/domain/NeoComException'

export interface ProcessedBlueprintToCategoryListUseCaseInput {
	token: string,
    pilotId:number
}
@Injectable({
	providedIn: 'root',
})
export class ProcessedBlueprintsToLocationListUseCase {
	constructor(private readonly secureAdapter: V1SecuredProxyAdapter, private readonly factory: BackendFactory) {}

	public execute(input: ProcessedBlueprintToCategoryListUseCaseInput): Observable<V2ProcessedBlueprint[]> {
		const blueprints = this.secureAdapter.v3_apiNeocomProcessedBlueprintsData(input.token,input.pilotId).pipe(
			mergeMap(async (blueprints: V1ProcessedBlueprintDto[]) => {
				const resolveData: V2ProcessedBlueprint[] = []
				for (let blueprintDto of blueprints) {
					const blueprint: V2ProcessedBlueprint = await this.resolvePromise(blueprintDto)
					resolveData.push(blueprint)
				}
				return resolveData
			}),
		)
		return blueprints
	}
	private async resolvePromise(blueprint: V1ProcessedBlueprintDto): Promise<V2ProcessedBlueprint> {
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
