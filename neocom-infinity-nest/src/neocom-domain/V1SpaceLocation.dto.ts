import { Record } from "neocom-domain";

export class V1SpaceLocationDto extends Record {
    public override jsonClass: string = 'SpaceLocationDto'
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
}
