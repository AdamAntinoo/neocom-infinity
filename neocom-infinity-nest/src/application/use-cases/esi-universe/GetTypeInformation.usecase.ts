import { Injectable } from "@nestjs/common";
import { GetUniverseCategoriesCategoryIdOk } from "application/domain/esi-model/getUniverseCategoriesCategoryIdOk";
import { GetUniverseGroupsGroupIdOk } from "application/domain/esi-model/getUniverseGroupsGroupIdOk";
import { GetUniverseTypesTypeIdOk } from "application/domain/esi-model/getUniverseTypesTypeIdOk";
import { ESIDataUniverseServicesPort } from "application/ports/ESIDataUniverseServices.port";
import { V1EsiTypeDto } from "neocom-domain";

declare namespace GetTypeInformationUseCase {
    export type Request = number
}

@Injectable()
export class GetTypeInformationUseCase {
    private esiUniverseTypeUrl = '/esi/v1/fuzzworks/marketData/'

    constructor(private readonly esiUniverseServices: ESIDataUniverseServicesPort) { }

    public async esiGetTypeInformation(typeId: GetTypeInformationUseCase.Request): Promise<V1EsiTypeDto> {
        const esiType: GetUniverseTypesTypeIdOk = await this.esiUniverseServices.getEsiType(typeId)
        const esiGroup: GetUniverseGroupsGroupIdOk = await this.esiUniverseServices.getEsiGroup(esiType.group_id)
        const esiCategory: GetUniverseCategoriesCategoryIdOk = await this.esiUniverseServices.getEsiCategory(esiGroup.category_id)
        // - compose the type
        return new Promise<V1EsiTypeDto>((resolve) => {
            const type: V1EsiTypeDto = new V1EsiTypeDto({
                typeId: esiType.type_id,
                name: esiType.name,
                description: esiType.description,
                iconId: esiType.icon_id,
                groupId: esiType.group_id,
                groupName: esiGroup.name,
                categoryId: esiGroup.category_id,
                categoryName: esiCategory.name,
                volume: esiType.volume,
                marketDataLink: this.generateMarketDataLink(esiType.type_id)
            })
            resolve(type)
        })
    }

    private generateMarketDataLink(typeId: number): string {
        return this.esiUniverseTypeUrl + typeId
    }
}
