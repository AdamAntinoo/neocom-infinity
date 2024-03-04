import { Injectable, Logger } from '@nestjs/common';
import { HttpSecureServiceInterface } from './http.secure.service.interface';
import { AssetEsi } from '../../../src/domain/dto/ESIAsset.esi';

@Injectable()
export class MockESIHttpSecureService implements HttpSecureServiceInterface {
  public wrapHttpGet<T>(request: string): Promise<T> {
    console.log('enter wrapHttpGet');
    if (request)
      return new Promise<AssetEsi[]>((resolve, reject) => {
        let sortedAssets = this.getCharacterAssets();
        const newsort = sortedAssets.sort((a, b) => a.item_id - b.item_id);
        resolve(sortedAssets.sort((a, b) => a.item_id - b.item_id));
      }) as Promise<T>;
  }
  private getCharacterAssets(): AssetEsi[] {
    console.log('enter getCharacterAssets');
    const assetlist = [
      new AssetEsi({
        is_singleton: false,
        item_id: 1012512980002,
        location_flag: 'AutoFit',
        location_id: 1035124094434,
        location_type: 'item',
        quantity: 1000,
        type_id: 1404,
      }),
      new AssetEsi({
        is_singleton: false,
        item_id: 1012451140001,
        location_flag: 'AutoFit',
        location_id: 1035124094434,
        location_type: 'item',
        quantity: 2000,
        type_id: 1403,
      }),
      new AssetEsi({
        is_singleton: false,
        item_id: 1012512980003,
        location_flag: 'AutoFit',
        location_id: 1035124094434,
        location_type: 'item',
        quantity: 3000,
        type_id: 1404,
      }),
    ];
    return assetlist;
  }
}
