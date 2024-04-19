import { V1MiningOperationsController } from "@Infra/adapter/inbound/esisecureapi/v1.miningoperations.controller"
import { V1EsiUniverseController } from "@Infra/adapter/inbound/esiuniverse/v1.esiuniverse.controller"
import { AuthenticationServicesAdapter } from "@Infra/adapter/outbound/AuthenticationServices/authenticationservices.adapter"
import { ESIMiningSecureService } from "@Infra/adapter/outbound/ESISecureDataServices/esi.mining.secure.adapter"
import { World } from "@cucumber/cucumber"
import { HttpService } from "@nestjs/axios"
import { ConfigService } from "@nestjs/config"
import { NestFactory } from "@nestjs/core"
import { AppModule } from "app.module"
import { GetCharactersCharacterIdMining200Ok } from "application/domain/esi-model/models"
import { AxiosResponse } from "axios"
import { V1EsiTypeDto, V1MarketDataDto, V1MiningOperationDto } from "neocom-domain"

export class NIN01World extends World {
    // - Common
    protected appModule: any
    public httpService: HttpService
    private configuration: ConfigService
    // NIN01
    public characterId: number
    public miningOperationsController: V1MiningOperationsController
    public miningActionsResponse: GetCharactersCharacterIdMining200Ok[]
    public miningOperationsResponse: V1MiningOperationDto[]
    public esiMiningSecureService: ESIMiningSecureService
    public targetMiningOperation: V1MiningOperationDto
    // NIN02
    public authenticationService: AuthenticationServicesAdapter
    public token: any
    public response: AxiosResponse
    // NIN03
    public esiTypeId: number
    public universeController: V1EsiUniverseController
    public esiTypeInformationResponse: V1EsiTypeDto
    public region: number
    public esiMarketDataResponse: V1MarketDataDto

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
        this.httpService = this.appModule.get(HttpService)
        this.configuration = this.appModule.get(ConfigService)

        this.miningOperationsController = this.appModule.get(V1MiningOperationsController)
        this.universeController = this.appModule.get(V1EsiUniverseController)
        this.esiMiningSecureService = new ESIMiningSecureService(this.httpService, this.configuration)
    }
}
