// - CORE
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { map } from 'rxjs/operators'
import { environment } from '@env/environment'
// - SERVICES
import { UniverseHttpWrapper } from './universe.httpwrapper'
import { HALResolver } from './HALResolver.service'
// - DOMAIN
import { EsiType } from '@domain/esi/EsiType.esi'
import { PlatformConstants } from '@env/PlatformConstants'
import { EsiTypeDto } from '@domain/dto/EsiType.dto'

@Injectable({
    providedIn: 'root'
})
export class UniverseService {
    protected UNIVERSEV1: string

    constructor(
        protected httpUniverseService: UniverseHttpWrapper,
        protected halResolver: HALResolver) {
        this.UNIVERSEV1 = environment.serverName + '/api/v1' + '/universe'
    }

    // - U N I V E R S E   A P I
    public apiv1_GetUniverseType(typeId: number): Observable<EsiType> {
        console.log(">[UniverseService.apiv1_GetUniverseType]> typeId: " + typeId)
        const request = PlatformConstants.UNIVERSE_V1 + "/types/" + typeId
        return this.httpUniverseService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                return new EsiTypeDto(data).transform(this.halResolver)
            }))
    }
}
