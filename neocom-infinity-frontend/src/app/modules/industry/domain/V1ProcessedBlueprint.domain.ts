// - DOMAIN
import { NeoComDelayed } from "@domain/core/NeoComDelayed.domain";
import { EsiType } from "@domain/esi/EsiType.esi"
import { Resource } from "./Resource.domain"

/** @deprecated */
export class ProcessedBlueprint extends NeoComDelayed {
    public type: EsiType
    public output: EsiType
    public bom: Resource[]

    constructor(values: Object = {}) {
        super(values)
        this.jsonClass = 'ProcessedBlueprint'
    }

    // - G E T T E R S
    public getName(): string {
        if (this.type) return this.type.getName()
    }
    public getUniqueId(): string {
        if (this.type) return this.type.getTypeId() + ''
        else return '-'
    }
    public getTypeIconURL(): string {
        if (this.type) return this.type.getTypeIconURL()
        else return '-'
    }
    public getModuleName(): string {
        if (this.output) return this.output.getName()
        else return '-'
    }
}
