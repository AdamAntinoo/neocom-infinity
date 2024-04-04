import { Observable } from "rxjs"
import { ESIMiningOperation } from "@infra/adapters/outbound/esi-data-service/domain/ESIMiningOperation"

export interface ESISecureDataServicePort{
    v1_apiEsiMiningOperationsData(pilotId: number): Observable<ESIMiningOperation[]>
}
