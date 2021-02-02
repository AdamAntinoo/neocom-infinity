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
    selector: 'v1-loyalty-offer',
    templateUrl: './v1-loyalty-offer-render.component.html',
    styleUrls: ['./v1-loyalty-offer-render.component.scss']
})
export class V1LoyaltyOfferRenderComponent extends V2NodeContainerRenderComponent {
    public isReady(): boolean {
        if (this.node)
            if (this.getNode().type)
                if (this.getNode().marketData)
                    return true
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
        return platformConstants.DEFAULT_ICON_PLACEHOLDER
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
    public getMarketData(): MarketOrderDto {
        if (this.node) return this.getNode().marketData
    }
}
