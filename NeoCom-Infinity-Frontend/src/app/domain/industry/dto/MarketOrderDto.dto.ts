// - DOMAIN
import { StationDto } from '@domain/core/dto/StationDto.dto'

export class MarketOrderDto {
    private station: StationDto
    private orderId: number
    private volumeRemain: number
    private typeId: number
    private price: number

    constructor(values: Object = {}) {
        Object.assign(this, values)
        this.decode()
    }
    public decode() {
        if (this.station) this.station = new StationDto(this.station)
    }

    // - G E T T E R S   &   S E T T E R S
    public getStationName(): string {
        return this.station['stationName']
    }
    public getPrice(): number {
        return this.price
    }
}
