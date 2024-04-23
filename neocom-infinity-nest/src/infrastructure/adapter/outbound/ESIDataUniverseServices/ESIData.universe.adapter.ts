import { HttpService } from "@nestjs/axios";
import { firstValueFrom, map } from "rxjs";
import { AxiosHeaders, AxiosRequestConfig, AxiosResponse } from "axios";
import { ConfigService } from "@nestjs/config"

import { GetUniverseCategoriesCategoryIdOk } from "application/domain/esi-model/getUniverseCategoriesCategoryIdOk";
import { GetUniverseGroupsGroupIdOk } from "application/domain/esi-model/getUniverseGroupsGroupIdOk";
import { GetUniverseTypesTypeIdOk } from "application/domain/esi-model/getUniverseTypesTypeIdOk";
import { ESIDataUniverseServicesPort } from "application/ports/ESIDataUniverseServices.port";
import { FuzzWorkMarketData } from "neocom-domain";
import { Injectable } from "@nestjs/common";
import { GetUniverseRegionsRegionIdOk } from "application/domain/esi-model/getUniverseRegionsRegionIdOk";
import { GetUniverseConstellationsConstellationIdOk } from "application/domain/esi-model/getUniverseConstellationsConstellationIdOk";
import { ESI_CONSTANTS } from "@Infra/config/GlobalConstants";
import { GetUniverseSystemsSystemIdOk } from "application/domain/esi-model/getUniverseSystemsSystemIdOk";
import { GetUniverseStationsStationIdOk } from "application/domain/esi-model/getUniverseStationsStationIdOk";

export const esiUniverseTypeUrl = '/universe/types/'
export const esiUniverseGroupUrl = '/universe/groups/'
export const esiUniverseCategoryUrl = '/universe/categories/'

@Injectable()
export class ESIDataUniverseAdapter implements ESIDataUniverseServicesPort {
    constructor(
        private readonly httpService: HttpService,
        private readonly configuration: ConfigService
    ) { }

    public async getEsiType(typeId: number): Promise<GetUniverseTypesTypeIdOk> {
        const request: string = this.configuration.get<string>('ESI_UNIVERSE_HOST') + esiUniverseTypeUrl + typeId
        console.log('type>request->' + request)
        const config: AxiosRequestConfig = {
            headers: this.generateEsiUniverseHeaders()
        }
        return await firstValueFrom(this.httpService.get<GetUniverseTypesTypeIdOk>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as GetUniverseTypesTypeIdOk
            }))
        )
    }
    public async getEsiGroup(groupId: number): Promise<GetUniverseGroupsGroupIdOk> {
        const request: string = this.configuration.get<string>('ESI_UNIVERSE_HOST') + esiUniverseGroupUrl + groupId
        const config: AxiosRequestConfig = {
            headers: this.generateEsiUniverseHeaders()
        }
        return await firstValueFrom(this.httpService.get<GetUniverseGroupsGroupIdOk>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as GetUniverseGroupsGroupIdOk
            }))
        )
    }
    public async getEsiCategory(categoryId: number): Promise<GetUniverseCategoriesCategoryIdOk> {
        const request: string = this.configuration.get<string>('ESI_UNIVERSE_HOST') + esiUniverseCategoryUrl + categoryId
        const config: AxiosRequestConfig = {
            headers: this.generateEsiUniverseHeaders()
        }
        return await firstValueFrom(this.httpService.get<GetUniverseCategoriesCategoryIdOk>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as GetUniverseCategoriesCategoryIdOk
            }))
        )
    }
    public async getFuzzWorkMarketData(typeId: number, systemId: number): Promise<FuzzWorkMarketData> {
        const request: string = this.configuration.get('FUZZ_UNIVERSE_HOST') + ESI_CONSTANTS.FUZZWORK_PREFIX + systemId + '&types=' + typeId
        console.log('marketdata>request->' + request)
        const config: AxiosRequestConfig = {
            headers: this.generateEsiUniverseHeaders()
        }
        return await firstValueFrom(this.httpService.get<FuzzWorkMarketData>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as FuzzWorkMarketData
            }))
        )
    }
    public async getUniverseRegion(locationId: number): Promise<GetUniverseRegionsRegionIdOk> {
        const request: string = this.configuration.get<string>('ESI_UNIVERSE_HOST') + ESI_CONSTANTS.ESI_UNIVERSE_REGION_URL + locationId
            + ESI_CONSTANTS.ESI_UNIVERSE_REQUEST_SUFFIX
        console.log('universeRegion>request->' + request)
        const config: AxiosRequestConfig = {
            headers: this.generateEsiUniverseHeaders()
        }
        return await firstValueFrom(this.httpService.get<GetUniverseRegionsRegionIdOk>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as GetUniverseRegionsRegionIdOk
            }))
        )
    }
    public async getUniverseConstellation(locationId: number): Promise<GetUniverseConstellationsConstellationIdOk> {
        const request: string = this.configuration.get<string>('ESI_UNIVERSE_HOST') + ESI_CONSTANTS.ESI_UNIVERSE_CONSTELLATION_URL + locationId
            + ESI_CONSTANTS.ESI_UNIVERSE_REQUEST_SUFFIX
        console.log('universeConstellation>request->' + request)
        const config: AxiosRequestConfig = {
            headers: this.generateEsiUniverseHeaders()
        }
        return await firstValueFrom(this.httpService.get<GetUniverseConstellationsConstellationIdOk>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as GetUniverseConstellationsConstellationIdOk
            }))
        )
    }
    public async getUniverseSystem(locationId: number): Promise<GetUniverseSystemsSystemIdOk> {
        const request: string = this.configuration.get<string>('ESI_UNIVERSE_HOST') + ESI_CONSTANTS.ESI_UNIVERSE_SYSTEM_URL + locationId
            + ESI_CONSTANTS.ESI_UNIVERSE_REQUEST_SUFFIX
        console.log('universeSystem>request->' + request)
        const config: AxiosRequestConfig = {
            headers: this.generateEsiUniverseHeaders()
        }
        return await firstValueFrom(this.httpService.get<GetUniverseSystemsSystemIdOk>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as GetUniverseSystemsSystemIdOk
            }))
        )
    }
    public async getUniverseStation(locationId: number): Promise<GetUniverseStationsStationIdOk> {
        const request: string = this.configuration.get<string>('ESI_UNIVERSE_HOST') + ESI_CONSTANTS.ESI_UNIVERSE_STATION_URL + locationId
            + ESI_CONSTANTS.ESI_UNIVERSE_REQUEST_SUFFIX
        console.log('universeStation>request->' + request)
        const config: AxiosRequestConfig = {
            headers: this.generateEsiUniverseHeaders()
        }
        return await firstValueFrom(this.httpService.get<GetUniverseStationsStationIdOk>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as GetUniverseStationsStationIdOk
            }))
        )
    }

    private generateEsiUniverseHeaders(): AxiosHeaders {
        const headers: AxiosHeaders = new AxiosHeaders()
        headers.set('Content-Type', 'application/json')
        headers.set('accept', 'application/json')
        headers.set('Accept-Language', 'en')
        headers.set('Cache-Control', 'no-cache')
        return headers
    }
}
