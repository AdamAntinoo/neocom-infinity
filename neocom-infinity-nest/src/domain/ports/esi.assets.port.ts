import { Observable } from "rxjs";
import { AxiosResponse } from 'axios';
import { ResponseTransformer } from "src/infrastructure/adapter/outbound/core/ResponseTransformer";
import { AssetEsi } from "../dto/ESIAsset.esi";

export interface AssetsPort {
  apiEsiCharacterAssetsData(pilotId: number): Promise<AssetEsi[]>;
}
