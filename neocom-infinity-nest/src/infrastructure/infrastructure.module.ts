import { Module } from '@nestjs/common';
import { MincontrollerService } from './mincontroller/mincontroller.service';
import { MincontrollerController } from './mincontroller/mincontroller.controller';
import { V1MiningOperationsController } from './adapter/inbound/v1.miningoperations.controller';

@Module({
    imports:[],
    controllers: [V1MiningOperationsController],
})
export class MincontrollerModule { }
