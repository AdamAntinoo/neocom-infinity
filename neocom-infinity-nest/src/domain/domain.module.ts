import { Module } from '@nestjs/common';
import { AssetEsi } from './dto/ESIAsset.esi';

@Module({
  exports: [AssetEsi],
  providers: [],
})
export class DomainModule {}
