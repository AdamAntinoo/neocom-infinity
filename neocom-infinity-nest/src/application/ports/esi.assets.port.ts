import { Observable } from "rxjs";
import { AxiosResponse } from 'axios';
import { AssetEsi } from "../domain/asset.esi";
import { ResponseTransformer } from "src/infrastructure/adapter/outbound/core/ResponseTransformer";

export interface AssetsPort {
  apiEsiCharacterAssetsData(pilotId: number): Promise<AssetEsi[]>;
}
