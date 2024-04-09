import { NeoCom } from "@domain/NeoCom.domain"
import { ITransformable } from "neocom-domain"

export class V3MiningOperation extends NeoCom implements ITransformable<V3MiningOperation>{
    public jsonClass: string = 'MiningOperation'
    public id?: string
    public date?: string
    public quantity?: number = 0
    public solarSystem?: string
    public typeId?: string

    public transform(): V3MiningOperation {
        console.log('transform->no need transformation')
        return this
    }

    public identify(): string {
        return 'MiningOperation'
    }
    public getIdentifier(): string {
        return this.id
    }
    public getQuantity(): number {
        return this.quantity
    }
}
