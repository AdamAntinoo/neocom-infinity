import { HttpService } from "@nestjs/axios";
import { GetUniverseCategoriesCategoryIdOk } from "application/domain/esi-model/getUniverseCategoriesCategoryIdOk";
import { GetUniverseGroupsGroupIdOk } from "application/domain/esi-model/getUniverseGroupsGroupIdOk";
import { GetUniverseTypesTypeIdOk } from "application/domain/esi-model/getUniverseTypesTypeIdOk";
import { ESIDataUniverseServicesPort } from "application/ports/ESIDataUniverseServices.port";
import { AxiosHeaders, AxiosRequestConfig, AxiosResponse } from "axios";
import { FuzzWorkMarketData } from "neocom-domain/FuzzWorkMarketData.dto";
import { firstValueFrom, lastValueFrom, map } from "rxjs";
import { ConfigService } from "@nestjs/config"
import { IEsiMiningSecureService } from "application/ports/IEsiMiningSecureService.port"
import { GetCharactersCharacterIdMining200Ok } from "application/domain/esi-model/models"
import { V1MiningOperationDto } from "neocom-domain"

export const esiUniverseTypeUrl = '/universe/types/'
export const esiUniverseGroupUrl = '/universe/groups/'
export const esiUniverseCategoryUrl = '/universe/categories/'
export const fuzzWorkMarketDataUrl = 'https://market.fuzzwork.co.uk/aggregates/?region='

export class ESIDataUniverseAdapter implements ESIDataUniverseServicesPort {
    constructor(
        private readonly httpService: HttpService,
        private readonly configuration: ConfigService
    ) { }

    public async getEsiType(typeId: number): Promise<GetUniverseTypesTypeIdOk> {
        const request: string = this.configuration.get<string>('ESI_UNIVERSE_HOST') + esiUniverseTypeUrl + typeId
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
        const request: string = fuzzWorkMarketDataUrl + systemId + '&types=' + typeId
        const config: AxiosRequestConfig = {
            headers: this.generateEsiUniverseHeaders()
        }
        return await firstValueFrom(this.httpService.get<FuzzWorkMarketData>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as FuzzWorkMarketData
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
