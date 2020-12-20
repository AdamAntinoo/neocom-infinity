// - DOMAIN
import { StationDto } from '@domain/core/dto/StationDto.dto'
import { NeoCom } from '@domain/NeoCom.domain'
import { IDtoCompliant } from '@innovative/domain/interfaces/IDtoCompliant.interface'
import { MarketOrderDto } from './MarketOrderDto.dto'

export class BuildActionDto extends NeoCom implements IDtoCompliant {
    private actionType: string = 'BUY'
    private corporationHome: StationDto
    private marketOrder: MarketOrderDto

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = 'BuildActionDao'
        this.transform()
    }
    
    // - I D T O C O M P L I A N T
    public transform(): void {
        if (null != this.marketOrder) this.marketOrder = new MarketOrderDto(this.marketOrder)
    }

    // - G E T T E R S
    public getActionTypeName(): string {
        return this.actionType
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
