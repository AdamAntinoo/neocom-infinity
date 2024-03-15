import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios'
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ScheduleModule, SchedulerRegistry } from '@nestjs/schedule';
import { StartMiningOperation } from '@App/use-cases/mining-operation/StartMiningOperation';
import { MiningOperationRepositoryMemory } from '@Infra/adapter/persistence/MiningOperationRepositoryMemory';
import { MincontrollerModule } from './infrastructure/infrastructure.module';

@Module({
    imports: [
        HttpModule.register({
            timeout: 5000,
            maxRedirects: 5
        }),
        ScheduleModule.forRoot(),
        MincontrollerModule
    ],
    controllers: [AppController],
    providers: [AppService, SchedulerRegistry, MiningOperationRepositoryMemory, StartMiningOperation],
})
export class AppModule { }
