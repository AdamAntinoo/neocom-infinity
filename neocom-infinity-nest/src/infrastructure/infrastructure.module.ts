import { Module } from '@nestjs/common';
import { AssetsAdapter } from './adapter/outbound/assets.adapter.impl';

@Module({
  providers: [AssetsAdapter],
})
export class InfrastructureModule {}
