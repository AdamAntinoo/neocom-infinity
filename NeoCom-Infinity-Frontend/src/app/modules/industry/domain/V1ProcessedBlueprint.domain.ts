// - DOMAIN
import { EsiType } from "@domain/esi/EsiType.esi"
import { NeoCom } from "@domain/NeoCom.domain";
import { Resource } from "./Resource.domain"

export class ProcessedBlueprint extends NeoCom {
    public type: EsiType
    public bom: Resource[]

    // constructor(values: Object = {}) {
    //     super(values)
    // }

    // - G E T T E R S
    public getName(): string {
        if (this.type) return this.type.getName()
    }
}
