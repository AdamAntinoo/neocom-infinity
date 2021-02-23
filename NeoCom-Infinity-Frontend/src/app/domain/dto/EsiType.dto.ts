// - SERVICES
import { HALResolver } from "@app/services/HALResolver.service";
// - DOMAIN
import { HALLink } from "@domain/hal/HALLink.hal";
import { EsiMarketData } from "@domain/esi/EsiMarketData.esi";
import { EsiType } from "@domain/esi/EsiType.esi";

export class EsiTypeDto {
    public marketData: HALLink<EsiMarketData>

    constructor(values: Object = {}) {
        Object.assign(this, values);
        if (this.marketData)
            this.marketData = new HALLink<EsiMarketData>(EsiMarketData).setContents(this.marketData)
    }

    public transform(halResolver: HALResolver): EsiType {
        const esiType: EsiType = new EsiType(this)
        if (this.marketData) {
            halResolver.resolve<EsiMarketData>(this.marketData)
                .subscribe((market: EsiMarketData) => {
                    esiType.marketData = market
                })
        }
        return esiType
    }
}
