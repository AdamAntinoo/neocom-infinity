import { IEsiMiningSecureService } from "@App/ports/IEsiMiningSecureService.port"
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation"
import { HttpService } from "@nestjs/axios"
import { lastValueFrom, map } from "rxjs"
import { MiningOperationConverter } from "./converter/MiningOperationConverter"
import { IESIMiningOperation } from "./domain/IESIMiningOperation.interface"
import { TypedResponseTransformer } from "@Inno/transformer/TypedResponseTransformer"
import { ESISecureDataServiceHALGeneratorAdapter } from "./esi.securedataservice.halgenerator.adapter"
import { AxiosHeaders, AxiosRequestConfig, AxiosResponse } from "axios"

export class ESIMiningSecureService implements IEsiMiningSecureService<V2MiningOperation>{
    constructor(
        private readonly httpService: HttpService,
        private halGenerator: ESISecureDataServiceHALGeneratorAdapter
        ) { }

    public async getMiningOperations(characterId: number): Promise<V2MiningOperation[]> {
        const request = 'http://localhost:5271' + '/characters/' + characterId + '/mining/'
        console.log('request->' + request)
        const headers : AxiosHeaders= new AxiosHeaders()
        headers.set('Content-Type','application/json')
        headers.set('x-neocom-check','check header')
        headers.set('authorization','Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1pbmR1c3RyeS5yZWFkX2NoYXJhY3Rlcl9taW5pbmcudjEiXSwianRpIjoiY2NhZWFmNjctNjM2ZS00ZDhhLThiNDQtZThlNzViM2ExMjE2Iiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzExNTMwMjExLCJpYXQiOjE3MTE1MjkwMTEsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.cVkBkCgLir-kzahfqxjhjNMmQoks1xbt0zthSvWt0Ynuv-rJhI25m4SGReMJSvnjyseh9bmyblJbXOYEJeF_zdbuyP-KRwshWj4hre-VZ4jJPf4Rl-QcdRxPJ-2hPk-w06ltuDCwWUmaCedQauXg9tHKnM8KGapZ64OENaEKbY4A4ilAS0Iukaz9HqqXEuW7rcGAKXvN27yguF2U_hoN3QzCzGcOB0sLyiW1lpjpOC-vO-1X9nc-RUJGK4bYxoVMtxUL1bBrbBmpp2Rb1A43bpsgNGWQQc-PSzasWw2sNX90oYFHKCGQ7_dRCsk2cT7xgcdaAGjVZe9yaIHTg4Ogzg')
        headers.set('Cache-Control','max-age: 600, public')
        const config:AxiosRequestConfig={
            headers:headers
        }

        return await lastValueFrom(this.httpService.get<V2MiningOperation[]>(request,config)
            .pipe(map((axiosResponse: AxiosResponse) => {
                console.log('esi data->' + axiosResponse.data)
                console.log('esi data->' + axiosResponse.data.instanceof)
                console.log('esi data->' + JSON.stringify(axiosResponse.data))

                return new TypedResponseTransformer<V2MiningOperation>(V2MiningOperation)
                    .setConverter(new MiningOperationConverter<IESIMiningOperation, V2MiningOperation>(this.halGenerator))
                    .transform(axiosResponse.data)
            }))
        )
    }
}
