import { HttpService } from "@nestjs/axios"
import { Injectable } from "@nestjs/common"
import { ESISecureDataServiceHALGeneratorAdapter } from "./esi.securedataservice.halgenerator.adapter"
import { ESIMiningSecureService } from "./esi.mining.secure.adapter"
import { V2MiningOperation } from "neocom-domain"
import { ESIDataServicesPort } from "application/ports/EsiDataServices.port"
import { IEsiMiningSecureService } from "application/ports/IEsiMiningSecureService.port"
import { ConfigService } from "@nestjs/config"

@Injectable()
export class ESISecureDataServicesAdapter implements ESIDataServicesPort {
    miningOperations: IEsiMiningSecureService<V2MiningOperation>

    constructor(
        private readonly httpService: HttpService,
        private readonly halGenerator: ESISecureDataServiceHALGeneratorAdapter,
        private readonly configuration: ConfigService
    ) {
        this.miningOperations = new ESIMiningSecureService(this.httpService, this.halGenerator, this.configuration)
    }
}
