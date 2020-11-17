export class PlanetaryResource {
  public jsonClass:string
  private resourceName":string
  private resourceLevel: number

  constructor(values: Object = {}) {
    Object.assign(this, values);
    this.jsonClass = "PlanetaryDataRecord";
}

}
