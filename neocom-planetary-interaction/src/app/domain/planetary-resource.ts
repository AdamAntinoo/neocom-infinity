export class PlanetaryResource {
    public jsonClass: string;
    private resourceName: string | undefined
    private resourceLevel: number | undefined
    private dependencies: PlanetaryResource[] = []

    constructor(values: Object = {}) {
        Object.assign(this, values);
        this.jsonClass = 'PlanetaryResource';
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
}
