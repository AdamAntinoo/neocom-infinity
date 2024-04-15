// - DOMAIN
import { EveItemDto } from 'neocom-domain/EveItemDto.dto'
import { HullDto } from './HullDto.dto'

export class FittingDto {
    public fittingItems: object[]
    public shipHull: EveItemDto
    public name: string
    public fittingId: number
    public shipTypeId: number
    public urlforItem: string
    public hullGroup: string
    public groupName: string

    constructor(values: Object = {}) {
        Object.assign(this, values)
        this.transform()
    }
    private transform(): void {
        if (null != this.shipHull) this.shipHull = new EveItemDto(this.shipHull)
    }
    // -  G E T T E R S
    public getHullGroup(): string {
        return this.shipHull.hullGroup
    }
}
