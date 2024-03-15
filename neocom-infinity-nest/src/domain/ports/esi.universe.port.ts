import { Observable } from "rxjs";
import { AxiosResponse } from 'axios';
import { UniverseType } from "@Domain/dto/UniverseType.esi";

export interface ESIUniversePort {
    apiEsiUniverseTypesData(typeId: number): Observable<UniverseType>
}
