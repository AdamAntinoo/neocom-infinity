import { V1BlueprintDto, V1MiningOperationDto } from 'neocom-domain'
import { V1ProcessedBlueprintDto } from 'neocom-domain/v1.ProcessedBlueprint.dto'
import { Observable } from 'rxjs'

export interface SecuredServicesPort {
	v3_apiNeocomMiningOperationsData(pilotId: number): Observable<V1MiningOperationDto[]>
	v3_apiNeocomBlueprintsData(token: string): Observable<V1BlueprintDto[]>
	v3_apiNeocomProcessedBlueprintsData(toke: string, pilotId: number): Observable<V1ProcessedBlueprintDto[]>
}
