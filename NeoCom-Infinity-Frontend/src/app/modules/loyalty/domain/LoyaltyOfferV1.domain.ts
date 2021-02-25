// - CORE
import { EsiMarketData } from '@domain/esi/EsiMarketData.esi';
import { EsiMarketsRegionsHistoryRecord } from '@domain/esi/EsiMarketsRegionsHistoryRecord.esi';
import { EsiType } from '@domain/esi/EsiType.esi';
import { NeoCom } from '@domain/NeoCom.domain';

export class LoyaltyOfferV1 extends NeoCom {
    public offerId: number
    public typeId: number
    public type: EsiType
    public typeName: string
    public corporationId: number
    public corporationName: string
    public lpValue: number
    public iskCost: number
    public lpCost: number
    public quantity: number
    public marketRegionId: number
    public price: number
    public marketData: EsiMarketData
    public marketHistory: EsiMarketsRegionsHistoryRecord[]

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = 'LoyaltyOfferV1'
        // Warning. References objects can come as links so should be cleared on construction
        this.type = undefined
        this.marketData = undefined
        this.marketHistory = []
    }

    // - G E T T E R S
    public getType(): EsiType {
        return this.type
    }
}
