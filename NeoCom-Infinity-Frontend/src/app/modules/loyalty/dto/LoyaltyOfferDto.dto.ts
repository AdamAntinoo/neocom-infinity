// - SERVICES
import { HALResolver } from "@app/services/HALResolver.service";
import { UniverseService } from "@app/services/universe.service";
// - DOMAIN
import { EsiType } from "@domain/esi/EsiType.esi"
import { HALLink } from "@domain/hal/HALLink.hal";
import { MarketOrderDto } from "@domain/industry/dto/MarketOrderDto.dto";
import { isThisHour } from "date-fns/esm";
import { LoyaltyOfferV1 } from "../domain/LoyaltyOfferV1.domain";

export class LoyaltyOfferDto {
    public typeId: number
    public type: HALLink<EsiType>
    public marketData: HALLink<MarketOrderDto>

    constructor(values: Object = {}) {
        Object.assign(this, values);
        if (this.type)
            this.type = new HALLink<EsiType>(EsiType).setContents(this.type)
        if (this.marketData)
            this.marketData = new HALLink<MarketOrderDto>(MarketOrderDto).setContents(this.marketData)
    }

    public transform(halResolver: HALResolver): LoyaltyOfferV1 {
        const loyaltyOffer: LoyaltyOfferV1 = new LoyaltyOfferV1(this)
        if (this.typeId) {
            halResolver.resolve<EsiType>(this.type)
                .subscribe((universeType: EsiType) => {
                    console.log('-[LoyaltyOfferDto]>completing EsiType')
                    loyaltyOffer.type = universeType
                })
        }
        if (this.marketData) {
            halResolver.resolve<MarketOrderDto>(this.marketData)
                .subscribe((market: MarketOrderDto) => {
                    loyaltyOffer.marketData = market
                })
        }
        return loyaltyOffer
    }
}
