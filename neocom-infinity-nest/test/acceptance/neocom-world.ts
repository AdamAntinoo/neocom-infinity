import { World, setWorldConstructor } from '@cucumber/cucumber';
import { DeltaCalculator } from "../../src/application/dmos/delta-calculator";
import { AssetEsi } from 'src/application/domain/asset.esi';
import { PilotCard } from 'src/application/domain/pilot.card';
import { AssetsPort } from 'src/application/ports/esi.assets.port';

export class NeoComWorld extends World {
  public deltaCalculator: DeltaCalculator;
  public initialList: AssetEsi[];
  public secondList: AssetEsi[];
  public output: AssetEsi[];
  public pilotCard: PilotCard;
  public assetAdapter:AssetsPort;

  constructor(options) {
    super(options);
  }
}
setWorldConstructor(NeoComWorld);
