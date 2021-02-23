// - DOMAIN
import { HALLink } from "@domain/hal/HALLink.hal"
import { EsiCategory } from "./EsiCategory.esi"
import { EsiGroup } from "./EsiGroup.esi"
import { EsiMarketData } from "./EsiMarketData.esi"
import { EsiNode } from "./EsiNode.esi"
import { UniverseType } from "./UniverseType.esi"

export class EsiType extends EsiNode {
    private typeId: number
    public name: string
    public description: string
    public group: EsiGroup
    public category: EsiCategory
    public type: UniverseType
    public tech: string
    public volume: number
    public isBlueprint: boolean = false
    public typeIconURL: string
    public marketData: EsiMarketData

    constructor(values: Object = {}) {
        super()
        // Warning. References objects can come as links so should be cleared on construction
        this.marketData = undefined
    }

    public decode() {
        if (this.group) this.group = new EsiGroup(this.group)
        if (this.category) this.category = new EsiCategory(this.category)
        if (this.type) this.type = new UniverseType(this.type)
    }
    // - G E T T E R S
    public getName(): string {
        return this.name
    }
    public getTypeId(): number {
        return this.typeId
    }
    public getTypeIconURL(): string {
        return this.typeIconURL
    }
}
