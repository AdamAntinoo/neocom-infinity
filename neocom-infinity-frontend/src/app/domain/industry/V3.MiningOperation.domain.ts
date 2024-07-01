import { LookupSolarSystem } from "@app/modules/planetary/domain/LookupSolarSystem.domain"
import { NeoCom } from "@domain/NeoCom.domain"
import { V1Stack } from "@domain/esi/V1.Stack.domain"
import { V1SpaceLocation } from "@domain/esi/v1.SpaceLocation.domain"
import { MANDATORY_FIELD_MISSING, NeoComError, V1SpaceLocationDto } from "neocom-domain"

export class V3MiningOperation extends NeoCom {
    public jsonClass: string = 'MiningOperation'
    public id: string
    public date: string
    public solarSystem: V1SpaceLocation
    public resources: V1Stack[] = []

    public identify(): string {
        return 'MiningOperation'
    }
    public getIdentifier(): string {
        return this.id
    }
    public getResources(): V1Stack[] {
        return this.resources
    }
    public getEstimatedValue(): number {
        let value: number = 0.0
        for (let resource of this.resources) {
            value += resource.type.marketData.sellPrice * resource.quantity
        }
        return value
    }
    public getVolume () : number{
        let volume: number = 0.0
        for (let resource of this.resources) {
            volume += resource.type.volume * resource.quantity
        }
        return volume
    }
    /** @deprecated */
    public addResource(resource: V1Stack): V3MiningOperation {
        this.resources.push(resource)
        return this
    }
    /** @deprecated */
    public withSolarSystem(solarSystem: V1SpaceLocation): V3MiningOperation {
        // if (undefined == solarSystem) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
        this.solarSystem = solarSystem
        return this
    }

    public static Builder = class Builder {
        public miningOperation: V3MiningOperation

        constructor(fields: Object = {}) {
            this.miningOperation = new V3MiningOperation(fields)
        }
        public withSolarSystem(solarSystem: V1SpaceLocation): Builder {
            if (undefined == solarSystem) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.miningOperation.solarSystem = solarSystem
            return this
        }
        public withResources(resources: V1Stack[]): Builder {
            if (undefined == resources) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.miningOperation.resources = resources
            return this
        }
        public build(): V3MiningOperation {
            return this.miningOperation
        }
    }
}
