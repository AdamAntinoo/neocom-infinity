// - DOMAIN
import { NeoComDelayed } from "@domain/core/NeoComDelayed.domain";
import { Station } from "@domain/location/Station.domain";
import { AllianceV1 } from "./AllianceV1.domain";

export class PublicCorporationV1 extends NeoComDelayed {
    public corporationId: number
    public name: string
    public ceoid: number
    public creatorId: number
    public dateFounded: string
    public description: string
    public homeStation: Station
    public memberCount: number
    public shares: number
    public taxRate: number
    public ticker: string
    public url4Icon: string
    public allianceId: number
    public alliance: AllianceV1

    constructor(values: Object = {}) {
        super(values)
        this.jsonClass = 'PublicCorporationV1'
    }

    // - G E T T E R S
    public getCorporationId(): number {
        return this.corporationId
    }
}
