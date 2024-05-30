import { V1MiningOperationDto, V1BlueprintDto } from 'neocom-domain'
import { Observable } from 'rxjs'
import { SecuredServicesPort } from 'src/infrastructure/ports/SecuredServices.port'
import { ApiMiningOperationsTypedRequest } from './typedrequests/ApiMiningOperations.typedrequest'
import { ApiBlueprintsTypedRequest } from './typedrequests/ApiBlueprints.typedrequest'
import { HttpClient } from '@angular/common/http'

/**
 * This adapter will collect the backend data transformation endpoints that encapsulate data provided by ESI under the authorization token.
 * The adapter will follow the best http practices like request encapsulation, optional logging and automatic response transformation.
 *
 * Exceptions will be launched when the request is not successful and should be handled by the UI on a generic way using the Toaster.
 */
export class V1SecuredProxyAdapter implements SecuredServicesPort {
	constructor(private readonly httpService: HttpClient) {}

	// - M I N I N G    O P E R A T I O N S
	v3_apiNeocomMiningOperationsData(pilotId: number): Observable<V1MiningOperationDto[]> {
		const miningOperationsTypedRequest = new ApiMiningOperationsTypedRequest({ token: 'token' }).build()
		return this.httpService.get<V1MiningOperationDto[]>(miningOperationsTypedRequest.request, {
			headers: miningOperationsTypedRequest.options.headers,
			observe: 'body',
		})
	}
	// - B L U P R I N T S
	v3_apiNeocomBlueprintsData(pilotId: number): Observable<V1BlueprintDto[]> {
		const apiTypedRequest: ApiBlueprintsTypedRequest = new ApiBlueprintsTypedRequest({ token: 'token' }).build()
		return this.httpService.get<V1BlueprintDto[]>(apiTypedRequest.request, {
			headers: apiTypedRequest.options.headers,
			observe: 'body',
		})
	}
}
