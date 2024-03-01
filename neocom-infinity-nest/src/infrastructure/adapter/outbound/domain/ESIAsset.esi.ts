export class AssetEsi {
  public is_singleton: boolean;
  public item_id: number;
  public location_flag: string;
  public location_id: number;
  public location_type: string;
  public quantity: number;
  public type_id: number;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
  public setQuantity(newQuantity: number): AssetEsi {
    this.quantity = newQuantity;
    return this;
  }
}
