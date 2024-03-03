import { World, setWorldConstructor } from '@cucumber/cucumber';
import { DeltaCalculator } from '../../src/application/dmos/delta-calculator';
import { PilotCard } from 'src/application/domain/pilot.card';
import { AssetsPort } from 'src/domain/ports/esi.assets.port';
import { AssetEsi } from 'src/domain/dto/ESIAsset.esi';
import { ESIAssetsDataAdapter } from 'src/infrastructure/adapter/outbound/esi.assets.adapter';
import { HttpSecureServiceInterface } from 'src/infrastructure/network/http.secure.service.interface';

export class NeoComWorld extends World {
  public deltaCalculator: DeltaCalculator;
  public assetlist:AssetEsi[]
  public initialList: AssetEsi[];
  public secondList: AssetEsi[];
  public output: AssetEsi[];
  public pilotCard: PilotCard;

  public httpSecureService: HttpSecureServiceInterface;
  public assetAdapter: ESIAssetsDataAdapter;

  public characterAssetsResponse: Promise<AssetEsi[]>;

  constructor(options) {
    super(options);
  }
}
setWorldConstructor(NeoComWorld);
