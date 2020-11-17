import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClientWrapperService } from '@bit/innovative.innovative.innovative-core/dist/innovative-core/services/httpclientwrapper.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private DATAV1: string

  constructor(protected http: HttpClient) {
    this.DATAV1 = environment.dataLocation

   }
  public apiGetPlanetPIInformation () : Observable<any>{
    const request = this.DATAV1 + 'knownPlanetaryData.json'
    return this.wrapHttpRESOURCECall(request)
        // .pipe(map((data: any) => {
        //     // console.log(">[BackendService.apiIndustryGetFittingDefinition_v1]> Transformation: " +
        //     //     transformer.description)
        //     // const response = transformer.transform(data) as Fitting[]
        //     return data
        // }))
  }
      /**
     * Reads a JSON formatted resource. There is no specific convertion to a types class and so can be done on the caller method.
     * @param request the location of the resource file to be read. The resource starts on the /assets/properties location.
     */
    public wrapHttpRESOURCECall(request: string): Observable<any> {
      console.log("><[HttpClientWrapperService.wrapHttpRESOURCECall]> request: " + request);
      return this.http.get(request);
  }
}
