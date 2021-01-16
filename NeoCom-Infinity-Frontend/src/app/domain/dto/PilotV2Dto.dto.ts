// - SERVICES
import { HALResolver } from "@app/services/HALResolver.service";
// - DOMAIN
import { HALLink } from "@domain/hal/HALLink.hal";
import { PilotV2 } from "@domain/character/PilotV2.domain";
import { CorporationV1Dto } from "./CorporationV1Dto.dto";

export class PilotV2Dto {
    private pilotId: number
    private corporationId: number
    private corporation: HALLink<CorporationV1Dto>

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }

    public transform(halResolver: HALResolver): PilotV2 {
        const pilot: PilotV2 = new PilotV2()
        pilot.pilotId = this.pilotId
        if (this.corporationId)
            halResolver.resolve<CorporationV1Dto>(this.corporation)
                .subscribe((corporation: CorporationV1Dto) => {
                    pilot.corporation = new CorporationV1Dto(corporation).transform(halResolver)
                })
        return pilot
    }
}
