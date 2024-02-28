export class AssetEsi {
  public is_singleton: boolean;
  public item_id: number;
  public location_flag: string;
  public location_id: number;
  public location_type: string;
  public quantity: number;
  public type_id: number;

  public from(start: AssetEsi): AssetEsi {
    this.is_singleton = start.is_singleton;
    this.item_id = start.item_id;
    this.location_flag = start.location_flag;
    this.location_id = start.location_id;
    this.location_type = start.location_type;
    this.quantity = start.quantity;
    this.type_id = start.type_id;
    return this;
  }
  public setQuantity(newQuantity: number): AssetEsi {
    this.quantity = newQuantity;
    return this;
  }
}
