import { Component, Input, OnInit } from '@angular/core';
import { MarketOrderDto } from '@domain/industry/dto/MarketOrderDto.dto';

@Component({
    selector: 'v1-market-data',
    templateUrl: './v1-market-data-render.component.html',
    styleUrls: ['./v1-market-data-render.component.scss']
})
export class V1MarketDataRenderComponent {
    @Input() id: string
    @Input() marketData: MarketOrderDto

    public getUniqueId(): string {
        return this.id
    }
    public getStationName(): string {
        return this.marketData.getStationName()
    }
    public getPrice(): number {
        return this.marketData.getPrice()
    }
    public getDistanceHops(): number {
        return 2
    }
    public getHopTime(): number {
        return 3
    }
}
