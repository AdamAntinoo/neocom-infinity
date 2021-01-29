// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { OnDestroy } from '@angular/core'
import { Input } from '@angular/core'
import { Subscription } from 'rxjs'
// - DOMAIN
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component';
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { NCVariant } from '@env/NeoComVariants'
import { LoyaltyCorporationV1 } from '../../domain/LoyaltyCorporationV1.domain'

@Component({
    selector: 'v1-loyalty-offer-recommendations-panel',
    templateUrl: './v1-loyalty-offer-recommendations-panel.component.html',
    styleUrls: ['./v1-loyalty-offer-recommendations-panel.component.scss']
})
export class V1LoyaltyOfferRecommendationsPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() loyaltyCorporation: LoyaltyCorporationV1

    public ngOnInit(): void {
        console.log(">[V1LoyaltyOfferRecommendationsPanelComponent.ngOnInit]");
        this.startDownloading();
        this.setVariant(NCVariant.LOYALTY)
        this.refresh();
        console.log("<[V1LoyaltyOfferRecommendationsPanelComponent.ngOnInit]");
    }
    // - I R E F R E S H A B L E
    public clean(): void {
    }
    /**
     * Access the backend to retrieve the list of recommended offers for the selected Loyalty Corporation.
     */
    public refresh(): void {
        this.clean()
        console.log(JSON.stringify(this.loyaltyCorporation))
    }
}