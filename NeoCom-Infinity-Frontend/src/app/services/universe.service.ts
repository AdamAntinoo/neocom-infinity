// - CORE
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { map } from 'rxjs/operators'
import { environment } from '@env/environment'
// - SERVICES
import { UniverseHttpWrapper } from './universe.httpwrapper'
// - DOMAIN
import { EsiType } from '@domain/esi/EsiType.esi'
import { platformConstants } from '@env/platform-constants'

@Injectable({
    providedIn: 'root'
})
export class UniverseService {
    protected UNIVERSEV1: string

    constructor(protected httpUniverseService: UniverseHttpWrapper) {
        this.UNIVERSEV1 = environment.serverName + '/api/v1' + '/universe'
    }

    // - U N I V E R S E   A P I
    public apiv1_GetUniverseType(typeId: number): Observable<EsiType> {
        console.log(">[UniverseService.apiv1_GetUniverseType]> typeId: " + typeId)
        const request = platformConstants.UNIVERSE_V1 + "/types/" + typeId
        return this.httpUniverseService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                return new EsiType(data)
            }))
    }
}
