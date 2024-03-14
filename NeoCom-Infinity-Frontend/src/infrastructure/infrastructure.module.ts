import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { ESISecureDataServiceAdapter } from './adapters/outbound/esi-data-service/ESISecureDataServiceAdapter';
import { V1MiningOperationsAdapterService } from './adapters/outbound/mining-operations/v1-mining-operations-adapter.service';

@NgModule({
    imports: [
        HttpClientModule,
    ],
    declarations: [
    ],
    providers: [
        // - SERVICES
        { provide: ESISecureDataServiceAdapter, useClass: ESISecureDataServiceAdapter }
        { provide: V1MiningOperationsAdapterService, useClass: V1MiningOperationsAdapterService }
    ]
})
export class InfrastructureModule { }
