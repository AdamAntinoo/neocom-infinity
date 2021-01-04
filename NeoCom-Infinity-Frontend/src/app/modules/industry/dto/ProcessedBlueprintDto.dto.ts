// - DOMAIN
import { ESIUniverseDataService } from "@app/services/ESIUniverseData.service";
import { EsiType } from "@domain/esi/EsiType.esi"
import { UniverseType } from "@domain/esi/UniverseType.esi";
import { NeoCom } from "@domain/NeoCom.domain";
import { UniverseTypeToEsiTypeConverter } from "../converter/UniverseTypeToEsiTypeConverter.converter";
import { Resource } from "../domain/Resource.domain";
import { ProcessedBlueprint } from "../domain/V1ProcessedBlueprint.domain";

export class ProcessedBlueprintDto {
    private blueprintType: number
    private bom: Resource[]

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
    public transform(universeService: ESIUniverseDataService): ProcessedBlueprint {
        const blueprint: ProcessedBlueprint = new ProcessedBlueprint()
        if (this.blueprintType) {
            universeService.apiEsiUniverseTypesData(this.blueprintType)
                .subscribe((universeType: UniverseType) => blueprint.type =
                    new UniverseTypeToEsiTypeConverter<EsiType>().convert(universeType))
        }
        blueprint.bom = []
        for (const resource of this.bom)
            blueprint.bom.push(new Resource(resource))
        return blueprint
    }
}
