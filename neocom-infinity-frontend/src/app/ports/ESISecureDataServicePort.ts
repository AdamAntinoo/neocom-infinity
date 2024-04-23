import { Observable } from "rxjs"

import { V1MiningOperationDto } from "neocom-domain"

export interface ESISecureDataServicePort{
    v1_apiEsiMiningOperationsData(pilotId: number): Observable<V1MiningOperationDto[]>
}
