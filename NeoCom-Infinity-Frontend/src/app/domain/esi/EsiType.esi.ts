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
}
