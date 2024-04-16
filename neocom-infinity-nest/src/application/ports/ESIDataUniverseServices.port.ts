import { GetUniverseCategoriesCategoryIdOk } from "application/domain/esi-model/getUniverseCategoriesCategoryIdOk";
import { GetUniverseGroupsGroupIdOk } from "application/domain/esi-model/getUniverseGroupsGroupIdOk";
import { GetUniverseTypesTypeIdOk } from "application/domain/esi-model/getUniverseTypesTypeIdOk";
import { FuzzWorkMarketData } from "neocom-domain";

export abstract class ESIDataUniverseServicesPort {
    public abstract getEsiType(typeId: number): Promise<GetUniverseTypesTypeIdOk>
    public abstract getEsiGroup(groupId: number): Promise<GetUniverseGroupsGroupIdOk>
    public abstract getEsiCategory(categoryId: number): Promise<GetUniverseCategoriesCategoryIdOk>
    public abstract getFuzzWorkMarketData(typeId: number, systemId: number): Promise<FuzzWorkMarketData>
}
