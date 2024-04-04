// - SERVICES
import { UniverseService } from "@app/services/universe.service";
// - DOMAIN
import { EsiType } from "@domain/esi/EsiType.esi"
import { Resource } from "../domain/Resource.domain";
import { ProcessedBlueprint } from "../domain/V1ProcessedBlueprint.domain";

export class ProcessedBlueprintDto {
    private blueprintType: number
    private output: EsiType
    private bom: Resource[]

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
    
    public transform(universeService: UniverseService): ProcessedBlueprint {
        const blueprint: ProcessedBlueprint = new ProcessedBlueprint()
        if (this.blueprintType) {
            universeService.apiv1_GetUniverseType(this.blueprintType)
                .subscribe((universeType: EsiType) => {
                    blueprint.type = universeType
                    blueprint.ready = true // Probably this will get deprecated since the update problem has been detected.
                })
        }
        if (this.output) blueprint.output = new EsiType(this.output)
        blueprint.bom = []
        for (const resource of this.bom)
            blueprint.bom.push(new Resource(resource))
        return blueprint
    }
}
