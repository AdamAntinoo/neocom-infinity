import { Observable } from "rxjs";
import { AxiosResponse } from 'axios';
import { AssetEsi } from "../dto/ESIAsset.esi";

export interface AssetsPort {
    apiEsiCharacterAssetsData(pilotId: number): Promise<AssetEsi[]>;
}
