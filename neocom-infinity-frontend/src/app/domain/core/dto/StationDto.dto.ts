// - DOMAIN
import { IDtoCompliant } from '@innovative/domain/interfaces/IDtoCompliant.interface'

export class StationDto implements IDtoCompliant {
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
    // - I D T O C O M P L I A N T
    public transform(): void { }
    // - G E T T E R S   &   S E T T E R S
    public getStationName(): string {
        return this.stationName
    }
}
