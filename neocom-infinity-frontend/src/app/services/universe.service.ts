// - CORE
import { Injectable } from '@angular/core'
import { Observable, lastValueFrom } from 'rxjs'
import { map } from 'rxjs/operators'
import { environment } from '@env/environment'
// - SERVICES
import { UniverseHttpWrapper } from './universe.httpwrapper'
import { HALResolver } from './HALResolver.service'
// - DOMAIN
import { EsiType } from '@domain/esi/EsiType.esi'
import { PlatformConstants } from '@env/PlatformConstants'
import { EsiTypeDto } from '@domain/dto/EsiType.dto'
import { NeoComException } from '@innovative/domain/NeoComException'
import { NeoCom } from '@domain/NeoCom.domain'

@Injectable({
    providedIn: 'root'
})
export class UniverseService {
    protected UNIVERSEV1: string

    constructor(
        protected httpUniverseService: UniverseHttpWrapper,
        protected halResolver: HALResolver) {
        this.UNIVERSEV1 = '/api/v1' + '/universe'
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
    public apiv1_GetUniverseLink<T>(link: string, targetType: T): Promise<T> {
        if (undefined != link) {
            console.log(">[UniverseService.apiv1_GetUniverseLink]> link: " + link)
            return lastValueFrom(this.httpUniverseService.wrapHttpGETCallNoHeaders(link) as Observable<T>)
        }
        else throw new NeoComException()
            .withTitle('Invalid HAL link.')
            .withMessage('Invalid HAL link for type ' + JSON.stringify(targetType))
    }
}
