import { LookupSolarSystem } from "@app/modules/planetary/domain/LookupSolarSystem.domain"
import { NeoCom } from "@domain/NeoCom.domain"
import { EveItemDto } from "neocom-domain/EveItemDto.dto"
import { EsiType } from "@domain/esi/EsiType.esi"
import { UniverseType } from "@domain/esi/UniverseType.esi"
import { HALLink } from "@innovative/domain/HALLink.domain"
import { ITransformable, NeoComError, V1MiningResourceDto } from "neocom-domain"
import { V1MiningOperation } from "./V1.MiningOperation.domain"
import { V1Stack } from "@domain/V1Stack.domain"

export class V3MiningOperation extends NeoCom implements ITransformable<V3MiningOperation>{
    public jsonClass: string = 'MiningOperation'
    public id?: string
    public date?: string
    // public quantity?: number
    public solarSystem: HALLink<LookupSolarSystem>
    private resources: V1Stack[]
    // public type: HALLink<UniverseType>

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
    public getResources(): V1Stack[] {
        return this.resources
    }
    public getSolarSystemId(): number {
        return this.solarSystem.getTarget().systemId
    }
    // public getQuantity(): number {
    //     return this.quantity
    // }
    // public async getTypeName2(): Promise<string> {
    //     const resolvedType = await this.type.resolve()
    //     return resolvedType.name
    // }
    // public getTypeName(): string {
    //     if (undefined == this.type.target) return '-'
    //     else return this.type.target.name
    // }

    public static Builder = class Builder {
        public operation: V3MiningOperation

        constructor(values: Object = {}) {
            this.operation = new V3MiningOperation(values)
            this.operation.resources = []
        }
        public addResource(resource: V1Stack) {
            // if ( undefined == resource)throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.operation.resources.push(resource)
        }
        public build(): V3MiningOperation {
            // if (undefined == this.operation.id) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            // if (undefined == this.operation.date) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            // if (undefined == this.operation.solarSystem) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            return this.operation
        }
    }
}
