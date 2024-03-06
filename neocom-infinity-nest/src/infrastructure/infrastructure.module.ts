import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios'

import { ApplicationModule } from '@App/application.module';
import { DomainModule } from '@Domain/domain.module';
import { ESIAssetsDataAdapter } from './adapter/outbound/esi.assets.adapter';
import { MiningOperationRepositoryMemory } from './adapter/persistence/MiningOperationRepositoryMemory';

@Module({
  imports: [
    HttpModule.register({
      timeout: 5000,
      maxRedirects: 5
    }),
    ApplicationModule,
    DomainModule
  ],
  providers: [ESIAssetsDataAdapter,MiningOperationRepositoryMemory],
})
export class InfrastructureModule { }
