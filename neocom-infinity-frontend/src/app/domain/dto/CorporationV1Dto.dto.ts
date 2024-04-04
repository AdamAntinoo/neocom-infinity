// - SERVICES
import { HALResolver } from "@app/services/HALResolver.service";
// - DOMAIN
import { HALLink } from "@domain/hal/HALLink.hal";
import { CorporationV1 } from "@domain/corporation/CorporationV1.domain";
import { AllianceV1 } from "@domain/corporation/AllianceV1.domain";
import { Station } from "@domain/location/Station.domain";
import { PilotV2Dto } from "./PilotV2Dto.dto";

export class CorporationV1Dto {
    public corporationId: number
    public name: string
    public ceo: HALLink<PilotV2Dto>
    public creatorId: number
    public dateFounded: string
    public description: string
    public homeStation: HALLink<Station>
    public memberCount: number
    public shares: number
    public taxRate: number
    public ticker: string
    public url4Icon: string
    public allianceId: number
    public alliance: AllianceV1

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }

    public transform(halResolver: HALResolver): CorporationV1 {
        const corporation: CorporationV1 = new CorporationV1()
        corporation.corporationId = this.corporationId
        corporation.name = this.name
        corporation.creatorId = this.creatorId
        corporation.dateFounded = this.dateFounded
        corporation.memberCount = this.memberCount
        corporation.shares = this.shares
        corporation.taxRate = this.taxRate
        corporation.ticker = this.ticker
        corporation.url4Icon = this.url4Icon
        if (this.ceo)
            halResolver.resolve<PilotV2Dto>(this.ceo)
                .subscribe((pilot: PilotV2Dto) => {
                    corporation.ceo = new PilotV2Dto(pilot).transform(halResolver)
                })
        if (this.homeStation) {
            halResolver.resolve<Station>(this.ceo)
                .subscribe((station: Station) => {
                    corporation.homeStation = station
                })
        }
        if (this.alliance) corporation.alliance = new AllianceV1(this.alliance)
        return corporation
    }
}
