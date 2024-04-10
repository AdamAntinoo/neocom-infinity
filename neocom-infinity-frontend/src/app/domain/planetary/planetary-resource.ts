import { NeoCom } from '@domain/NeoCom.domain';

export class PlanetaryResource extends NeoCom {
    private resourceId!: number
    private resourceName!: string
    private resourceLevel!: number
    private dependencies: PlanetaryResource[] = []

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values);
        this.jsonClass = 'PlanetaryResource';
    }

    // - G E T T E R S
    public getTypeId(): number {
        if (this.resourceId) return this.resourceId
        else return 34
    }
    public getName(): string {
        if (this.resourceName) return this.resourceName
        else return '-'
    }
    public getLevel(): number {
        if (this.resourceLevel) return this.resourceLevel
        else return 0.0
    }
    public getDependencies(): PlanetaryResource[] {
        return this.dependencies
    }
    public setLevel(level: number): PlanetaryResource {
        this.resourceLevel = level
        return this
    }
    public getURLIcon(): string {
        return 'https://image.eveonline.com/Type/' + this.resourceId + '_64.png'
    }
}
