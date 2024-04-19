import { V1EsiTypeDto } from "neocom-domain";

export interface EsiUniversepi {
    esiGetTypeInformation(typeId:number): Promise<V1EsiTypeDto>
}
