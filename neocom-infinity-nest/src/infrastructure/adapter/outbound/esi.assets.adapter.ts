import { Injectable, Logger } from "@nestjs/common";
import { HttpService, } from '@nestjs/axios';
import { AxiosResponse, AxiosError } from 'axios';
import { Observable, catchError, firstValueFrom, map } from "rxjs";
import { AssetEsi } from "./domain/ESIAsset.esi";
import { AssetsPort } from "../../../../src/application/ports/esi.assets.port";
import { ResponseTransformer } from "./core/ResponseTransformer";
import { ESIHttpService } from "../../network/esi.httpservice";
import { HttpServiceInterface } from "../../network/http.service.interface";
import { response } from "express";

@Injectable()
export class ESIAssetsDataAdapter implements AssetsPort {
  private readonly logger = new Logger(ESIAssetsDataAdapter.name);
  private ESIDATA: string
  private ESIUNIVERSE: string

  constructor(private readonly httpService: HttpServiceInterface) {
    this.ESIDATA = "https://esi.evetech.net/latest/";
    this.ESIUNIVERSE = this.ESIDATA + 'assets/'
  }

  // - A S S E T S
  // public apiEsiCharacterAssetsDataResponse(pilotId: number, transformer: ResponseTransformer): Observable<AxiosResponse<AssetEsi[]>> {
  //   const request = this.ESIUNIVERSE + '/characters/' + pilotId + '/assets/' + this.addEsiQueryParameters();
  //   return this.httpService.get(request)
  //   // .pipe(map((data: any) => {
  //   //   console.log(">[BackendService.apiGetPilotFittings_v1]> Transformation: " + transformer.description)
  //   //   const response = transformer.transform(data) as AssetEsi[]
  //   //   return response
  //   // }));
  // }
  // async apiEsiCharacterAssetsDataPromise(pilotId: number): Promise<AssetEsi[]> {
  //   const request = this.ESIUNIVERSE + '/characters/' + pilotId + '/assets/' + this.addEsiQueryParameters();
  //   return await firstValueFrom(
  //     this.httpService.get<AssetEsi[]>(request).pipe(
  //       catchError((error: AxiosError) => {
  //         this.logger.error(error.response.data);
  //         throw 'An error happened!';
  //       }),
  //     ),
  //   );
  //   // return data;
  // }
  async apiEsiCharacterAssetsData(pilotId: number): Promise<AssetEsi[]> {
    const request = this.ESIUNIVERSE + '/characters/' + pilotId + '/assets/' + this.addEsiQueryParameters();
    const resp: Promise<AssetEsi[]> = this.httpService.wrapHttpGet<AssetEsi[]>(request);
    return resp;
  }
  private addEsiQueryParameters(): string {
    return '?datasource=tranquility&language=en-us'
  }
}
