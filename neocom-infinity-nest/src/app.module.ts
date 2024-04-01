import { MiddlewareConsumer, Module, NestModule, RequestMethod } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios'
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ScheduleModule, SchedulerRegistry } from '@nestjs/schedule';
import { ESIDataServicesPort } from '@App/ports/ESIDataServices.port';
import { V1MiningOperationsController } from '@Infra/adapter/inbound/v1.miningoperations.controller';
import { CapsuleerMiningOperationsUseCase } from '@App/use-cases/mining-operation/CapsuleerMiningOperationsUseCase';
import { ESISecureDataServiceHALGeneratorAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservice.halgenerator.adapter';
import { ESISecureDataServicesAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/esi.securedataservices.adapter';
import { AuthenticationServicesPort } from '@App/ports/AuthenticationServices.port';
import { AuthenticationServicesAdapter } from '@Infra/adapter/outbound/AuthenticationServices/authenticationservices.adapter';
import { LoggerMiddleware } from '@Infra/config/LoggerInterceptor';
import { JwtModule } from '@nestjs/jwt';

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
    ],
    controllers: [AppController, V1MiningOperationsController],
    providers: [
        AppService, SchedulerRegistry,
        LoggerMiddleware,
        { provide: ESIDataServicesPort, useClass: ESISecureDataServicesAdapter },
        { provide: AuthenticationServicesPort, useClass: AuthenticationServicesAdapter },
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
