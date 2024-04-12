import { ConfigService } from "@nestjs/config"
import { HttpService } from "@nestjs/axios"
import { AxiosHeaders, AxiosRequestConfig, AxiosResponse } from "axios"
import { lastValueFrom, map } from "rxjs"
import { IEsiMiningSecureService } from "application/ports/IEsiMiningSecureService.port"
import { GetCharactersCharacterIdMining200Ok } from "application/domain/esi-model/models"
import { V1MiningOperationDto } from "neocom-domain/dto/V1MiningOperationDto.dto"

export class ESIMiningSecureService implements IEsiMiningSecureService<GetCharactersCharacterIdMining200Ok>{
    constructor(
        private readonly httpService: HttpService,
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

        return await lastValueFrom(this.httpService.get<V1MiningOperationDto[]>(request, config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                return axiosResponse.data as GetCharactersCharacterIdMining200Ok[]
            }))
        )
    }
}
