import { V1BlueprintDto, V1MiningOperationDto } from 'neocom-domain'
import { Observable } from 'rxjs'

export interface SecuredServicesPort {
	v3_apiNeocomMiningOperationsData(pilotId: number): Observable<V1MiningOperationDto[]>
	v3_apiNeocomBlueprintsData(token: string): Observable<V1BlueprintDto[]>
}
