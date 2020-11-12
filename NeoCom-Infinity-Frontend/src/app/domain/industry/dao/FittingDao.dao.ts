// - DOMAIN
import { EveItemDao } from '@domain/core/dao/EveItemDao.dao'
import { HullDao } from './HullDao.dao'

export class FittingDao {
    public fittingItems: object[]
    public shipHull: EveItemDao
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
    private transform () : void{
        if (null != this.shipHull) this.shipHull = new EveItemDao(this.shipHull)
    }
}
