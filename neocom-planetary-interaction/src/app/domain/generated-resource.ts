import { PlanetaryDataRecord } from './planetary-data-record';
import { PlanetaryResource } from './planetary-resource';

export class GeneratedResource extends PlanetaryResource {
    public planet: PlanetaryDataRecord | undefined

    constructor(values: Object = {}) {
        super(values)
        Object.assign(this, values);
        this.jsonClass = 'GeneratedResource';
    }

    public setPlanet(planet: PlanetaryDataRecord): GeneratedResource {
        this.planet = planet
        return this
    }
    public getPlanetName(): string {
        if (this.planet) return this.planet.getPlanetName() + ' ' + this.planet.getPlanetSuffix()
        else return '-'
    }
    public getPlanetClass(): string {
        if (this.planet) return this.planet.getPlanetType().toUpperCase()
        else return '-'
    }
}
