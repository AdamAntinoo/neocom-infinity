import { EsiNode } from "./EsiNode.esi";

export class UniverseMarketOrder extends EsiNode {
    public duration: number
    public isBuyOrder: boolean
    public issued: string
    public locationId: number
    public minVolume: number
    public orderId: number
    public price: number
    public range: string
    public systemId: number
    public typeId: number
    public volumeRemain: number
    public volumeTotal: number
}
