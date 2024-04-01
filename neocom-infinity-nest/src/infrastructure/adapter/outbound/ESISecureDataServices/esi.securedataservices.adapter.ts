import { ESIDataServicesPort } from "@App/ports/ESIDataServices.port"
import { IEsiMiningSecureService } from "@App/ports/IEsiMiningSecureService.port"
import { HttpService } from "@nestjs/axios"
import { Injectable } from "@nestjs/common"
import { ESISecureDataServiceHALGeneratorAdapter } from "./esi.securedataservice.halgenerator.adapter"
import { ESIMiningSecureService } from "./esi.mining.adapter"
import { V2MiningOperation } from "neocom-domain"

@Injectable()
export class ESISecureDataServicesAdapter implements ESIDataServicesPort {
    miningOperations: IEsiMiningSecureService<V2MiningOperation>

    constructor(private readonly httpService: HttpService,
        private halGenerator: ESISecureDataServiceHALGeneratorAdapter) {
        this.miningOperations = new ESIMiningSecureService(this.httpService, this.halGenerator)
    }
}
