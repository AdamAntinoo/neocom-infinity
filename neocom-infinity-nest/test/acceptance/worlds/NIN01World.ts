import { V1MiningOperationsController } from "@Infra/adapter/inbound/esisecureapi/v1.miningoperations.controller"
import { AuthenticationServicesAdapter } from "@Infra/adapter/outbound/AuthenticationServices/authenticationservices.adapter"
import { ESIMiningSecureService } from "@Infra/adapter/outbound/ESISecureDataServices/esi.mining.secure.adapter"
import { ESISecureDataServiceHALGeneratorAdapter } from "@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservice.halgenerator.adapter"
import { World, setWorldConstructor } from "@cucumber/cucumber"
import { HttpService } from "@nestjs/axios"
import { ConfigService } from "@nestjs/config"
import { NestFactory } from "@nestjs/core"
import { AppModule } from "app.module"
import { GetCharactersCharacterIdAgentsResearch200Ok } from "application/domain/esi-model/getCharactersCharacterIdAgentsResearch200Ok"
import { GetCharactersCharacterIdMining200Ok } from "application/domain/esi-model/models"
import { IEsiMiningSecureService } from "application/ports/IEsiMiningSecureService.port"
import { AxiosResponse } from "axios"
import { V2MiningOperation } from "neocom-domain"
import { V1MiningOperationDto } from "neocom-domain/dto/V1MiningOperationDto.dto"

export class NIN01World extends World {
    // - Common
    protected appModule: any
    public httpService: HttpService
    private halGenerator: ESISecureDataServiceHALGeneratorAdapter
    private configuration: ConfigService
    // NIN01
    public characterId: number
    public controller: V1MiningOperationsController
    public miningActionsResponse: GetCharactersCharacterIdMining200Ok[]
    public miningOperationsResponse: V1MiningOperationDto[]
    public esiMiningSecureService: ESIMiningSecureService
    public targetMiningOperation: V1MiningOperationDto
    // NIN02
    public authenticationService: AuthenticationServicesAdapter
    public token: any
    public response: AxiosResponse

    constructor(options) {
        super(options)
        console.log('NIN01World world constructor')
    }
    /**
    * Create the dependencies that are required by the scenario
    */
    public async init() {
        console.log('NIN01World init')
        this.appModule = await NestFactory.create(AppModule)
        console.log('module->' + this.appModule)
        this.httpService = this.appModule.get(HttpService)
        this.halGenerator = this.appModule.get(ESISecureDataServiceHALGeneratorAdapter)
        this.configuration = this.appModule.get(ConfigService)

        this.controller = this.appModule.get(V1MiningOperationsController)
        this.esiMiningSecureService = new ESIMiningSecureService(this.httpService, this.configuration)
    }
}
