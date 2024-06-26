import { MiddlewareConsumer, Module, NestModule, RequestMethod } from '@nestjs/common'
import { HttpModule } from '@nestjs/axios'
import { ScheduleModule, SchedulerRegistry } from '@nestjs/schedule'
import { LoggerMiddleware } from '@Infra/config/LoggerInterceptor'
import { JwtModule } from '@nestjs/jwt'
import { CapsuleerMiningOperationsUseCase } from '@App/use-cases/esisecure/CapsuleerMiningOperationsUseCase'
import { ConfigModule, ConfigService } from '@nestjs/config'
import { GetTypeInformationUseCase } from 'application/use-cases/esi-universe/GetTypeInformation.usecase'
import { ESIDataUniverseServicesPort } from 'application/ports/ESIDataUniverseServices.port'
import { ESIDataUniverseAdapter } from '@Infra/adapter/outbound/ESIDataUniverseServices/ESIData.universe.adapter'
import { GetMarketDataUseCase } from 'application/use-cases/esi-universe/GetMarketData.usecase'
import { GetSpaceLocationUseCase } from 'application/use-cases/esi-universe/GetSpaceLocation.usecase'
import { V1ESISecureDataAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/V1.EsiSecureData.adapter'

import { ESIDataServicesPort } from '@App/ports/ESIDataServices.port'
import { CapsuleerAssetsUseCase } from '@App/use-cases/esisecure/CapsuleerAssets.usecase'
import { CapsuleerBlueprintsUseCase } from '@App/use-cases/esisecure/CapsuleerBlueprints.usecase'
import { V1CharacterController } from '@Infra/adapter/inbound/esisecureapi/V1.Character.controller'
import { V1EsiUniverseController } from '@Infra/adapter/inbound/esiuniverse/v1.esiuniverse.controller'
import { V1IndustryController } from '@Infra/adapter/inbound/esisecureapi/V1.Industry.controller'

const ENV = process.env.NODE_ENV

@Module({
	imports: [
		HttpModule.register({
			timeout: 90000, // Add time for debugging.
			maxRedirects: 5,
			headers: { 'X-Requested-With': 'XMLHttpRequest' },
			withCredentials: true,
		}),
		ScheduleModule.forRoot(),
		JwtModule.register({ secret: 'hard!to-guess_secret' }),
		ConfigModule.forRoot({
			isGlobal: true,
			envFilePath: [!ENV ? '.env' : `.env.${ENV}`, '.env.version'],
		}),
	],
	controllers: [V1CharacterController, V1IndustryController, V1EsiUniverseController],
	providers: [
		SchedulerRegistry,
		LoggerMiddleware,
		ConfigService,
		// PORTS
		{ provide: ESIDataServicesPort, useClass: V1ESISecureDataAdapter },
		{ provide: ESIDataUniverseServicesPort, useClass: ESIDataUniverseAdapter },
		{ provide: CapsuleerAssetsUseCase, useClass: CapsuleerAssetsUseCase },
		{ provide: CapsuleerBlueprintsUseCase, useClass: CapsuleerBlueprintsUseCase },
		{ provide: CapsuleerMiningOperationsUseCase, useClass: CapsuleerMiningOperationsUseCase },
		GetTypeInformationUseCase,
		GetMarketDataUseCase,
		GetSpaceLocationUseCase,
		{ provide: ESIDataUniverseServicesPort, useClass: ESIDataUniverseAdapter },
	],
})
export class AppModule implements NestModule {
	configure(consumer: MiddlewareConsumer) {
		consumer.apply(LoggerMiddleware).forRoutes({ path: '*', method: RequestMethod.ALL })
	}
}
