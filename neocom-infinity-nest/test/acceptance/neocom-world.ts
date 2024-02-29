import { World, setWorldConstructor } from '@cucumber/cucumber';
import { DeltaCalculator } from "../../src/application/dmos/delta-calculator";
import { AssetEsi } from 'src/application/domain/asset-esi';

export class NeoComWorld extends World {
  public deltaCalculator = new DeltaCalculator();
  public initialList: AssetEsi[];
  public secondList: AssetEsi[];
  public output: AssetEsi[];

  constructor(options) {
    super(options);
  }
}
setWorldConstructor(NeoComWorld);
