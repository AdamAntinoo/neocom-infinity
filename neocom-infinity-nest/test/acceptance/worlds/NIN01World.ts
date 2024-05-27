import { V1CharacterController } from "@Infra/adapter/inbound/character/V1.Character.controller"
import { V1MiningOperationsController } from "@Infra/adapter/inbound/esisecureapi/v1.miningoperations.controller"
import { V1EsiUniverseController } from "@Infra/adapter/inbound/esiuniverse/v1.esiuniverse.controller"
import { AuthenticationServicesAdapter } from "@Infra/adapter/outbound/AuthenticationServices/authenticationservices.adapter"
import { V1ESISecureDataAdapter } from "@Infra/adapter/outbound/ESISecureDataServices/V1.EsiSecureData.adapter"
import { World } from "@cucumber/cucumber"
import { HttpService } from "@nestjs/axios"
import { ConfigService } from "@nestjs/config"
import { NestFactory } from "@nestjs/core"
import { AppModule } from "app.module"
import { GetCharactersCharacterIdMining200Ok } from "application/domain/esi-model/models"
import { AxiosResponse } from "axios"
import { V1AssetDto, V1EsiTypeDto, V1MarketDataDto, V1MiningOperationDto } from "neocom-domain"

export class NIN01World extends World {
    // - Common
    protected appModule: any
    public httpService: HttpService
    public responseResultCode:string
    private configuration: ConfigService
    public esiSecureDataService : V1ESISecureDataAdapter
    // NIN01
    public characterId: number
    public miningOperationsController: V1MiningOperationsController
    public miningActionsResponse: GetCharactersCharacterIdMining200Ok[]
    public miningOperationsResponse: V1MiningOperationDto[]
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
    // NIN28
    public characterController : V1CharacterController
    public esiAssetsResponse: V1AssetDto[]


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
        this.characterController= this.appModule.get(V1CharacterController)
        this.esiSecureDataService = this.appModule.get(V1ESISecureDataAdapter)
    }
}
