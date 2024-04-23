import { GetUniverseCategoriesCategoryIdOk } from "application/domain/esi-model/getUniverseCategoriesCategoryIdOk";
import { GetUniverseConstellationsConstellationIdOk } from "application/domain/esi-model/getUniverseConstellationsConstellationIdOk";
import { GetUniverseGroupsGroupIdOk } from "application/domain/esi-model/getUniverseGroupsGroupIdOk";
import { GetUniverseRegionsRegionIdOk } from "application/domain/esi-model/getUniverseRegionsRegionIdOk";
import { GetUniverseStationsStationIdOk } from "application/domain/esi-model/getUniverseStationsStationIdOk";
import { GetUniverseSystemsSystemIdOk } from "application/domain/esi-model/getUniverseSystemsSystemIdOk";
import { GetUniverseTypesTypeIdOk } from "application/domain/esi-model/getUniverseTypesTypeIdOk";
import { ESIDataUniverseServicesPort } from "application/ports/ESIDataUniverseServices.port";
import { FuzzWorkMarketData } from "neocom-domain";

export class EsiDataUniverseMock implements ESIDataUniverseServicesPort {
    public getEsiType(typeId: number): Promise<GetUniverseTypesTypeIdOk> {
        console.log('request->' + typeId)
        return new Promise((resolve) => {
            resolve({
                type_id: typeId,
                name: '-type-name-',
                description: '-test-description-',
                group_id: 34,
                icon_id: 34,
                volume: 0.1
            } as GetUniverseTypesTypeIdOk)
        })
    }
    public getEsiGroup(groupId: number): Promise<GetUniverseGroupsGroupIdOk> {
        console.log('request->' + groupId)
        return new Promise((resolve) => {
            resolve({
                group_id: groupId,
                name: '-group-name-',
                category_id: 76
            } as GetUniverseGroupsGroupIdOk)
        })
    }
    public getEsiCategory(categoryId: number): Promise<GetUniverseCategoriesCategoryIdOk> {
        console.log('request->' + categoryId)
        return new Promise((resolve) => {
            resolve({
                category_id: categoryId,
                name: '-category-name-'
            } as GetUniverseCategoriesCategoryIdOk)
        })
    }
    public getFuzzWorkMarketData(typeId: number, systemId: number): Promise<FuzzWorkMarketData|any> {
        console.log('request->' + typeId + '/' + systemId)
        return new Promise((resolve) => {
            resolve({
                17464: {
                    buy:
                    {
                        weightedAverage: 11.29852562537868,
                        max: 13.86,
                        min: 0.01,
                        stddev: 5.6712547112609935,
                        median: 10.02,
                        volume: 7943707.0,
                        orderCount: 5,
                        percentile: 13.86
                    },
                    sell: {
                        weightedAverage: 20.38022049993837,
                        max: 35.0,
                        min: 16.0,
                        stddev: 6.661956792163517,
                        median: 18.67,
                        volume: 9597463.0,
                        orderCount: 18,
                        percentile: 16.77646731954059
                    }
                }
            })
        })
    }
    public getUniverseRegion(locationId: number): Promise<GetUniverseRegionsRegionIdOk> {
        console.log('locationId->' + locationId)
        return new Promise((resolve) => {
            resolve({
                region_id: locationId,
                name: 'The Forge'
            } as GetUniverseRegionsRegionIdOk)
        })
    }
    public getUniverseConstellation(locationId: number): Promise<GetUniverseConstellationsConstellationIdOk> {
        console.log('locationId->' + locationId)
        return new Promise((resolve) => {
            resolve({
                region_id: 10000002,
                constellation_id: locationId,
                name: 'Kimotoro'
            } as GetUniverseConstellationsConstellationIdOk)
        })
    }
    public getUniverseSystem(locationId: number): Promise<GetUniverseSystemsSystemIdOk> {
        console.log('locationId->' + locationId)
        return new Promise((resolve) => {
            resolve({
                constellation_id: 20000020,
                system_id: locationId,
                name: 'Jita'
            } as GetUniverseSystemsSystemIdOk)
        })
    }
    public getUniverseStation(locationId: number): Promise<GetUniverseStationsStationIdOk> {
        throw new Error("Method not implemented.");
    }

}
