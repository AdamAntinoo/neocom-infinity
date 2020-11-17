export class PlanetaryResource {
  public jsonClass: string;
  private resourceName: string| undefined
  private resourceLevel: number| undefined

  constructor(values: Object = {}) {
    Object.assign(this, values);
    this.jsonClass = 'PlanetaryResource';
  }
}
