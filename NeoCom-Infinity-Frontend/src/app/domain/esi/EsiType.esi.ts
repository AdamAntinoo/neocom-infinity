// - DOMAIN
import { EsiNode } from "./EsiNode.esi"

export class EsiType extends EsiNode {
    private typeId: number
    public name: string
    public description: string
    public marketGroupId: number
    public capacity: number
    public mass: number
    public packagedVolume: number
    public volume: number

    // - G E T T E R S
    public getName(): string {
        return this.name
    }
    public getTypeId(): number {
        return this.typeId
    }
    public getTypeIconURL(): string {
        return 'https://image.eveonline.com/Type/' + this.typeId + '_64.png'
    }
}
