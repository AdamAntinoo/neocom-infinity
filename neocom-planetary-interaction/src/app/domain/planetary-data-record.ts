import { PlanetaryResource } from './planetary-resource';

export class PlanetaryDataRecord {
  public jsonClass: string | undefined
  private name: string | undefined
  private planetSuffix: string | undefined
  private planetType: string | undefined
  private planetTax: number = 10.0
  private planetResources: PlanetaryResource[] = []

  constructor(values: Object = {}) {
    Object.assign(this, values);
    this.jsonClass = 'PlanetaryDataRecord'
    this.decode()
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
}
