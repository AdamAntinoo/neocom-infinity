import { MarketOrderDao } from './MarketOrderDao.dao'

export class BuildActionDao {
    private actionType: string = 'BUY'
    private location: string
    private hops: number
    private item: object
    private quantity: number
    private corporationHome: object
    private marketOrder: MarketOrderDao

    constructor(values: Object = {}) {
        // super()
        Object.assign(this, values)
        // this.jsonClass = 'FittingBuildConfiguration'
        this.transform()
    }
    private transform(): void {
        if (null != this.marketOrder) this.marketOrder = new MarketOrderDao(this.marketOrder)
    }
    public getModuleName(): string {
        return this.item['name']
    }
    public getTech(): string {
        // return this.item.getTech()
        return 'Tech I'
    }
    public getURLIcon(): string {
        if (null != this.item)
            return 'https://image.eveonline.com/Type/' + this.item['typeId'] + '_64.png'
        else 'assets/res-ui/drawable/defaulticonplaceholder.png'
    }
    public getStationName(): string {
        return this.marketOrder.getStationName()
    }
    public getPrice(): number {
        return this.marketOrder.getPrice()
    }
    public getHops(): number {
        return this.hops
    }
}
