import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { ESISecureDataServiceAdapter } from '@adapter/outbound/esi-data-service/ESISecureDataServiceAdapter';
import { V1MiningOperationsAdapterService } from '@adapter/outbound/mining-operations/V1.mining-operations.adapter';
import { UnsecuredProxy } from '@adapter/outbound/UnsecuredProxy/V1.UnsecuredProxy.adapter';
import { BackendFactory } from '@adapter/factory/BackendFactory';

@NgModule({
    imports: [
        HttpClientModule,
    ],
    declarations: [
    ],
    providers: [
        // - SERVICES
        ESISecureDataServiceAdapter,
        V1MiningOperationsAdapterService,
        UnsecuredProxy,
        BackendFactory
    ]
})
export class InfrastructureModule { }
