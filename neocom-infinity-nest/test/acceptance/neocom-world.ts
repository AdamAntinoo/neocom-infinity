import { setWorldConstructor } from '@cucumber/cucumber';
import { DeltaCalculator } from "../../src/application/dmos/delta-calculator";

export class NeoComWorld {
  public deltaCalculator = new DeltaCalculator();
  public initialList = [];
  public secindList = [];
}
// setWorldConstructor(NeoComWorld);
