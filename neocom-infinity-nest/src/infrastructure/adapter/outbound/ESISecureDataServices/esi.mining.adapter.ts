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

export class ESIMiningSecureService implements IEsiMiningSecureService<V2MiningOperation>{
    constructor(
        private readonly httpService: HttpService,
        private halGenerator: ESISecureDataServiceHALGeneratorAdapter,
        private readonly configuration: ConfigService
    ) { }

    public async getMiningOperations(characterId: number, token: string): Promise<V2MiningOperation[]> {
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
                console.log('esi data->' + axiosResponse.data)
                console.log('esi data->' + axiosResponse.data.instanceof)
                console.log('esi data->' + JSON.stringify(axiosResponse.data))

                return new TypedResponseTransformer<V2MiningOperation>()
                    .setConverter(new MiningOperationConverter<IESIMiningOperation, V2MiningOperation>(this.halGenerator))
                    .transform(axiosResponse.data)
            }))
        )
    }
    private generateAuthorization(token: EsiToken): string {
        return 'Bearer ' + token
    }
}
