import { AuthenticationServicesPort } from "@App/ports/AuthenticationServices.port"
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation"
import { V1MiningOperationsController } from "@Infra/adapter/inbound/v1.miningoperations.controller"
import { AuthenticationServicesAdapter } from "@Infra/adapter/outbound/AuthenticationServices/authenticationservices.adapter"
import { World, setWorldConstructor } from "@cucumber/cucumber"
import { HttpService } from "@nestjs/axios"
import { NestFactory } from "@nestjs/core"
import { AppModule } from "app.module"
import { AxiosResponse } from "axios"

export class NIN01World extends World {
    // - Common
    protected appModule: any
    public httpService: HttpService
    // NIN01
    public characterId: number
    public controller: V1MiningOperationsController
    public miningActionsResponse: V2MiningOperation[]
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
        console.log('httpService->' + this.httpService)
        this.controller = this.appModule.get(V1MiningOperationsController)
        this.authenticationService = this.appModule.get(AuthenticationServicesPort)
    }
}
