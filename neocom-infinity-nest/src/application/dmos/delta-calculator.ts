import { assert } from "console";
import { AssetEsi } from "../../../test/acceptance/asset-esi";

export class DeltaCalculator {
  /**
   * Calculates a new asset list for Ore assets (other assets should be filtered out) the is the diffeence on assets from the initial adn the second.
   * - If the asset is on the initial and not on the second then the assets has been removed and should not be on the result list.
   * - If the asset id matches between asset lists then a new asset is calculated with the difference between the initial and the second.
   * - If the asset id only appears on the second list then this is a new asst and goes to the output list as is.
   * @param initialList the start asset list to be used as baseline.
   * @param secondList the new asset list to be used for delta calculation.
   * @returns the delta asset list resulting from the difference.
   */
  apply(initialList: AssetEsi[], secondList: AssetEsi[]): AssetEsi[] {
    let output: AssetEsi[] = [];
    secondList.forEach(asset => {
      let matches: AssetEsi[] = this.searchAssetId(asset.item_id, initialList);
      if (this.isEmpty(matches)) // The assets if not of the original list so it is new.
        output.push(this.getFirst(matches));
      else { // The assets ids match. Calculate the delta.
        output.push(this.calculateDelta(this.getFirst(matches), asset));
      }
    });
    return output;
  }
  private calculateDelta(start: AssetEsi, asset: AssetEsi): AssetEsi {
    return new AssetEsi().from(start).setQuantity(start.quantity - asset.quantity);
  }
  private getFirst(source: AssetEsi[]): AssetEsi {
    if (source.length == 0) return source[0];
  }
  private isEmpty(source: AssetEsi[]): boolean {
    return (!Array.isArray(source) || source.length == 0);
  }
  private searchAssetId(item_id: number, source: AssetEsi[]): AssetEsi[] {
    return source.filter(asset => asset.type_id == item_id)
  }
}
