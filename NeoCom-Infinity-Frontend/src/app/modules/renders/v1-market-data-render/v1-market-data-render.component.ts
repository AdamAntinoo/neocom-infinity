import { Component, Input, OnInit } from '@angular/core';
import { MarketOrderDao } from '@domain/industry/dao/MarketOrderDao.dao';

@Component({
    selector: 'v1-market-data',
    templateUrl: './v1-market-data-render.component.html',
    styleUrls: ['./v1-market-data-render.component.scss']
})
export class V1MarketDataRenderComponent {
    @Input() marketData: MarketOrderDao

    public getStationName(): string {
        return this.marketData.getStationName()
    }
    public getPrice(): number {
        return this.marketData.getPrice()
    }
    public getDistanceHops(): number {
        return 2
    }
}
