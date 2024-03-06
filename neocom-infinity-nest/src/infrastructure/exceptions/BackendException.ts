export class BackendException {
  public readonly message: string;

  constructor(fields: object = {}) {
    Object.assign(this, fields);
  }
}
