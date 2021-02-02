// - CORE
import { Component } from '@angular/core';
import { ViewChild } from '@angular/core';
import { LoyaltyCorporationV1 } from '../../domain/LoyaltyCorporationV1.domain';
import { V1LoyaltyOfferRecommendationsPanelComponent } from '../../panel/v1-loyalty-offer-recommendations-panel/v1-loyalty-offer-recommendations-panel.component';

@Component({
    selector: 'v1-loyalty-recommendations-page',
    templateUrl: './v1-loyalty-recommendations-page.component.html',
    styleUrls: ['./v1-loyalty-recommendations-page.component.scss']
})
export class V1LoyaltyRecommendationsPageComponent {
    @ViewChild(V1LoyaltyOfferRecommendationsPanelComponent)
    loyaltyRecommendationsPanel: V1LoyaltyOfferRecommendationsPanelComponent
    public self: V1LoyaltyRecommendationsPageComponent

    constructor() {
        this.self = this
    }

    public selectedCorporation: LoyaltyCorporationV1
    // - A P I
    public setSelectedCorporation(corporation: LoyaltyCorporationV1): void {
        this.selectedCorporation = corporation
        if (this.loyaltyRecommendationsPanel)
            setTimeout(() => this.loyaltyRecommendationsPanel.refresh(), 200)
    }
}
