import { MiddlewareConsumer, Module, NestModule, RequestMethod } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios'
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ScheduleModule, SchedulerRegistry } from '@nestjs/schedule';
import { V1MiningOperationsController } from '@Infra/adapter/inbound/esisecureapi/v1.miningoperations.controller';
import { ESISecureDataServiceHALGeneratorAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservice.halgenerator.adapter';
import { ESISecureDataServicesAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservices.adapter';
import { AuthenticationServicesAdapter } from '@Infra/adapter/outbound/AuthenticationServices/authenticationservices.adapter';
import { LoggerMiddleware } from '@Infra/config/LoggerInterceptor';
import { JwtModule } from '@nestjs/jwt';
import { AuthenticationServicesPort } from 'application/ports/AuthenticationServices.port';
import { ESIDataServicesPort } from 'application/ports/EsiDataServices.port';
import { CapsuleerMiningOperationsUseCase } from 'application/use-cases/mining-operation/CapsuleerMiningOperationsUseCase';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { ESIMiningSecureService } from '@Infra/adapter/outbound/ESISecureDataServices/esi.mining.secure.adapter';
import { IEsiMiningSecureService } from 'application/ports/IEsiMiningSecureService.port';

const ENV = process.env.NODE_ENV

@Module({
    imports: [
        HttpModule.register({
            timeout: 90000, // Add time for debugging.
            maxRedirects: 5,
            headers: { 'X-Requested-With': 'XMLHttpRequest' },
            withCredentials: true
        }),
        ScheduleModule.forRoot(),
        JwtModule.register({ secret: 'hard!to-guess_secret' }),
        ConfigModule.forRoot({
            isGlobal: true,
            envFilePath: !ENV ? '.env' : `.env.${ENV}`
        })
    ],
    controllers: [AppController, V1MiningOperationsController],
    providers: [
        AppService, SchedulerRegistry,
        LoggerMiddleware,
        ConfigService,
        { provide: ESIDataServicesPort, useClass: ESISecureDataServicesAdapter },
        { provide: IEsiMiningSecureService, useClass: ESIMiningSecureService },
        // { provide: AuthenticationServicesPort, useClass: AuthenticationServicesAdapter },
        { provide: CapsuleerMiningOperationsUseCase, useClass: CapsuleerMiningOperationsUseCase },
        { provide: ESISecureDataServiceHALGeneratorAdapter, useClass: ESISecureDataServiceHALGeneratorAdapter }
    ],
})
export class AppModule implements NestModule {
    configure(consumer: MiddlewareConsumer) {
        consumer
            .apply(LoggerMiddleware)
            .forRoutes({ path: '*', method: RequestMethod.ALL });
    }
}
