import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios'
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ScheduleModule, SchedulerRegistry } from '@nestjs/schedule';
import { ESIDataServicesPort } from '@App/ports/ESIDataServices.port';
import { V1MiningOperationsController } from '@Infra/adapter/inbound/v1.miningoperations.controller';
import { CapsuleerMiningOperationsUseCase } from '@App/use-cases/mining-operation/CapsuleerMiningOperationsUseCase';
import { ESISecureDataServiceHALGeneratorAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservice.halgenerator.adapter';
import { ESISecureDataServicesAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservices.adapter';

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
        AppService, SchedulerRegistry,
        { provide: ESIDataServicesPort, useClass: ESISecureDataServicesAdapter },
        { provide: CapsuleerMiningOperationsUseCase, useClass: CapsuleerMiningOperationsUseCase },
        { provide: ESISecureDataServiceHALGeneratorAdapter, useClass: ESISecureDataServiceHALGeneratorAdapter }
    ],
})
export class AppModule { }
