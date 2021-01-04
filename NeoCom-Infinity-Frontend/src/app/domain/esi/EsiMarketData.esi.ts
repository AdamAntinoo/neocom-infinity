// - DOMAIN
import { MarketOrderDto } from "@domain/industry/dto/MarketOrderDto.dto"
import { EsiNode } from "./EsiNode.esi"
import { UniverseMarketOrder } from "./UniverseMarketOrder.esi"

export class EsiMarketData extends EsiNode {
    private bestSellOrder: MarketOrderDto
    private bestBuyOrder: MarketOrderDto
    private sellOrders: UniverseMarketOrder[]
    private sellDeep: number
    private sellAverage: number
}
