// - CORE MODULES
import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
// - ROUTING
import { Routes } from '@angular/router'
import { RouterModule } from '@angular/router';
// - APPLICATION MODULES
import { RendersModule } from '@app/modules/renders/renders.module';
// - COMPONENTS
import { V1LoyaltyRecommendationsPageComponent } from './page/v1-loyalty-recommendations-page/v1-loyalty-recommendations-page.component';
import { V1LoyaltyCorporationSelectionPanelComponent } from './panel/v1-loyalty-corporation-selection-panel/v1-loyalty-corporation-selection-panel.component';
import { V1LoyaltyOfferRecommendationsPanelComponent } from './panel/v1-loyalty-offer-recommendations-panel/v1-loyalty-offer-recommendations-panel.component';

const routes: Routes = [
    { path: '', component: V1LoyaltyRecommendationsPageComponent }
];
@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(routes),
        RendersModule
    ],
    declarations: [
        V1LoyaltyRecommendationsPageComponent,
        V1LoyaltyCorporationSelectionPanelComponent,
        V1LoyaltyOfferRecommendationsPanelComponent],
    exports: [RouterModule]
})
export class LoyaltyModule { }
