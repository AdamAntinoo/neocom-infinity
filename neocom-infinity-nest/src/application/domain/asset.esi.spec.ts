import { AssetEsi } from './asset.esi';

describe('DOMAIN AssetEsi [Module: -]', () => {
  describe('constructor contract', () => {
    it('should be created', () => {
      const asset: AssetEsi = new AssetEsi();
      expect(asset).toBeDefined();
    });
    it('quantity should be updated', () => {
      const asset: AssetEsi = new AssetEsi();
      asset.quantity = 1000;
      expect(asset.quantity).toBe(1000);
      asset.setQuantity(2000);
      expect(asset.quantity).toBe(2000);
    });
  });
});
