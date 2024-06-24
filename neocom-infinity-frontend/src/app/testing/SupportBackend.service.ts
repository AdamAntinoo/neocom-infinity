// - CORE
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { map } from 'rxjs/operators';
// - HTTP PACKAGE
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
// - SERVICES
// - DOMAIN
import { environment } from '@env/environment';
import { NeoComResponse } from '@app/domain/dto/NeoComResponse.dto';
import { NeoComException } from '@innovative/domain/NeoComException';
import { ResponseTransformer } from 'neocom-domain/ResponseTransformer';
import { Corporation } from '@app/domain/Corporation.domain';
import { SupportAppStoreService } from './SupportAppStore.service';
import { Pilot } from '@app/domain/Pilot.domain';
import { IsolationService } from '@innovative/services/isolation.service';
import { AuthenticationStateResponse } from '@domain/dto/AuthenticationStateResponse.dto';
import { PlatformConstants } from '@env/PlatformConstants';
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';

@Injectable({
    providedIn: 'root'
})
export class SupportBackendService {
    private APIV1: string;
    private nextException: HttpErrorResponse
    private exceptionMap: Map<string, HttpErrorResponse> = new Map<string, HttpErrorResponse>()

    constructor(
        public isolation: IsolationService,
        protected appStoreService: SupportAppStoreService) {
        this.APIV1 = PlatformConstants.NEOCOM_V1;
    }

    // - E X C E P T I O N S
    public activateException(exceptionCode: string, apiTarget: string): void {
        this.nextException = new HttpErrorResponse({
            error: { error: exceptionCode }
        })
        this.exceptionMap.set(apiTarget, this.nextException)
    }

    public apiv1_ValidateAuthtenticationState(): Observable<AuthenticationStateResponse> {
        console.log(">[BackendService.apiv1_validateAuthenticationState]")
        // Check for exceptions
        const hit = this.exceptionMap.get('apiv1_validateAuthenticationState')
        console.log('-[hit]> ' + JSON.stringify(hit))
        if (hit) {
            console.log(">[BackendService.apiv1_validateAuthenticationState]>Throw error" + hit.error)
            return new Observable<AuthenticationStateResponse>((observer) => {
                throwError(hit)
            })
        } else
            return new Observable<AuthenticationStateResponse>((observer) => {
                observer.next(new AuthenticationStateResponse({
                    state: "valid"
                }));
                observer.complete()
            })
    }
    public apiv1_ValidateAuthorizationToken(code: string, state: string): Observable<AuthenticationStateResponse> {
        console.log(">[BackendService.apiv1_ValidateAuthorizationToken]> code: " + code);
        // Construct the request to call the backend.
        let request = 'server-name' + 'api-v1' + "/validateAuthorizationToken" +
            "/code/" + code +
            "/state/" + state +
            "/datasource/" + NeoComConstants.ESIDATASOURCE.toLowerCase();
        return Observable.create((observer) => {
            observer.next(new AuthenticationStateResponse(
                {
                    responseType: "ValidateAuthorizationTokenResponse",
                    jwtToken: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiYWNjb3VudE5hbWUiOiJBZGFtIEFudGlub28iLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkvMTIzIn0.VE261-Uzlsw8nH6JNox_DBVhrQY_BqR3P2Knc_DQmO-ejlHXiCNX3YPHd-pKK-bis_bxWq-lQxVEXd2vvhg0yQ",
                    credential: {
                        jsonClass: "Credential",
                        uniqueId: "tranquility/123",
                        accountId: 123,
                        accountName: "Adam Antinoo",
                        dataSource: "tranquility"
                    }
                }));
            observer.complete();
        });
    }
    public apiGetCorporationPublicData_v1(corporationId: number, transformer: ResponseTransformer): Observable<Corporation> {
        const request = this.APIV1 + "/corporations/" + corporationId;
        const corporationData = this.appStoreService.directAccessMockResource('corporations');
        return Observable.create((observer) => {
            console.log(">[BackendService.apiGetCorporationPublicData_v1]> Transformation: " +
                transformer.description);
            const response = transformer.transform(corporationData) as Corporation;
            observer.next(response);
            observer.complete();
        });
    }
    public apiGetPilotPublicData_v1(pilotId: number): Observable<Pilot> {
        const request = this.APIV1 + "/pilots/" + pilotId;
        const pilotData = this.appStoreService.directAccessMockResource('pilots');
        return Observable.create((observer) => {
            observer.next(new Pilot(pilotData));
            observer.complete();
        });
    }
}
