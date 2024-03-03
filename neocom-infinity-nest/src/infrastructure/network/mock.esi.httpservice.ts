import { Injectable, Logger } from "@nestjs/common";
import { HttpService, } from '@nestjs/axios';
import { AxiosResponse, AxiosError } from 'axios';
import { AssetEsi } from "../../application/domain/asset.esi";
import { HttpServiceInterface } from "./http.service.interface";

@Injectable()
export class MockESIHttpService implements HttpServiceInterface {
  public wrapHttpGet<T>(request: string): Promise<T> {
    if (request)
      return new Promise<AssetEsi[]>((resolve, reject) => {
        resolve(this.getCharacterAssets());
      }) as Promise<T>
  }
  private getCharacterAssets(): AssetEsi[] {
    const assetlist = [
      new AssetEsi({
        is_singleton: false,
        item_id: 100001,
        location_flag: 'AutoFit',
        location_id: 1035124094434,
        location_type: 'item',
        quantity: 1000,
        type_id: 101,
      }),
      new AssetEsi({
        is_singleton: false,
        item_id: 100002,
        location_flag: 'AutoFit',
        location_id: 1035124094434,
        location_type: 'item',
        quantity: 2000,
        type_id: 102,
      }),
      new AssetEsi({
        is_singleton: false,
        item_id: 100003,
        location_flag: 'AutoFit',
        location_id: 1035124094434,
        location_type: 'item',
        quantity: 3000,
        type_id: 103,
      }),
    ];
    return assetlist;
  }
}
