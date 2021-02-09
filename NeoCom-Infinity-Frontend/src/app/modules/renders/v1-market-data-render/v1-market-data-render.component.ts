// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - SERVICES
// - DOMAIN
import { RenderComponent } from '../../shared/renders/render/render.component';
import { Pilot } from '@app/domain/Pilot.domain';
import { PilotV2 } from '@domain/character/PilotV2.domain';
import { platformConstants } from '@env/platform-constants';
import { PublicPilotV1 } from '@domain/character/PublicPilotV1.domain';
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';
import { LookupRegion } from '@app/modules/planetary/domain/LookupRegion.domain';
import { LoyaltyCorporationV1 } from '@app/modules/loyalty/domain/LoyaltyCorporationV1.domain';
import { LoyaltyOfferV1 } from '@app/modules/loyalty/domain/LoyaltyOfferV1.domain';
import { EsiMarketData } from '@domain/esi/EsiMarketData.esi';
import { MarketOrderDto } from '@domain/industry/dto/MarketOrderDto.dto';

@Component({
    selector: 'v1-market-data',
    templateUrl: './v1-market-data-render.component.html',
    styleUrls: ['./v1-market-data-render.component.scss']
})
export class V1MarketDataRenderComponent {
    @Input() id: string
    @Input() marketData: EsiMarketData
    @Input() variant: string

    public getVariant(): string {
        if (this.variant) return this.variant
        else return '-DEFAULT-'
    }
    public getNode(): EsiMarketData {
        return this.marketData as EsiMarketData
    }
    public getUniqueId(): string {
        if (this.marketData) return 'marketdata:' + this.getNode().typeId
        return '-pending-'
    }
    public getStationName(): string {
        if (this.marketData) return this.getNode().bestSellOrder.getStationName()
        else return '-'
    }
    public getPrice(): number {
        if (this.marketData) return this.getNode().bestSellOrder.getPrice()
        else return 0.0
    }
    public getDistanceHops(): number {
        return 2
    }
    public getHopTime(): number {
        return 3
    }
}
