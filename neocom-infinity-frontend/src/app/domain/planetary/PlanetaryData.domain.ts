import { NeoCom } from '@domain/NeoCom.domain';
import { PlanetaryResource } from './planetary-resource';
import { v4 as uuidv4 } from 'uuid';
import { PlanetaryDataService } from '@app/modules/planetary/service/PlanetaryData.service';

export class PlanetaryData extends NeoCom {
    private id: string
    private planetId: number
    private name!: string
    private planetSuffix!: string
    private planetType!: string
    private planetTax: number = 10.0
    private planetResourceLevels: number[]
    private planetResources: PlanetaryResource[] = []

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values);
        this.jsonClass = 'PlanetaryData'
        this.transform()
        this.id = uuidv4()
    }
    public transform(planetaryService?: PlanetaryDataService): PlanetaryData {
        if (planetaryService)
            if (this.planetResourceLevels) {
                const transformedResources: PlanetaryResource[] = []
                for (let index = 0; index < this.planetResourceLevels.length; index++) {
                    const level = this.planetResourceLevels[index]
                    const targetResource = planetaryService.getPlanetResource4PlanetType(this.planetType, index)
                    if (targetResource) {
                        const resource = new PlanetaryResource(targetResource)
                        console.log('-[]>Transformation: ' + this.planetType + ' - ' + level)
                        if (resource) this.planetResources.push(resource.setLevel(level))
                    }
                }
            }
        return this
    }

    // - G E T T E R S
    public getPlanetId(): number {
        return this.planetId
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
    public getPlanetResources(): PlanetaryResource[] {
        return this.planetResources
    }
    public getResource(name: string): PlanetaryResource {
        for (let resource of this.planetResources) {
            if (resource.getName() == name) return resource
        }
        return undefined
    }
    public isEqual(target: PlanetaryData | undefined): boolean {
        if (target) {
            if (this.getPlanetType() != target.getPlanetType()) return false
            if (this.getPlanetSuffix() != target.getPlanetSuffix()) return false
            if (this.getPlanetName() != target.getPlanetName()) return false
        }
        return true
    }
}
