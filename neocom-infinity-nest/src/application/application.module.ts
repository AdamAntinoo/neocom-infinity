import { Module } from '@nestjs/common';
import { CapsuleerMiningOperationsUseCase } from './use-cases/mining-operation/CapsuleerMiningOperationsUseCase';

@Module({
    providers: [CapsuleerMiningOperationsUseCase]
})
export class ApplicationModule { }
