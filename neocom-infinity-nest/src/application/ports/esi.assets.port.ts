import { AssetEsi } from "../domain/asset.esi";

export interface AssetsPort {
  getCharacterAssets(characterId: number): AssetEsi[];
}
