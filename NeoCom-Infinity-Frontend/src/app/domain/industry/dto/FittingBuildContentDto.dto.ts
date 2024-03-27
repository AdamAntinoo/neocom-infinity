// - DOMAIN
import { NeoCom } from '@domain/NeoCom.domain'
import { BuildActionDto } from './BuildActionDto.dto'
import { FittingItemHAL } from '../hal/FittingItemHAL.hal'
import { MarketOrderDto } from './MarketOrderDto.dto'

export class FittingBuildContentDto extends NeoCom {
    private id: string
    private fittingItem: FittingItemHAL
    private action: BuildActionDto

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = 'FittingBuildContentDao'
        this.transform()
    }
    /**
     * Transform json serialized data into DAO or HAL Typescript instances.
     */
    protected transform(): void {
        if (null != this.fittingItem) this.fittingItem = new FittingItemHAL(this.fittingItem)
        if (null != this.action) this.action = new BuildActionDto(this.action)
    }
    public getId(): string {
        return this.id
    }
    /**
     * Calculates the name of the contents group that matches the location flag name. The groups are the HIGH, MID, LOW and RIGS groups plus the different cargo holds that can be defined inside a ship. The default for any unmapped location is the CARGO group location.
     */
    public getLocationGroup(): string {
        switch (this.fittingItem.location) {
            case 'LOSLOT0':
            case 'LOSLOT1':
            case 'LOSLOT2':
                return 'LOW-SLOTS'
            case 'MEDSLOT0':
            case 'MEDSLOT1':
            case 'MEDSLOT2':
            case 'MEDSLOT3':
                return 'MED-SLOTS'
            case 'HISLOT0':
            case 'HISLOT1':
            case 'HISLOT2':
            case 'HISLOT3':
                return 'HIGH-SLOTS'
            case 'RIGSLOT0':
            case 'RIGSLOT1':
            case 'RIGSLOT2':
                return 'RIG-SLOTS'
            default: return 'CARGO-BAY'
        }
    }
    public getFittingItem(): FittingItemHAL {
        return this.fittingItem
    }
    public getMarketOrder(): MarketOrderDto {
        return this.action.getMarketOrder()
    }
}
