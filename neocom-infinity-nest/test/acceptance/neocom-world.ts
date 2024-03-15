import { World, setWorldConstructor } from '@cucumber/cucumber'
import { SchedulerRegistry } from '@nestjs/schedule'

import { ApplicationModule } from '@App/application.module'
import { InfrastructureModule } from '@Infra/infrastructure.module'

import { DeltaCalculator } from '../application/helpers/delta-calculator'
import { PilotCard } from 'src/application/domain/pilot.card'
import { AssetEsi } from '@Domain/dto/ESIAsset.esi'
import { ESIAssetsDataAdapter } from '@Infra/adapter/outbound/esi.assets.adapter'
import { HttpSecureServiceInterface } from '@Infra/network/http.secure.service.interface'
import { MiningOperationRepositoryMemory } from '@Infra/adapter/persistence/MiningOperationRepositoryMemory'
import { MiningOperation } from '@Domain/entities/V1.MiningOperation'
import { StartMiningOperation } from '@App/use-cases/mining-operation/StartMiningOperation'
import { NestFactory } from '@nestjs/core'

export class NeoComWorld extends World {
    // - Delta Calculator
    public deltaCalculator: DeltaCalculator
    public assetlist: AssetEsi[]
    public initialList: AssetEsi[]
    public secondList: AssetEsi[]
    public output: AssetEsi[]
    public pilotCard: PilotCard

    public httpSecureService: HttpSecureServiceInterface
    public assetAdapter: ESIAssetsDataAdapter

    public characterAssetsResponse: Promise<AssetEsi[]>
    // - Esi Assers Adapter
    // - Mining Operations
    public providerSchedulerRegistry: SchedulerRegistry
    public providerStartMiningOperationUseCase: StartMiningOperation
    public providerMiningOperationRepository: MiningOperationRepositoryMemory
    public miningOperation: MiningOperation
    private appModule: any
    private infraModule: any

    constructor(options, startMiningOperationUseCase: StartMiningOperation, schedulerRegistry: SchedulerRegistry, miningOperationRepository: MiningOperationRepositoryMemory) {
        super(options)
        this.providerStartMiningOperationUseCase = startMiningOperationUseCase
        this.providerSchedulerRegistry = schedulerRegistry
        this.providerMiningOperationRepository = miningOperationRepository
        this.attach(JSON.stringify(schedulerRegistry))
        this.attach(JSON.stringify(startMiningOperationUseCase))
        this.init()
    }
    /**
     * Create the dependencies that are required by the scenario
     */
    public async init() {
        this.appModule = await NestFactory.create(ApplicationModule)
        this.infraModule = await NestFactory.create(InfrastructureModule)
        // Load services and adapters
        this.providerStartMiningOperationUseCase = this.appModule.get()
        this.providerMiningOperationRepository = this.infraModule.get(MiningOperationRepositoryMemory)
        this.attach(JSON.stringify(this.providerStartMiningOperationUseCase))
        this.attach(JSON.stringify(this.providerMiningOperationRepository))
    }
    public async close() { }
}
setWorldConstructor(NeoComWorld)
