// - CORE
import { Component } from '@angular/core';
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain';
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto';
import { NeoCom } from '@domain/NeoCom.domain';
import { NCVariant } from '@env/NeoComVariants';
// - DOMAIN
import { LoyaltyCorporationV1 } from '../../domain/LoyaltyCorporationV1.domain';

@Component({
    selector: 'v1-loyalty-corporation-selection-panel',
    templateUrl: './v1-loyalty-corporation-selection-panel.component.html',
    styleUrls: ['./v1-loyalty-corporation-selection-panel.component.scss']
})
export class V1LoyaltyCorporationSelectionPanelComponent  {
    public self:V1LoyaltyCorporationSelectionPanelComponent
    public variant : NCVariant=NCVariant.LOYALTY
    public loyaltyCorporations: LoyaltyCorporationV1[] = []
    constructor() {
        this.self=this
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
    }
}
