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
import { UniverseService } from './universe.service'
import { IsolationService } from '@innovative/services/isolation.service';
import { BackendHttpWrapper } from './backend.httpwrapper'
import { UniverseHttpWrapper } from './universe.httpwrapper'
// - DOMAIN
import { Pilot } from '@app/domain/Pilot.domain'
import { Corporation } from '@app/domain/Corporation.domain'
import { ServerStatus } from '@domain/esi/ServerStatus.domain'
import { Fitting } from '@app/domain/Fitting.domain'
import { ResponseTransformer } from 'neocom-domain/ResponseTransformer'
import { PilotV2 } from '@domain/character/PilotV2.domain'
import { PublicService } from './public.service'
import { HALResolver } from './HALResolver.service'
import { platformBrowser } from '@angular/platform-browser'
import { PlatformConstants } from '@env/PlatformConstants'
import { AuthenticationStateResponse } from '@domain/dto/AuthenticationStateResponse.dto'
import { NeoComConstants } from '@app/platform/NeocomConstants.platform'

@Injectable({
    providedIn: 'root'
})
export class BackendService extends PublicService {
    protected APIV1: string
    protected APIV2: string

    constructor(
        protected httpUniverseService: UniverseHttpWrapper,
        protected halResolver: HALResolver,
        protected httpService: BackendHttpWrapper,
        protected isolation: IsolationService) {
        super(httpUniverseService, halResolver)
        this.APIV1 =  PlatformConstants.NEOCOM_V1
        this.APIV2 = PlatformConstants.NEOCOM_V2
    }

    // - B A C K E N D - A P I
    public apiv1_ValidateAuthtenticationState(): Observable<AuthenticationStateResponse> {
        console.log('step 02')
        const request = this.APIV1 + "/validateAuthenticationState"
        let headers = new HttpHeaders() // Additional headers for this authentication varification call.
        headers = headers.set('xApp-Authentication-Check', 'pilot')
        return this.httpService.wrapHttpGETCall(request, headers)
            .pipe(map((data: any) => {
                console.log('enteriing GET pipe map')
                return new AuthenticationStateResponse(data)
            }))
    }
    public apiv1_ValidateAuthorizationToken(code: string, state: string,
        transformer: ResponseTransformer): Observable<AuthenticationStateResponse> {
        console.log(">[BackendService.apiv1_ValidateAuthorizationToken]> code: " + code)
        // Construct the request to call the backend.
        const request = this.APIV1 + "/validateAuthorizationToken" +
            "?code=" + code +
            "&state=" + state +
            "&datasource=" + NeoComConstants.ESIDATASOURCE.toLowerCase()
        let headers = new HttpHeaders() // Additional mockup headers to apisimulation.
        headers = headers.set('xapp-validation-code', code)
        return this.httpService.wrapHttpGETCall(request, headers)
            .pipe(map((data: any) => {
                console.log(">[BackendService.apiv1_ValidateAuthorizationToken]> Transformation: " +
                    transformer.description)
                const response = transformer.transform(data) as AuthenticationStateResponse
                return response
            }))
    }
    public apiv1_GetPilotData(pilotId: number): Observable<PilotV2> {
        const request = this.APIV1 + "/pilots/" + pilotId
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                return new PilotV2(data)
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
    public apiv2_GetPilotPublicData(pilotId: number, transformer: ResponseTransformer): Observable<PilotV2> {
        const request = this.APIV2 + "/pilots/" + pilotId
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                return transformer.transform(data) as PilotV2
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
