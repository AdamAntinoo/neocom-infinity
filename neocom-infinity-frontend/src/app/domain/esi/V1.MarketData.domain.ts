import { EsiNode } from "./EsiNode.esi";

export class V1MarketData extends EsiNode{
    public buyPrice: number
    public buyOrderCount: number
    public sellPrice: number
    public sellOrderCount: number

    protected decode(): void {    }

}
