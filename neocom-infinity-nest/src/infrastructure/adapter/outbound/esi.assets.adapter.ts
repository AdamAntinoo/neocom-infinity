import { Injectable, Logger } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { AxiosResponse, AxiosError } from 'axios';
import { Observable, catchError, firstValueFrom, map } from 'rxjs';
import { AssetEsi } from '../../../domain/dto/ESIAsset.esi';
import { AssetsPort } from '../../../domain/ports/esi.assets.port';
import { ResponseTransformer } from './core/ResponseTransformer';
import { ESISecureDataServiceAdapter } from '../../network/esi.secure.dataservice.adapter';
import { HttpSecureServiceInterface } from '../../network/http.secure.service.interface';
import { response } from 'express';

@Injectable()
export class ESIAssetsDataAdapter implements AssetsPort {
  private readonly logger = new Logger(ESIAssetsDataAdapter.name);
  private ESIDATA: string;
  private ESIUNIVERSE: string;

  constructor(private readonly httpService: HttpSecureServiceInterface) {
    this.ESIDATA = 'https://esi.evetech.net/latest/';
    this.ESIUNIVERSE = this.ESIDATA + 'assets/';
  }

  // - A S S E T S
  public async apiEsiCharacterAssetsData(pilotId: number): Promise<AssetEsi[]> {
    const request =
      this.ESIUNIVERSE +
      '/characters/' +
      pilotId +
      '/assets/' +
      this.addEsiQueryParameters();
    const resp: Promise<AssetEsi[]> =
      this.httpService.wrapHttpGet<AssetEsi[]>(request);
    return resp;
  }
  private addEsiQueryParameters(): string {
    return '?datasource=tranquility&language=en-us';
  }
}
