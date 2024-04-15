import { LookupSolarSystem } from "@app/modules/planetary/domain/LookupSolarSystem.domain"
import { NeoCom } from "@domain/NeoCom.domain"
import { EveItemDto } from "@domain/core/dto/EveItemDto.dto"
import { EsiType } from "@domain/esi/EsiType.esi"
import { UniverseType } from "@domain/esi/UniverseType.esi"
import { HALLink } from "@innovative/domain/HALLink.domain"
import { ITransformable } from "neocom-domain"

export class V3MiningOperation extends NeoCom implements ITransformable<V3MiningOperation>{
    public jsonClass: string = 'MiningOperation'
    public id?: string
    public date?: string
    public quantity?: number
    public solarSystem: HALLink<LookupSolarSystem>
    public type: HALLink<UniverseType>

    constructor(values: Object = {}) {
        super(values)
        this.jsonClass = this.identify()
    }

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
    public async getTypeName2(): Promise<string> {
        const resolvedType = await this.type.resolve()
        return resolvedType.name
    }
    public getTypeName(): string {
        if (undefined == this.type.target) return '-'
        else return this.type.target.name
    }
}