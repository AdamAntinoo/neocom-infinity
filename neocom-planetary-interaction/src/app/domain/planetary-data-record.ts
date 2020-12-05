import { PlanetaryResource } from './planetary-resource';
import { v4 as uuidv4 } from 'uuid';

export class PlanetaryDataRecord {
    public jsonClass: string
    private id: string
    private name!: string
    private planetSuffix!: string
    private planetType!: string
    private planetTax: number = 10.0
    private planetResources: PlanetaryResource[] = []
    public selected: boolean = false

    constructor(values: Object = {}) {
        Object.assign(this, values);
        this.jsonClass = 'PlanetaryDataRecord'
        this.decode()
        this.id= uuidv4()
    }
    private decode(): void {
        if (null != this.planetResources) {
            const resources: PlanetaryResource[] = []
            for (let index = 0; index < this.planetResources.length; index++) {
                const element = this.planetResources[index];
                resources.push(new PlanetaryResource(element))
            }
            this.planetResources = resources
        }
    }

    public select(): void {
        this.selected = true
    }
    public deselect(): void {
        this.selected = false
    }
    public toggleSelect(): void {
        this.selected = !this.selected
    }
    public getPlanetName(): string {
        if (this.name) return this.name
        else return '-'
    }
    public getPlanetSuffix(): string {
        if (this.planetSuffix) return this.planetSuffix
        else return '-'
    }
    public getPlanetType(): string {
        if (this.planetType) return this.planetType
        else return '-'
    }
    public getResources(): PlanetaryResource[] {
        return this.planetResources
    }
    public getResource(name: string): PlanetaryResource | undefined {
        // const found : boolean = false
        for (let resource of this.planetResources) {
            if (resource.getName() == name) return resource
        }
        return undefined
    }
    public isEqual(target: PlanetaryDataRecord | undefined): boolean {
        if (target) {
            if (this.getPlanetType() != target.getPlanetType()) return false
            if (this.getPlanetSuffix() != target.getPlanetSuffix()) return false
            if (this.getPlanetType() != target.getPlanetType()) return false
        }
        return true
    }
}
