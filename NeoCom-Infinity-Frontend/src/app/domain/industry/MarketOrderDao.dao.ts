export class MarketOrderDao {
    private station: object
    private volumeRemain: number
    private orderId: number
    private price: number
    private typeId: number

    constructor(values: Object = {}) {
        Object.assign(this, values)
    }

    public getStationName(): string {
        return this.station['stationName']
    }
    public getPrice(): number {
        return this.price
    }
}
