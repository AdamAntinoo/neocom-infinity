import { Observable } from "rxjs";
import { AxiosResponse } from 'axios';

export interface ESIUniversePort {
  apiEsiUniverseTypesData(typeId: number): Observable<UniverseType>
}
