import { ESIDataUniverseServicesPort } from '@App/ports/ESIDataUniverseServices.port'
import { Injectable } from '@nestjs/common'
import { GetUniverseCategoriesCategoryIdOk, GetUniverseGroupsGroupIdOk, GetUniverseTypesTypeIdOk, V1EsiTypeDto } from 'neocom-domain'

declare namespace GetTypeInformationUseCase {
	export type Request = number
}

@Injectable()
export class GetTypeInformationUseCase {
	private esiUniverseTypeUrl = '/esi/v1/fuzzworks/marketData/'
	private readonly PREDEFINED_REGION = 30000142

	constructor(private readonly esiUniverseServices: ESIDataUniverseServicesPort) {}

	public async esiGetTypeInformation(typeId: GetTypeInformationUseCase.Request): Promise<V1EsiTypeDto> {
		const esiType: GetUniverseTypesTypeIdOk = await this.esiUniverseServices.getEsiType(typeId)
		const esiGroup: GetUniverseGroupsGroupIdOk = await this.esiUniverseServices.getEsiGroup(esiType.group_id)
		const esiCategory: GetUniverseCategoriesCategoryIdOk = await this.esiUniverseServices.getEsiCategory(esiGroup.category_id)
		// - compose the type
		return new Promise<V1EsiTypeDto>(resolve => {
			const type: V1EsiTypeDto = new V1EsiTypeDto({
				typeId: esiType.type_id,
				name: esiType.name,
				description: esiType.description,
				groupId: esiType.group_id,
				groupName: esiGroup.name,
				categoryId: esiGroup.category_id,
				categoryName: esiCategory.name,
				volume: esiType.volume,
				marketDataLink: this.generateMarketDataLink(esiType.type_id, this.PREDEFINED_REGION),
			})
			resolve(type)
		})
	}

	private generateMarketDataLink(typeId: number, region: number): string {
		return this.esiUniverseTypeUrl + typeId + '/' + region
	}
	private getHullType(typeId: number): Promise<V1EsiTypeDto> {
		return this.esiUniverseServices.getEsiType(typeId)
	}
}
