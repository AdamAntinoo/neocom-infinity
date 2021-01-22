// - CORE
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { map } from 'rxjs/operators'
import { environment } from '@env/environment'
// - SERVICES
import { UniverseHttpWrapper } from './universe.httpwrapper'
// - DOMAIN
import { UniverseService } from './universe.service'
import { PublicCorporationV1 } from '@domain/corporation/PublicCorporationV1.domain'
import { PublicPilotV1 } from '@domain/character/PublicPilotV1.domain'

@Injectable({
    providedIn: 'root'
})
export class PublicService extends UniverseService {
    protected PUBLICV1: string

    constructor(protected httpUniverseService: UniverseHttpWrapper) {
        super(httpUniverseService)
        this.PUBLICV1 = environment.serverName + environment.apiVersion1 + '/public'
    }

    // - U N I V E R S E   A P I
    public apiv1_GetPublicPilotData(pilotId: number): Observable<PublicPilotV1> {
        const request = this.PUBLICV1 + '/pilots/' + pilotId
        return this.httpUniverseService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                return new PublicPilotV1(data)
            }))
    }
    public apiv1_GetPublicCorporationData(coporationId: number): Observable<PublicCorporationV1> {
        // console.log(">[UniverseService.apiv1_GetPublicCorporationData]> coporationId: " + coporationId)
        const request = this.PUBLICV1 + '/corporations/' + coporationId
        return this.httpUniverseService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                return new PublicCorporationV1(data)
            }))
    }
}
