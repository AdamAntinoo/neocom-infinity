import { ITransformable } from "@Domain/interfaces/ITransformable.interface"
import { UUID, randomUUID } from "crypto"

export class V2MiningOperation implements ITransformable<V2MiningOperation>{
    public jsonClass: string = 'MiningOperation'
    public id: UUID = randomUUID()
    public date: string
    public quantity: number = 0
    public solarSystem: string
    public typeId: string

    constructor(fields: object = {}) {
        Object.assign(this, fields)
    }
    public transform(): V2MiningOperation {
        console.log('transform->no need transformation')
        return this
    }
}
