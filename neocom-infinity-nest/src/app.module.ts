import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios'
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ScheduleModule, SchedulerRegistry } from '@nestjs/schedule';
import { StartMiningOperation } from '@App/use-cases/mining-operation/StartMiningOperation';
import { MiningOperationRepositoryMemory } from '@Infra/adapter/persistence/MiningOperationRepositoryMemory';
import { EsiDataServicesPort } from '@App/ports/EsiDataServices.port';
import { EsiMiningAdapter } from '@Infra/adapter/outbound/esi.mining.adapter';
import { V1MiningOperationsController } from '@Infra/adapter/inbound/v1.miningoperations.controller';
import { CapsuleerMiningOperationsUseCase } from '@App/use-cases/mining-operation/CapsuleerMiningOperationsUseCase';

@Module({
    imports: [
        HttpModule.register({
            timeout: 5000,
            maxRedirects: 5
        }),
        ScheduleModule.forRoot(),
    ],
    controllers: [AppController, V1MiningOperationsController],
    providers: [
        AppService, SchedulerRegistry, MiningOperationRepositoryMemory, StartMiningOperation,
        { provide: EsiDataServicesPort, useClass: EsiMiningAdapter },
        { provide: CapsuleerMiningOperationsUseCase, useClass: CapsuleerMiningOperationsUseCase }
    ],
})
export class AppModule { }
