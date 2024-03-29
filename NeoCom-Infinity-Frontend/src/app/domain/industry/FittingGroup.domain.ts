// - DOMAIN
import { NeoCom } from '@domain/NeoCom.domain';

export class FittingGroup extends NeoCom {
    public id: string
    public name: string
    public weight: number = 100
    private contents: NeoCom[] = []

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = 'FittingGroup'
    }
    public getId(): string {
        return this.id
    }
    public setId(newId: string): FittingGroup {
        this.id = newId
        return this
    }
    public addContent(item: NeoCom): FittingGroup {
        this.contents.push(item)
        return this
    }
    public getName(): string {
        return this.name
    }
    public setName(name: string): FittingGroup {
        this.name = name
        return this
    }
    public setWeight(weight: number): FittingGroup {
        this.weight = weight
        return this
    }

    // -  I C O L L A B O R A T I O N
    public collaborate2View(): NeoCom[] {
        const collabration: NeoCom[] = [this]
        collabration.push(...this.contents)
        return collabration
    }
}
