import { EsiNode } from "./EsiNode.esi"
import { V1MarketData } from "./V1.MarketData.domain"

export class V2UniverseType extends EsiNode {
    public typeId: number
    public name: string
    public description: string
    public iconId: number
    public groupId: number
    public groupName: string
    public categoryId: number
    public categoryName: string
    public capacity: number
    public packagedVolume: number
    public volume: number
    public marketData: V1MarketData

    public withMarketData ( marketData : V1MarketData): V2UniverseType{
        this.marketData=marketData
        return this
    }

    protected decode(): void { }
}
