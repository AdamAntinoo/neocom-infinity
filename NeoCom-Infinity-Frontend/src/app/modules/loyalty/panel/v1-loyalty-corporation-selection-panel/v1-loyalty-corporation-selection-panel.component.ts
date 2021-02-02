// - CORE
import { Component, Input } from '@angular/core';
import { OnInit } from '@angular/core';
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain';
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto';
import { NeoCom } from '@domain/NeoCom.domain';
import { NCVariant } from '@env/NeoComVariants';
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component';
// - DOMAIN
import { LoyaltyCorporationV1 } from '../../domain/LoyaltyCorporationV1.domain';
import { V1LoyaltyRecommendationsPageComponent } from '../../page/v1-loyalty-recommendations-page/v1-loyalty-recommendations-page.component';

@Component({
    selector: 'v1-loyalty-corporation-selection-panel',
    templateUrl: './v1-loyalty-corporation-selection-panel.component.html',
    styleUrls: ['./v1-loyalty-corporation-selection-panel.component.scss']
})
export class V1LoyaltyCorporationSelectionPanelComponent extends AppPanelComponent implements OnInit {
    @Input() container: V1LoyaltyRecommendationsPageComponent

    public self: V1LoyaltyCorporationSelectionPanelComponent
    public variant: NCVariant = NCVariant.LOYALTY
    public loyaltyCorporations: LoyaltyCorporationV1[] = []

    public ngOnInit() {
        this.self = this
        this.loyaltyCorporations.push(new LoyaltyCorporationV1({
            "id": 1000179,
            "ceo_id": 3018998,
            "creator_id": 1,
            "description": "It was us who brought civilization to this dark and perilous world, and it is us who uphold it, with compassion, strength and courage. We need you to help us save the Minmatar from themselves. We will help the lost ones find their way, capsuleer, and we will do it with the deathless force only you can wield.",
            "faction_id": 500003,
            "home_station_id": 60015064,
            "member_count": 18872,
            "name": "24th Imperial Crusade",
            "shares": 0,
            "tax_rate": 0,
            "ticker": "IC24"
        }))
        this.loyaltyCorporations.push(new LoyaltyCorporationV1({
            "id": 1000182,
            "ceo_id": 3018996,
            "creator_id": 1,
            "description": "The Minmatar heart sings for freedom and the Minmatar soul strives for open skies, but the Minmatar heart has been withering away in captivity. It is now up to you, capsuleer. You hold the power to free our people. You are the heroes of your generation. Join us in the struggle for freedom. Death to Amarr; long live the Minmatar Nation.",
            "faction_id": 500002,
            "home_station_id": 60015096,
            "member_count": 21088,
            "name": "Tribal Liberation Force",
            "shares": 0,
            "tax_rate": 0,
            "ticker": "TLIB"
        }))
    }
    // - I V I E W E R
    public fireSelectionChanged() {
        const corporation = this.getSelection()[0] as LoyaltyCorporationV1
        this.container.setSelectedCorporation(corporation)
    }
}
