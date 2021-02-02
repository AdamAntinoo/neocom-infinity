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
import { PublicPilotV1Dto } from '@domain/dto/PublicPilotV1.dto'
import { HALResolver } from './HALResolver.service'
import { LoyaltyCorporationV1 } from '@app/modules/loyalty/domain/LoyaltyCorporationV1.domain'
import { LoyaltyOfferV1 } from '@app/modules/loyalty/domain/LoyaltyOfferV1.domain'
import { LoyaltyOfferDto } from '@app/modules/loyalty/dto/LoyaltyOfferDto.dto'

@Injectable({
    providedIn: 'root'
})
export class PublicService extends UniverseService {
    protected PUBLICV1: string

    constructor(
        protected httpUniverseService: UniverseHttpWrapper,
        protected halResolver: HALResolver) {
        super(httpUniverseService)
        this.PUBLICV1 = environment.serverName + '/api/v1' + '/public'
    }

    // - U N I V E R S E   A P I
    public apiv1_GetPublicPilotData(pilotId: number): Observable<PublicPilotV1> {
        const request = this.PUBLICV1 + '/pilots/' + pilotId
        return this.httpUniverseService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                return new PublicPilotV1Dto(data).transform(this.halResolver)
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
    public apiv1_GetLoyaltyOffers(corporationId: number): Observable<LoyaltyOfferV1[]> {
        const request = this.PUBLICV1 + '/loyalty/corporations/' + corporationId
        return this.httpUniverseService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                const offers: LoyaltyOfferV1[] = []
                for (const offer of data) {
                    offers.push(new LoyaltyOfferDto(offer).transform(this, this.halResolver))
                }
                return offers
            }))
    }
}
