// - DOMAIN
import { EsiNode } from "./EsiNode.esi"

export class UniverseRaceData extends EsiNode {
    public allianceId: number
    public description: string
    public name: string
    public raceId: number

    protected decode (): void {}
}
