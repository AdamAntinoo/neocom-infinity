// - DOMAIN
import { ESIUniverseDataService } from '@app/services/ESIUniverseData.service';
import { UniversePlanet } from './UniversePlanet.esi';

export class UniverseSystem {
    private planets: any[]
    private planetIds: number[] = []
    private planetData: UniversePlanet[] = []

    constructor(values: Object = {}) {
        Object.assign(this, values);
        this.decode()
    }
    /**
     * THis method is to reconstruct dependencies when them depend on external services to retrieve the data to be expanded.
     * @param esiDataService The service to use to recover the detailed data.
     */
    public postConstruct(esiDataService: ESIUniverseDataService) {
        for (let identifier of this.planetIds) {
            this.planetData.push(new UniversePlanet(esiDataService.apiEsiUniversePlanetsData(identifier)))
        }
    }
    private decode(): void {
        // Process data to get the list of planet ids.
        if (this.planets) {
            for (let planetEntry of this.planets) {
                const planetId = planetEntry['planet_id']
                if (planetId) this.planetIds.push(planetId)
            }
        }
    }

    // - G E T T E R S
    public getPlanets(): UniversePlanet[] {
        return this.planetData
    }
}
