// - DOMAIN
import { StationDto } from '@domain/core/dto/StationDto.dto'
import { NeoCom } from '@domain/NeoCom.domain'
import { MarketOrderDto } from './MarketOrderDto.dto'

export class BuildActionDto extends NeoCom {
    private actionType: string = 'BUY'
    private corporationHome: StationDto
    private marketOrder: MarketOrderDto

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = 'BuildActionDao'
        this.transform()
    }
    private transform(): void {
        if (null != this.marketOrder) this.marketOrder = new MarketOrderDto(this.marketOrder)
    }
    public getPrice(): number {
        return this.marketOrder.getPrice()
    }
    public getStationName(): string {
        return this.marketOrder.getStationName()
    }
    public getMarketOrder(): MarketOrderDto {
        return this.marketOrder
    }
}
