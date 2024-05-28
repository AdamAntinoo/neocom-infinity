import {
	GetUniverseTypesTypeIdOk,
	GetUniverseGroupsGroupIdOk,
	GetUniverseCategoriesCategoryIdOk,
	FuzzWorkMarketData,
	GetUniverseRegionsRegionIdOk,
	GetUniverseConstellationsConstellationIdOk,
	GetUniverseSystemsSystemIdOk,
	GetUniverseStationsStationIdOk,
} from 'neocom-domain'

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
