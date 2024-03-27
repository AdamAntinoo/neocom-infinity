// - DOMAIN
import { NeoCom } from '@domain/NeoCom.domain'
import { BuildActionDto } from './BuildActionDto.dto'
import { FittingItemHAL } from '../hal/FittingItemHAL.hal'
import { HullDto } from './HullDto.dto'
import { FittingDto } from './FittingDto.dto'

export class FittingInfoDto {
    public fitting: FittingDto
    public hull: HullDto
    public hullAction: BuildActionDto

    constructor(values: Object = {}) {
        Object.assign(this, values)
        this.transform()
    }
    private transform(): void {
        if (null != this.fitting) this.fitting = new FittingDto(this.fitting)
        if (null != this.hull) this.hull = new HullDto(this.hull)
        if (null != this.hullAction) this.hullAction = new BuildActionDto(this.hullAction)
    }
}
