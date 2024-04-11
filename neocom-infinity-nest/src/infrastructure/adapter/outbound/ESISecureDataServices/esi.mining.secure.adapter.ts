import { ConfigService } from "@nestjs/config"
import { HttpService } from "@nestjs/axios"
import { AxiosHeaders, AxiosRequestConfig, AxiosResponse } from "axios"
import { lastValueFrom, map } from "rxjs"
import { MiningOperationConverter } from "./converter/MiningOperationConverter"
import { IESIMiningOperation } from "./domain/IESIMiningOperation.interface"
import { ESISecureDataServiceHALGeneratorAdapter } from "./esi.securedataservice.halgenerator.adapter"
import { TypedResponseTransformer, V2MiningOperation } from "neocom-domain"
import { IEsiMiningSecureService } from "application/ports/IEsiMiningSecureService.port"
import { EsiMining } from "application/use-cases/mining-operation/EsiMining"
import { EsiToken } from "@Infra/adapter/inbound/EsiToken.interface"
import { GetCharactersCharacterIdMining200Ok } from "application/domain/esi-model/models"

export class ESIMiningSecureService implements IEsiMiningSecureService<GetCharactersCharacterIdMining200Ok>{
    constructor(
        private readonly httpService: HttpService,
        private readonly halGenerator: ESISecureDataServiceHALGeneratorAdapter,
        private readonly configuration: ConfigService
    ) { }

    public async getMiningOperations(characterId: number, token: string): Promise<GetCharactersCharacterIdMining200Ok[]> {
        const request = this.configuration.get<string>('BACKEND_HOST') + '/characters/' + characterId + '/mining/'
        console.log('request->' + request)
        const headers: AxiosHeaders = new AxiosHeaders()
        headers.set('Content-Type', 'application/json')
        headers.set('x-neocom-check', 'check header')
        headers.set('authorization', token)
        headers.set('Cache-Control', 'max-age: 600, public')
        const config: AxiosRequestConfig = {
            headers: headers
        }

        return await lastValueFrom(this.httpService.get<V2MiningOperation[]>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as GetCharactersCharacterIdMining200Ok[]
            }))
        )
    }
    private generateAuthorization(token: EsiToken): string {
        return 'Bearer ' + token
    }
}
