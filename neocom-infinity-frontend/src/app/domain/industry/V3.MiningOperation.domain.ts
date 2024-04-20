import { LookupSolarSystem } from "@app/modules/planetary/domain/LookupSolarSystem.domain"
import { NeoCom } from "@domain/NeoCom.domain"
import { V1Stack } from "@domain/esi/V1.Stack.domain"
import { MANDATORY_FIELD_MISSING, NeoComError } from "neocom-domain"

export class V3MiningOperation extends NeoCom {
    public jsonClass: string = 'MiningOperation'
    public id: string
    public date: string
    public solarSystem: LookupSolarSystem
    public resources: V1Stack[] = []

    public constructor(values: Object = {}) {
        super(values)
        // this.jsonClass = this.identify()
    }

    public identify(): string {
        return 'MiningOperation'
    }
    public getIdentifier(): string {
        return this.id
    }
    public getQuantity(): number {
        return 34
    }
    public getTypeName(): string {
        // if (undefined == this.type.target) return '-'
        // else
        return 'name'
    }
    public getResources(): V1Stack[] {
        return this.resources
    }
    public addResource(resource: V1Stack): V3MiningOperation {
        this.resources.push(resource)
        return this
    }

    public static Builder = class Builder {
        public miningOperation: V3MiningOperation

        constructor(fields: Object = {}) {
            this.miningOperation = new V3MiningOperation(fields)
        }
        public withSolarSystem(solarSystem: LookupSolarSystem): Builder {
            if (undefined == solarSystem) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.miningOperation.solarSystem = solarSystem
            return this
        }
        public build(): V3MiningOperation {
            return this.miningOperation
        }
    }
}
