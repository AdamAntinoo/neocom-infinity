import { NeoCom } from '@domain/NeoCom.domain';

export class KnownSystem extends NeoCom {
    public systemId: number
    public systemName: string
    public constellationId: number
    public constellationName: string
    public regionId: number
    public regionName: string
    public securityLevel: number
    public securityClass: string
    public planetCount: number

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = "KnownSystem"
        this.transform()
    }
    private transform(): void { }

    // - G E T T E R S
    public getUniqueId(): number {
        return this.systemId
    }
    public getName(): string {
        return this.systemName
    }
}
