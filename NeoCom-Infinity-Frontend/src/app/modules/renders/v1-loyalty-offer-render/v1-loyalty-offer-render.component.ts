// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - SERVICES
// - DOMAIN
import { RenderComponent } from '../../shared/renders/render/render.component';
import { Pilot } from '@app/domain/Pilot.domain';
import { PilotV2 } from '@domain/character/PilotV2.domain';
import { PlatformConstants } from '@env/PlatformConstants';
import { PublicPilotV1 } from '@domain/character/PublicPilotV1.domain';
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';
import { LookupRegion } from '@app/modules/planetary/domain/LookupRegion.domain';
import { LoyaltyCorporationV1 } from '@app/modules/loyalty/domain/LoyaltyCorporationV1.domain';
import { LoyaltyOfferV1 } from '@app/modules/loyalty/domain/LoyaltyOfferV1.domain';
import { EsiMarketData } from '@domain/esi/EsiMarketData.esi';
import { MarketOrderDto } from '@domain/industry/dto/MarketOrderDto.dto';
import { TradeHistoryData } from '@app/modules/loyalty/domain/TradeHistoryData.domain';
import { EsiMarketsRegionsHistoryRecord } from '@domain/esi/EsiMarketsRegionsHistoryRecord.esi';
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';

@Component({
    selector: 'v1-loyalty-offer',
    templateUrl: './v1-loyalty-offer-render.component.html',
    styleUrls: ['./v1-loyalty-offer-render.component.scss']
})
export class V1LoyaltyOfferRenderComponent extends V2NodeContainerRenderComponent implements OnInit {
    public marketHistoryData: TradeHistoryData[] = [];
    public yscale: number = 10
    public colorScheme = {
        domain: ['blueviolet']
    }
    public yaxisTicks: any[] = [10]
    private historyUpdated: boolean = false

    public ngOnInit() {
        // this.updateChartData()
    }
    public hasData(): boolean {
        if (this.isReady()) {
            if (!this.historyUpdated) {
                this.updateChartData()
                this.historyUpdated = true
            }
            if (this.marketHistoryData.length > 1) return true
        } else return false
    }
    public isReady(): boolean {
        if (this.node)
            if (this.getNode().type)
                if (this.getNode().marketData)
                    if (this.getNode().marketHistory.length > 1) return true
        return false
    }
    public getNode(): LoyaltyOfferV1 {
        return this.node as LoyaltyOfferV1
    }
    public getUniqueId(): string {
        if (this.node) 'offer:' + this.getNode().offerId
        else return '-'
    }
    public getURLIcon(): string {
        if (this.node)
            if (this.getNode().getType())
                return this.getNode().getType().getTypeIconURL()
        return NeoComConstants.DEFAULT_ICON_PLACEHOLDER
    }
    public getLoyaltyCorporationName(): string {
        if (this.node) return this.getNode().corporationName
        else return '-'
    }
    public getName(): string {
        if (this.node) return this.getNode().type.getName()
        else return '-'
    }
    public getLpCost(): number {
        if (this.node) return this.getNode().lpCost
        else return 0
    }
    public getIskCost(): number {
        if (this.node) return this.getNode().iskCost
        else return 0
    }
    public getLpCalculatedValue(): number {
        if (this.node) return this.getNode().lpValue
        else return 0
    }
    public getMarketData(): EsiMarketData {
        if (this.node) return this.getNode().marketData
    }
    public getExpectedProfit(): number {
        if (this.node) {
            return this.getNode().marketData.bestSellOrder.getPrice() - this.getIskCost()
        } else return 0
    }
    public getExpectedProfitPercentage(): number {
        if (this.node) {
            return (this.getNode().marketData.bestSellOrder.getPrice() - this.getIskCost()) /
                this.getNode().marketData.bestSellOrder.getPrice()
        } else return 0
    }

    public updateChartData(): void {
        const charData: TradeHistoryData[] = []
        const sourceRecords: EsiMarketsRegionsHistoryRecord[] = this.getNode().marketHistory.reverse()
        if (sourceRecords.length > 10)
            for (let index = 0; index < 15; index++) {
                const record = sourceRecords[index]
                if (record) charData.push(new TradeHistoryData({ name: record.date, value: record.volume }))
            }
        this.yaxisTicks = this.generateTicksFromData(charData)
        this.marketHistoryData = charData.reverse()
    }
    private generateTicksFromData(data: TradeHistoryData[]): number[] {
        const ticks: number[] = []
        let max: number = 0
        let min: number = 9999
        for (const record of data) {
            if (record.value > max) max = record.value
            if (record.value < min) min = record.value
        }
        const tickSize = Math.floor(max / 4)
        let tick: number = 0
        while (tick < max) {
            tick += tickSize
            ticks.push(tick)
        }
        this.yscale = tick
        return ticks
    }
}
