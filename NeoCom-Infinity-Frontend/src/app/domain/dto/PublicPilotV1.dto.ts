// - SERVICES
import { HALResolver } from "@app/services/HALResolver.service";
// - DOMAIN
import { HALLink } from "@domain/hal/HALLink.hal";
import { UniverseRaceData } from "@domain/esi/UniverseRaceData.esi";
import { UniverseAncestryData } from "@domain/esi/UniverseAncestryData.esi";
import { UniverseBloodlineData } from "@domain/esi/UniverseBloodlineData.esi";
import { PublicCorporationV1 } from "@domain/corporation/PublicCorporationV1.domain";
import { PublicPilotV1 } from "@domain/character/PublicPilotV1.domain";

export class PublicPilotV1Dto {
    private corporation: HALLink<PublicCorporationV1>
    private raceData: UniverseRaceData
    private ancestryData: UniverseAncestryData
    private bloodlineData: UniverseBloodlineData

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }

    public transform(halResolver: HALResolver): PublicPilotV1 {
        const pilot: PublicPilotV1 = new PublicPilotV1(this)
        if (this.corporation) {
            this.corporation = new HALLink<PublicCorporationV1>(PublicCorporationV1).setContents(this.corporation)
            halResolver.resolve<PublicCorporationV1>(this.corporation)
                .subscribe((corporation: PublicCorporationV1) => {
                    pilot.corporation = corporation
                })
        }
        if (this.raceData) pilot.raceData = new UniverseRaceData(this.raceData)
        if (this.ancestryData) pilot.ancestryData = new UniverseAncestryData(this.ancestryData)
        if (this.bloodlineData) pilot.bloodlineData = new UniverseBloodlineData(this.bloodlineData)
        return pilot
    }
}
