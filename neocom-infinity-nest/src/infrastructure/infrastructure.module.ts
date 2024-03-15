import { Module } from '@nestjs/common';
import { MincontrollerService } from './mincontroller/mincontroller.service';
import { MincontrollerController } from './mincontroller/mincontroller.controller';
import { V1MiningOperationsController } from './adapter/inbound/v1.miningoperations.controller';
import { ApplicationModule } from '@App/application.module';
import { HttpModule } from '@nestjs/axios';
import { EsiMiningAdapter } from './adapter/outbound/esi.mining.adapter';

@Module({
    imports: [HttpModule, ApplicationModule],
    controllers: [V1MiningOperationsController],
    providers: [EsiMiningAdapter],
    exports: [EsiMiningAdapter]
})
export class InfrastructureModule { }
