// - DOMAIN
import { IDtoCompliant } from '@innovative/domain/interfaces/IDtoCompliant.interface'

export class SystemDto implements IDtoCompliant {
    public systemId: number
    public systemName : string
    public constellationId: number
    public constellationName : string
    public regionId : number
    public regionName : string
    public securityLevel : number
    public securityClass : string

    constructor(values: Object = {}) {
        Object.assign(this, values)
    }
    public transform(): void { }
}
