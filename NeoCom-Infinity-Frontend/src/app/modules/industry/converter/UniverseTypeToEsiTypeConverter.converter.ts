// - DOMAIN
import { EsiType } from "@domain/esi/EsiType.esi";

export class UniverseTypeToEsiTypeConverter {
    public convert(input: any): T {
        const result: EsiType = new EsiType({
            typeId: input.type_id,
            name: input.name,
            description: input.description,
            marketGroupId: input.market_group_id,
            capacity: input.capacity,
            mass: input.mass,
            packagedVolume: input.packaged_volume,
            volume: input.volume
        })
        console.log('>[UniverseTypeToEsiTypeConverter.convert]> Result: ' + JSON.stringify(result))
        return result;
    }
}
