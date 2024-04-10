import { MarketOrderDto } from './MarketOrderDto.dto'

export class HullDto {
    public item: any
    public quantity: number
    public corporationHome: object
    public marketOrder: MarketOrderDto

    constructor(values: Object = {}) {
        Object.assign(this, values)
        this.transform()
    }
    private transform(): void {
        if (null != this.marketOrder) this.marketOrder = new MarketOrderDto(this.marketOrder)
    }
    public getHullClass(): string {
        if (null != this.item)
            return this.item['name']
        else return '-CLASS-'
    }
    public getHullURLIcon(): string {
        if (null != this.item)
            return 'https://image.eveonline.com/Type/' + this.item['typeId'] + '_64.png'
        else 'assets/res-ui/drawable/defaulticonplaceholder.png'
    }
    public getHullTypeId(): number {
        return this.item.typeId
    }
    public getTech(): string {
        return 'Tech I'
        // return this.item.tech
    }
    public getPrice(): number {
        return this.marketOrder.getPrice()
    }
    public getMarketStation(): string {
        return this.marketOrder.getStationName()
    }
    public getHullHops(): number {
        return 3
    }
    public getHullHopTime(): number {
        return this.getHullHops() * 2
    }
}
