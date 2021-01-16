// - SERVICES
import { HALResolver } from "@app/services/HALResolver.service";
// - DOMAIN
import { HALLink } from "@domain/hal/HALLink.hal";
import { PilotV2 } from "@domain/character/PilotV2.domain";
import { CorporationV1Dto } from "./CorporationV1Dto.dto";
import { SpaceLocationV1 } from "@domain/location/SpaceLocationV1.domain";
import { UniverseRaceData } from "@domain/esi/UniverseRaceData.esi";
import { UniverseAncestryData } from "@domain/esi/UniverseAncestryData.esi";
import { UniverseBloodlineData } from "@domain/esi/UniverseBloodlineData.esi";

export class PilotV2Dto {
    private pilotId: number
    private corporation: HALLink<CorporationV1Dto>
    private pilotPublicData: object
    private url4Icon: string
    private lastKnownLocation: HALLink<SpaceLocationV1>
    private raceData: object
    private ancestryData: object
    private bloodlineData: object

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }

    public transform(halResolver: HALResolver): PilotV2 {
        const pilot: PilotV2 = new PilotV2()
        pilot.pilotId = this.pilotId
        pilot.birthday = this.pilotPublicData['birthday']
        pilot.description = this.pilotPublicData['description']
        pilot.gender = this.pilotPublicData['gender']
        pilot.name = this.pilotPublicData['name']
        pilot.securityStatus = this.pilotPublicData['securityStatus']
        pilot.titles = this.pilotPublicData['titles']
        pilot.url4Icon = this.url4Icon
        if (this.lastKnownLocation) {
            halResolver.resolve<SpaceLocationV1>(this.corporation)
                .subscribe((spaceLcoation: SpaceLocationV1) => {
                    pilot.lastKnownLocation = spaceLcoation
                })
        }
        if (this.raceData) pilot.raceData = new UniverseRaceData(this.raceData)
        if (this.ancestryData) pilot.ancestryData = new UniverseAncestryData(this.ancestryData)
        if (this.bloodlineData) pilot.bloodlineData = new UniverseBloodlineData(this.bloodlineData)
        return pilot
    }
}
