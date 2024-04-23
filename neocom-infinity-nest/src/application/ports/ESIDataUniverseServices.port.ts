import { GetUniverseCategoriesCategoryIdOk } from "application/domain/esi-model/getUniverseCategoriesCategoryIdOk";
import { GetUniverseConstellationsConstellationIdOk } from "application/domain/esi-model/getUniverseConstellationsConstellationIdOk";
import { GetUniverseGroupsGroupIdOk } from "application/domain/esi-model/getUniverseGroupsGroupIdOk";
import { GetUniverseRegionsRegionIdOk } from "application/domain/esi-model/getUniverseRegionsRegionIdOk";
import { GetUniverseStationsStationIdOk } from "application/domain/esi-model/getUniverseStationsStationIdOk";
import { GetUniverseSystemsSystemIdOk } from "application/domain/esi-model/getUniverseSystemsSystemIdOk";
import { GetUniverseTypesTypeIdOk } from "application/domain/esi-model/getUniverseTypesTypeIdOk";
import { FuzzWorkMarketData } from "neocom-domain";

export abstract class ESIDataUniverseServicesPort {
    public abstract getEsiType(typeId: number): Promise<GetUniverseTypesTypeIdOk>
    public abstract getEsiGroup(groupId: number): Promise<GetUniverseGroupsGroupIdOk>
    public abstract getEsiCategory(categoryId: number): Promise<GetUniverseCategoriesCategoryIdOk>
    public abstract getFuzzWorkMarketData(typeId: number, systemId: number): Promise<FuzzWorkMarketData>

    public abstract getUniverseRegion(locationId: number): Promise<GetUniverseRegionsRegionIdOk>
    public abstract getUniverseConstellation(locationId: number): Promise<GetUniverseConstellationsConstellationIdOk>
    public abstract getUniverseSystem(locationId: number): Promise<GetUniverseSystemsSystemIdOk>
    public abstract getUniverseStation(locationId: number): Promise<GetUniverseStationsStationIdOk>
}
