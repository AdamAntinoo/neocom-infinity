// - INNOVATIVE
import { ICollaboration } from "@innovative/domain/interfaces/ICollaboration.interface";
// - DOMAIN
import { NeoCom } from "@domain/NeoCom.domain";
import { INamed } from "@innovative/domain/interfaces/INamed.interface";

export class LookupSolarSystem extends NeoCom implements INamed {
    public systemId: number
    public systemName: string
    public region: string
    public security: number

    constructor(values: Object = {}) {
        super(values)
        this.jsonClass = 'LookupSolarSystem'
    }

    // - I N A M E D
    public getName(): string {
        return this.systemName
    }
    // - G E T T E R S
    public getRegion(): string {
        return this.region
    }
}
