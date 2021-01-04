// - DOMAIN
import { EsiCategory } from "@domain/esi/EsiCategory.esi";
import { EsiGroup } from "@domain/esi/EsiGroup.esi";
import { EsiMarketData } from "@domain/esi/EsiMarketData.esi";
import { EsiType } from "@domain/esi/EsiType.esi";
import { HALLink } from "@domain/hal/HALLink.hal";
import { NeoCom } from "@domain/NeoCom.domain";

export class Resource extends NeoCom {
    private typeId: number
    private name: string
    private quantity: number
    private group: EsiGroup
    private category: EsiCategory
    private type: EsiType
    private tech: string
    private volume: number
    private isBlueprint: boolean
    private typeIconUrl: string
    private marketData: HALLink<EsiMarketData>

    constructor(values: Object = {}) {
        super(values)
        // // Object.assign(this, values);
        // this.decode()
    }

    public decode(): void {
        if ( this.group) this.group = new EsiGroup(this.group)
        if ( this.category) this.category = new EsiCategory(this.category)
        if ( this.type) this.type = new EsiType(this.type)
     }

}
