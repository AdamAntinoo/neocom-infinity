import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation"
import { V1MiningOperationsController } from "@Infra/adapter/inbound/v1.miningoperations.controller"
import { World, setWorldConstructor } from "@cucumber/cucumber"
import { NestFactory } from "@nestjs/core"
import { AppModule } from "app.module"

export class NIN01World extends World {
    private appModule: any
    public characterId: number
    public controller: V1MiningOperationsController
    public miningActionsResponse:V2MiningOperation[]

    constructor(options) {
        super(options)
        console.log('world constructor')
     }
    /**
    * Create the dependencies that are required by the scenario
    */
    public async init() {
        console.log('workd init')
        this.appModule = await NestFactory.create(AppModule)
        this.controller = this.appModule.get(V1MiningOperationsController)
    }
}
