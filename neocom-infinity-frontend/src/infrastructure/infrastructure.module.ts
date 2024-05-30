import { NgModule } from '@angular/core'
import { HttpClientModule } from '@angular/common/http'

import { V1SecuredProxyAdapter } from '@adapter/outbound/SecuredProxy/v1.SecuredProxy.adapter'

import { ESISecureDataServiceAdapter } from '@adapter/outbound/esi-data-service/ESISecureDataServiceAdapter'
import { V1MiningOperationsAdapterService } from '@adapter/outbound/mining-operations/V1.mining-operations.adapter'
import { UnsecuredProxy } from '@adapter/outbound/UnsecuredProxy/V1.UnsecuredProxy.adapter'
import { BackendFactory } from '@adapter/factory/BackendFactory'

@NgModule({
	imports: [HttpClientModule],
	declarations: [],
	providers: [
		// - SERVICES
		{ provide: SecuredServicesPort, useClass: V1SecuredProxyAdapter },

		ESISecureDataServiceAdapter,
		V1MiningOperationsAdapterService,
		UnsecuredProxy,
		BackendFactory,
	],
})
export class InfrastructureModule {}
