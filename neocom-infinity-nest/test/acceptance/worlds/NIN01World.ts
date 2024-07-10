import { ESIDataServicesPort } from '@App/ports/v1ESIDataServices.port'
import { V1CharacterController } from '@Infra/adapter/inbound/esisecureapi/V1.Character.controller'
import { V1IndustryController } from '@Infra/adapter/inbound/esisecureapi/V1.Industry.controller'
import { V1EsiUniverseController } from '@Infra/adapter/inbound/esiuniverse/v1.esiuniverse.controller'
import { AuthenticationServicesAdapter } from '@Infra/adapter/outbound/AuthenticationServices/authenticationservices.adapter'
import { World } from '@cucumber/cucumber'
import { HttpService } from '@nestjs/axios'
import { ConfigService } from '@nestjs/config'
import { NestFactory } from '@nestjs/core'
import { AppModule } from 'app.module'
import { AxiosResponse } from 'axios'
import { GetCharactersCharacterIdMining200Ok, V1EsiTypeDto, V1MarketDataDto, V1MiningOperationDto, V2ProcessedBlueprintDto } from 'neocom-domain'
import { V1AssetDto } from 'neocom-domain'
import { V1BlueprintDto } from 'neocom-domain'

export class NIN01World extends World {
	// - Common
	protected appModule: any
	public httpService: HttpService
	public responseResultCode: string
	public configuration: ConfigService
	public esiSecureDataService: ESIDataServicesPort
	// NIN01
	public characterId: number
	public miningOperationsController: V1IndustryController
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
	public characterController: V1CharacterController
	public esiAssetsResponse: V1AssetDto[]
	public esiBlueprintsResponse: V1BlueprintDto[]
	// NIN29
	public industryController: V1IndustryController
	public processedBlueprintsResponse: V2ProcessedBlueprintDto[]

	constructor(options) {
		super(options)
		console.log('NIN01World world constructor')
	}
	/** Create the dependencies that are required by the scenario */
	public async init() {
		console.log('NIN01World init')
		this.appModule = await NestFactory.create(AppModule)
		this.httpService = this.appModule.get(HttpService)
		this.configuration = this.appModule.get(ConfigService)

		this.miningOperationsController = this.appModule.get(V1IndustryController)
		this.universeController = this.appModule.get(V1EsiUniverseController)
		this.characterController = this.appModule.get(V1CharacterController)
		this.esiSecureDataService = this.appModule.get(ESIDataServicesPort)
		this.industryController = this.appModule.get(V1IndustryController)
	}
}
