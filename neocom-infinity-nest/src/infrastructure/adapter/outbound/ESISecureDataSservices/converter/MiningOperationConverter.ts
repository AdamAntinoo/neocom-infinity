// import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";
import { IConverter } from "@Domain/interfaces/IConverter.interface";
import { IESIMiningOperation } from "../domain/IESIMiningOperation.interface";

export class MiningOperationConverter<S, D> implements IConverter<IESIMiningOperation, V2MiningOperation>{
    public convert(source: IESIMiningOperation): V2MiningOperation {
        let solarSystem: string = undefined
        let type: string = undefined
        if (source.solar_system_id) {
            solarSystem = 'http://localhost'
        }
        if (source.type_id) {
            type = 'jhjhwfh'
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
