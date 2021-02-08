// - SERVICES
import { HALResolver } from "@app/services/HALResolver.service";
// - DOMAIN
import { EsiType } from "@domain/esi/EsiType.esi"
import { HALLink } from "@domain/hal/HALLink.hal";
import { MarketOrderDto } from "@domain/industry/dto/MarketOrderDto.dto";
import { isThisHour } from "date-fns/esm";
import { LoyaltyOfferV1 } from "../domain/LoyaltyOfferV1.domain";
import { UniverseService } from "@app/services/universe.service";
import { EsiMarketData } from "@domain/esi/EsiMarketData.esi";
import { EsiMarketsRegionsHistory } from "@domain/esi/EsiMarketsRegionsHistory.esi";
import { EsiMarketsRegionsHistoryRecord } from "@domain/esi/EsiMarketsRegionsHistoryRecord.esi";

export class LoyaltyOfferDto {
    public typeId: number
    public type: HALLink<EsiType>
    public marketData: HALLink<EsiMarketData>
    public marketHistory: any[]

    constructor(values: Object = {}) {
        Object.assign(this, values);
        if (this.type)
            this.type = new HALLink<EsiType>(EsiType).setContents(this.type)
        if (this.marketData)
            this.marketData = new HALLink<EsiMarketData>(EsiMarketData).setContents(this.marketData)
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
            halResolver.resolve<EsiMarketData>(this.marketData)
                .subscribe((market: EsiMarketData) => {
                    loyaltyOffer.marketData = market
                })
        }
        if (this.marketHistory) {
            loyaltyOffer.marketHistory = []
            for (const record of this.marketHistory)
                loyaltyOffer.marketHistory.push(new EsiMarketsRegionsHistoryRecord(record))
        }
        return loyaltyOffer
    }
}
