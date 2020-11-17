import { PlanetaryResource } from './planetary-resource'

export class PlanetaryDataRecord {
  public jsonClass:string
  private name: string
  private planetSuffix: string
  private planetType: string
  private planetTax": 1.0,
  private planetResourcws: PlanetaryResource[]

  constructor(values: Object = {}) {
    Object.assign(this, values);
    this.jsonClass = "PlanetaryDataRecord";
}

}
