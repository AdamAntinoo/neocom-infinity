// - DOMAIN
import { NeoCom } from '@domain/NeoCom.domain'

export class StationDto {
    public locationId: number
    public regionId: number
    public regionName: string
    public constellationId: number
    public constellationName: string
    public systemId: number
    public systemName: string
    public stationId: number
    public stationName: string

    constructor(values: Object = {}) {
        Object.assign(this, values)
    }
}
