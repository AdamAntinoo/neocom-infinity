import { AssetEsi } from '../domain/asset.esi';

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
    const output: AssetEsi[] = [];
    secondList.forEach((asset) => {
      const matches: AssetEsi[] = this.searchAssetId(
        asset.item_id,
        initialList,
      );
      // if (this.isEmpty(matches)) {
      if (matches.length == 0) {
        // The assets if not on the original list so it is new.
        output.push(asset);
      } else {
        // The assets ids match. Calculate the delta.
        output.push(this.calculateDelta(this.getFirst(matches), asset));
      }
    });
    return output;
  }
  private calculateDelta(start: AssetEsi, asset: AssetEsi): AssetEsi {
    let newAsset: AssetEsi = new AssetEsi(start);
    return newAsset.setQuantity(asset.quantity - start.quantity);
  }
  private getFirst(source: AssetEsi[]): AssetEsi {
    if (source.length > 0) return source[0];
  }
  private searchAssetId(item_id: number, source: AssetEsi[]): AssetEsi[] {
    return source.filter((asset) => asset.item_id == item_id);
  }
}
