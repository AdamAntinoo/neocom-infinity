// - DOMAIN
import { StationDao } from '@domain/core/dao/StationDao.dao'

export class MarketOrderDao {
    private station: StationDao
    private orderId: number
    private volumeRemain: number
    private typeId: number
    private price: number

    constructor(values: Object = {}) {
        Object.assign(this, values)
    }

    // - G E T T E R S   &   S E T T E R S
    public getStationName(): string {
        return this.station['stationName']
    }
    public getPrice(): number {
        return this.price
    }
}
