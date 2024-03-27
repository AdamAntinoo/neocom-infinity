// - CORE
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { map } from 'rxjs/operators'
import { environment } from '@env/environment'
// - HTTP PACKAGE
import { HttpClient } from '@angular/common/http'
import { HttpErrorResponse } from '@angular/common/http'
import { HttpHeaders } from '@angular/common/http'
// - SERVICES
import { IsolationService } from '@app/platform/isolation.service'
import { HttpClientWrapperService } from './httpclientwrapper.service'
// - DOMAIN
import { ValidateAuthorizationTokenResponse } from '@app/domain/dto/ValidateAuthorizationTokenResponse'
import { Pilot } from '@app/domain/Pilot.domain'
import { ResponseTransformer } from './support/ResponseTransformer'
import { Corporation } from '@app/domain/Corporation.domain'
import { ServerStatus } from '@app/domain/ServerStatus.domain'
import { Fitting } from '@app/domain/Fitting.domain'

@Injectable({
    providedIn: 'root'
})
export class BackendService {
    private APIV1: string
    private APIV2: string

    constructor(
        protected isolation: IsolationService,
        protected httpService: HttpClientWrapperService) {
        this.APIV1 = environment.serverName + environment.apiVersion1
        this.APIV2 = environment.serverName + environment.apiVersion2
    }

    // - B A C K E N D - A P I
    public apiValidateAuthorizationToken_v1(code: string, state: string,
        transformer: ResponseTransformer): Observable<ValidateAuthorizationTokenResponse> {
        console.log(">[BackendService.apiValidateAuthorizationToken_v1]> code: " + code)
        // Construct the request to call the backend.
        const request = this.APIV1 + "/validateAuthorizationToken" +
            "?code=" + code +
            "&state=" + state +
            "&datasource=" + environment.ESIDataSource.toLowerCase()
        let headers = new HttpHeaders() // Additional mockup headers to apisimulation.
            .set('xapp-validation-code', code)
        return this.httpService.wrapHttpGETCall(request, headers)
            .pipe(map((data: any) => {
                console.log(">[BackendService.apiValidateAuthorizationToken_v1]> Transformation: " +
                    transformer.description)
                const response = transformer.transform(data) as ValidateAuthorizationTokenResponse
                return response
            }))
    }
    public apiGetServerInfo_v1(transformer: ResponseTransformer): Observable<ServerStatus> {
        const request = this.APIV1 + "/server/datasource/" + environment.ESIDataSource.toLowerCase()
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                console.log(">[BackendService.apiGetServerInfo_v1]> Transformation: " +
                    transformer.description)
                const response = transformer.transform(data) as ServerStatus
                return response
            }))
    }
    public apiGetCorporationPublicData_v1(corporationId: number, transformer: ResponseTransformer): Observable<Corporation> {
        const request = this.APIV1 + "/corporations/" + corporationId
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                console.log(">[BackendService.apiGetCorporationPublicData_v1]> Transformation: " +
                    transformer.description)
                const response = transformer.transform(data) as Corporation
                return response
            }))
    }
    public apiGetPilotPublicData_v1(pilotId: number): Observable<Pilot> {
        const request = this.APIV1 + "/pilots/" + pilotId
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                const response = new Pilot(data)
                return response
            }))
    }
    public apiGetPilotPublicData_v2(pilotId: number, transformer: ResponseTransformer): Observable<any> {
        const request = this.APIV2 + "/pilots/" + pilotId
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                const response = transformer.transform(data) as Pilot
                return response
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

    // - B A C K E N D   I N D U S T R Y
    public apiIndustryGetFittingDefinition_v1(fittingId: number, transformer: ResponseTransformer): Observable<any> {
        const request = this.APIV1 + '/industry/fittings/buildConfiguration/' + fittingId
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                // console.log(">[BackendService.apiIndustryGetFittingDefinition_v1]> Transformation: " +
                //     transformer.description)
                // const response = transformer.transform(data) as Fitting[]
                return data
            }))
    }
    public halIndustryGetFittingDefinition_v1(fittingId: number, transformer: ResponseTransformer): Observable<any> {
        const request = this.APIV1 + '/industry/fittings/buildConfiguration/' + fittingId
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                // console.log(">[BackendService.apiIndustryGetFittingDefinition_v1]> Transformation: " +
                //     transformer.description)
                // const response = transformer.transform(data) as Fitting[]
                return data
            }))
    }
}
