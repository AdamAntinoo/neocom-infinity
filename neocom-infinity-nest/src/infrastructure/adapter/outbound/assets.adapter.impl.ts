import { Injectable } from "@nestjs/common";
import { AssetEsi } from "src/application/domain/asset.esi";
import { AssetsPort } from "src/application/ports/esi.assets.port";

@Injectable()
export class AssetsAdapter implements AssetsPort {
  getCharacterAssets(characterId: number): AssetEsi[] {
    throw new Error("Method not implemented.");
  }
  // - C O I L S
  public apiv2_InventoryGetCoils(): Observable<Coil[]> {
    const request = this.INVENTORYAPIV2 + '/inventory/coils'
    const transformer: ResponseTransformer = new ResponseTransformer().setDescription('Transforms the list of Coils from the backend.')
      .setTransformation((entrydata: any): Coil[] => {
        const coilList: Coil[] = []
        if (Array.isArray(entrydata))
          for (let entry of entrydata)
            coilList.push(new Coil(entry))
        return coilList
      })
    let headers = new HttpHeaders()
    headers = headers.set('xApp-Api-Version', 'API v2');
    return this.httpService.wrapHttpGETCall(request, headers)
      .pipe(map((data: any) => {
        console.log(">[InventoryService.apiv2_InventoryGetCoils]> Transformation: " + transformer.description)
        return transformer.transform(data) as Coil[]
      }))
  }
  public apiGetPilotFittings_v1(pilotId: number, transformer: ResponseTransformer): Observable<Fitting[]> {
    const request = this.APIV1 + '/fittings/pilot/' + pilotId
    return this.httpService.wrapHttpGETCall(request)
      .pipe(map((data: any) => {
        console.log(">[BackendService.apiGetPilotFittings_v1]> Transformation: " +
          transformer.description)
        const response = transformer.transform(data) as Fitting[]
        return response
      }))
  }
  public apiEsiUniverseTypesData(typeId: number): Observable<UniverseType> {
    const request = this.ESIUNIVERSE + 'types/' + typeId + this.addEsiQueryParameters()
    return this.httpService.wrapHttpGETCall(request)
      .pipe(map((data: any) => {
        const response = new UniverseType(data)
        return response
      }))
  }
  private addEsiQueryParameters(): string {
    return '?datasource=tranquility&language=en-us'
  }
}
