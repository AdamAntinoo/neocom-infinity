import { ITransformable } from "../core/ITransformable.interface"

export class V2MiningOperation implements ITransformable<V2MiningOperation>{
  public jsonClass: string = 'MiningOperation'
  public id?: string
  public date?: string
  public quantity?: number = 0
  public solarSystem?: string
  public typeId?: string

  constructor(fields: object = {}) {
    Object.assign(this, fields)
  }
  public transform(): V2MiningOperation {
    console.log('transform->no need transformation')
    return this
  }
}
