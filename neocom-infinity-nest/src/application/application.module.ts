import { Module } from '@nestjs/common';
import { CapsuleerMiningOperationsUseCase } from './use-cases/mining-operation/CapsuleerMiningOperationsUseCase';
import { HttpModule } from '@nestjs/axios';

@Module({
    imports: [
        HttpModule.register({
            timeout: 5000,
            maxRedirects: 5
        })
    ],
    providers: [CapsuleerMiningOperationsUseCase],
    exports: [CapsuleerMiningOperationsUseCase]
})
export class ApplicationModule { }
