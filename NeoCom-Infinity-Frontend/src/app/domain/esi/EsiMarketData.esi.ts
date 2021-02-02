// - DOMAIN
import { MarketOrderDto } from "@domain/industry/dto/MarketOrderDto.dto"
import { forEach } from "cypress/types/lodash"
import { EsiNode } from "./EsiNode.esi"
import { UniverseMarketOrder } from "./UniverseMarketOrder.esi"

export class EsiMarketData extends EsiNode {
    public typeId:number
    public bestSellOrder: MarketOrderDto
    private bestBuyOrder: MarketOrderDto
    private sellOrders: UniverseMarketOrder[]
    private sellDeep: number
    private sellAverage: number

    public decode() {
        if (this.bestSellOrder) this.bestSellOrder = new MarketOrderDto(this.bestSellOrder)
        if (this.bestBuyOrder) this.bestBuyOrder = new MarketOrderDto(this.bestBuyOrder)
        if (this.sellOrders) {
            const decodedSellOrders: UniverseMarketOrder[] = []
            for (let order of this.sellOrders)
                decodedSellOrders.push(new UniverseMarketOrder(order))
            this.sellOrders = decodedSellOrders
        }
    }
}
