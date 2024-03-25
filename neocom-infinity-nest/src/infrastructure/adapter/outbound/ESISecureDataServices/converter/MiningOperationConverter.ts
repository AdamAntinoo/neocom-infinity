import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";
import { IConverter } from "@Inno/converter/IConverter.interface";
import { IESIMiningOperation } from "../domain/IESIMiningOperation.interface";
import { ESISecureDataServiceHALGeneratorAdapter } from "../esi.securedataservice.halgenerator.adapter";

export class MiningOperationConverter<S, D> implements IConverter<IESIMiningOperation, V2MiningOperation>{
    constructor(private halGenerator: ESISecureDataServiceHALGeneratorAdapter){}

    public convert(source: IESIMiningOperation): V2MiningOperation {
        let solarSystem: string = undefined
        let type: string = undefined
        if (source.solar_system_id) {
            solarSystem = this.halGenerator.getSystemLink(source.solar_system_id)
        }
        if (source.type_id) {
            type = this.halGenerator.getTypeLink(source.type_id)
        }
        return new V2MiningOperation({
            id: source.date + '-' + source.solar_system_id + '-' + source.type_id,
            date: source.date,
            quantity: source.quantity,
            solarSystem: solarSystem,
            typeId: type
        })
    }
}
