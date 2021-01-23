// - INNOVATIVE
import { ICollaboration } from "@innovative/domain/interfaces/ICollaboration.interface";
// - DOMAIN
import { NeoCom } from "@domain/NeoCom.domain";
import { LookupSolarSystem } from "./LookupSolarSystem.domain";
import { INamed } from "@innovative/domain/interfaces/INamed.interface";

export class LookupRegion extends NeoCom implements INamed {
    public name: string
    public systems: LookupSolarSystem[] = []

    constructor(values: Object = {}) {
        super(values)
        this.jsonClass = 'LookupRegion'
    }
    // -  I C O L L A B O R A T I O N
    public collaborate2View(): ICollaboration[] {
        if (this.isExpanded())
            return this.systems
        else
            return [this];
    }
    // - I N A M E D
    public getName(): string {
        return this.name
    }
    public addSystem(system: LookupSolarSystem): void {
        this.systems.push(system)
    }
    public getSystemsCount():number{
        return this.systems.length
    }
}
