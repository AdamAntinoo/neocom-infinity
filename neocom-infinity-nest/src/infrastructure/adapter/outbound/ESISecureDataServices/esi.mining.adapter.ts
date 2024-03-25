import { EsiDataServicesPort } from "@App/ports/EsiDataServices.port"
import { IEsiSecureService } from "@App/ports/IEsiSecureService.port"
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation"
import { HttpService } from "@nestjs/axios"
import { Injectable, OnApplicationBootstrap } from "@nestjs/common"
import { lastValueFrom, map } from "rxjs"
import { MiningOperationConverter } from "./converter/MiningOperationConverter"
import { IESIMiningOperation } from "./domain/IESIMiningOperation.interface"
import { TypedResponseTransformer } from "@Inno/transformer/TypedResponseTransformer"
import { ESISecureDataServiceHALGeneratorAdapter } from "./esi.securedataservice.halgenerator.adapter"
import { AxiosResponse } from "axios"

@Injectable()
export class EsiMiningAdapter implements EsiDataServicesPort {
    miningOperations: IEsiSecureService<V2MiningOperation>

    constructor(private readonly httpService: HttpService,
        private halGenerator: ESISecureDataServiceHALGeneratorAdapter) {
        this.miningOperations = new EsiMiningSecureService(this.httpService, this.halGenerator)
    }
}

class EsiMiningSecureService implements IEsiSecureService<V2MiningOperation>{
    constructor(private readonly httpService: HttpService,
        private halGenerator: ESISecureDataServiceHALGeneratorAdapter) { }

    public async getMiningOperations(characterId: number): Promise<V2MiningOperation[]> {
        const request = 'http://localhost:5271' + '/characters/' + characterId + '/mining/'
        console.log('request->' + request)

        return await lastValueFrom(this.httpService.get<V2MiningOperation[]>(request)
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
