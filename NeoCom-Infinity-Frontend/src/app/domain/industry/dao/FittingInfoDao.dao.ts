// - DOMAIN
import { NeoCom } from '@domain/NeoCom.domain'
import { BuildActionDao } from './BuildActionDao.dao'
import { FittingItemHAL } from '../hal/FittingItemHAL.hal'
import { HullDao } from './HullDao.dao'
import { FittingDao } from './FittingDao.dao'

export class FittingInfoDao {
    public fitting: FittingDao
    public hull: HullDao
    public hullAction: BuildActionDao

    constructor(values: Object = {}) {
        Object.assign(this, values)
        this.transform()
    }
    private transform(): void {
        if (null != this.fitting) this.fitting = new FittingDao(this.fitting)
        if (null != this.hull) this.hull = new HullDao(this.hull)
        if (null != this.hullAction) this.hullAction = new BuildActionDao(this.hullAction)
    }
}
