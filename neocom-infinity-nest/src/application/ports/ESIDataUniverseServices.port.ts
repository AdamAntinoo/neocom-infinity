import { GetUniverseCategoriesCategoryIdOk } from "application/domain/esi-model/getUniverseCategoriesCategoryIdOk";
import { GetUniverseGroupsGroupIdOk } from "application/domain/esi-model/getUniverseGroupsGroupIdOk";
import { GetUniverseTypesTypeIdOk } from "application/domain/esi-model/getUniverseTypesTypeIdOk";

export abstract class ESIDataUniverseServicesPort {
    public abstract getEsiType(typeId: number): Promise<GetUniverseTypesTypeIdOk>
    public abstract getEsiGroup(groupId: number): Promise<GetUniverseGroupsGroupIdOk>
    public abstract getEsiCategory(categoryId: number): Promise<GetUniverseCategoriesCategoryIdOk>
}
