import { IConverter } from "neocom-domain/dist/converter/IConverter.interface"
import { GetCharactersCharacterIdMining200Ok } from "application/domain/esi-model/models"
import { V1MiningResourceDto } from "neocom-domain/dto/V1MiningResourceDto.dto"

export class MiningOperationConverter<S, D> implements IConverter<GetCharactersCharacterIdMining200Ok, V1MiningResourceDto>{
    public convert(source: GetCharactersCharacterIdMining200Ok): V1MiningResourceDto {
        let solarSystem: string = undefined
        let type: string = undefined
        if (source.solar_system_id) {
            solarSystem = this.getSystemLink(source.solar_system_id)
        }
        if (source.type_id) {
            type = this.getTypeLink(source.type_id)
        }
        return new V1MiningResourceDto({
            id: this.miningResourceUniqueIdGenerator(source),
            date: source.date,
            quantity: source.quantity,
            solarSystem: solarSystem,
            typeId: type
        })
    }
    public getSystemLink(systemId: number): string {
        return 'https://esi.evetech.net/latest/universe/systems/' + systemId + '/?datasource=tranquility&language=en'
    }
    public getTypeLink(typeId: number): string {
        return 'https://esi.evetech.net/latest/universe/types/' + typeId + '/?datasource=tranquility&language=en'
    }
    private miningResourceUniqueIdGenerator(source : GetCharactersCharacterIdMining200Ok): string{
        return source.date + '/' + source.solar_system_id + '-' + source.type_id
    }
}
