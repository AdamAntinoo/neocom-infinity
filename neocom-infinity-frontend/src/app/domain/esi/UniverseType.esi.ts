import { EsiNode } from "./EsiNode.esi"

export class UniverseType extends EsiNode {
    public typeId: number
    public name: string
    public description: string
    public groupId: number
    public marketGroupId: number
    public capacity: number
    public mass: number
    public packagedVolume: number
    public volume: number

    protected decode(): void { }
}
