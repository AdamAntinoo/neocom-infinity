import { Observable } from "rxjs";

export interface ESIUniversePort {
  apiEsiUniverseTypesData(typeId: number): Observable<UniverseType>
}
