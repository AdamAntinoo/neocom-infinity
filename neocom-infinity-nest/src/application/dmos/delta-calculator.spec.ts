import { AssetEsi } from "../domain/asset.esi";
import { DeltaCalculator } from "./delta-calculator";

const deltaCalculator: DeltaCalculator = new DeltaCalculator();

describe('DMO DeltaCalculator [Module: -]', () => {
  test('constructor contract', () => {
    const deltaCalculator: DeltaCalculator = new DeltaCalculator();
    expect(deltaCalculator).toBeDefined();
  });
  test('apply for an identical asset list > quantity = 0', () => {
    const t1List: AssetEsi[] = [];
    t1List.push(new AssetEsi({
      is_singleton: false,
      item_id: 100001,
      location_flag: 'AutoFit',
      location_id: 1035124094434,
      location_type: 'item',
      quantity: 1000,
      type_id: 101,
    }));
    const sut: AssetEsi[] = deltaCalculator.apply(t1List, t1List);
    console.info('DeltaCalculator.apply: ',JSON.stringify(sut));
    expect(sut.length).toBe(1);
    expect(sut[0].item_id).toBe(100001);
    expect(sut[0].quantity).toBe(0);
  });
  test('apply for a delete asset > second list', () => {
    const t1List: AssetEsi[] = [];
    t1List.push(new AssetEsi({
      is_singleton: false,
      item_id: 100001,
      location_flag: 'AutoFit',
      location_id: 1035124094434,
      location_type: 'item',
      quantity: 1000,
      type_id: 101,
    }));
    const t2List: AssetEsi[] = [];
    t2List.push(new AssetEsi({
      is_singleton: false,
      item_id: 100002,
      location_flag: 'AutoFit',
      location_id: 1035124094434,
      location_type: 'item',
      quantity: 1000,
      type_id: 101,
    }));
    const sut: AssetEsi[] = deltaCalculator.apply(t1List, t2List);
    console.info('DeltaCalculator2.apply: ',JSON.stringify(sut));
    expect(sut.length).toBe(1);
    expect(sut[0].item_id).toBe(100002);
    expect(sut[0].quantity).toBe(1000);
  });
  test('apply for an additional asset > updated queantity', () => {
    const t1List: AssetEsi[] = [];
    t1List.push(new AssetEsi({
      is_singleton: false,
      item_id: 100001,
      location_flag: 'AutoFit',
      location_id: 1035124094434,
      location_type: 'item',
      quantity: 1000,
      type_id: 101,
    }));
    const t2List: AssetEsi[] = [];
    t2List.push(new AssetEsi({
      is_singleton: false,
      item_id: 100001,
      location_flag: 'AutoFit',
      location_id: 1035124094434,
      location_type: 'item',
      quantity: 2000,
      type_id: 101,
    }));
    const sut: AssetEsi[] = deltaCalculator.apply(t1List, t2List);
    console.info('DeltaCalculator3.apply: ',JSON.stringify(sut));
    expect(sut.length).toBe(1);
    expect(sut[0].item_id).toBe(100001);
    expect(sut[0].quantity).toBe(1000);
  });
});
