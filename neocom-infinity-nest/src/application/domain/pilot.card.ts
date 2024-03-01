export class PilotCard {
  public id: number;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }

  public setPilotId(newid: number) {
    this.id = newid;
  }
}
