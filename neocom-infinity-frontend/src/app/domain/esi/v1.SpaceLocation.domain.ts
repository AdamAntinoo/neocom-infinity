import { EsiNode } from "./EsiNode.esi"

export class V1SpaceLocation extends EsiNode {
    public referenceType?: string // This is the real interface this instance represents. If can be Region-Constellation-System...
    public regionId?: number
    public regionName?: string
    public constellationId?: number
    public constellationName?: string
    public solarSystemId?: number
    public solarSystemName?: string
    public stationId?: number
    public stationName?: string
    public securityClass?: string
    public securityStatus?: number

    protected decode(): void { }

}
