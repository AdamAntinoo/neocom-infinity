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
import { BackendService } from "@app/services/backend.service";
import { BackendHttpWrapper } from '@app/services/backend.httpwrapper'
import { IsolationService } from '@innovative/services/isolation.service';
// - DOMAIN
import { ValidateAuthorizationTokenResponse } from '@app/domain/dto/ValidateAuthorizationTokenResponse'
import { Pilot } from '@app/domain/Pilot.domain'
import { Corporation } from '@app/domain/Corporation.domain'
import { ServerStatus } from '@app/domain/ServerStatus.domain'
import { Fitting } from '@app/domain/Fitting.domain'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer'
import { ProcessedBlueprint } from '../domain/V1ProcessedBlueprint.domain'

@Injectable({
    providedIn: 'root'
})
export class IndustryService extends BackendService {
    constructor(
        protected isolation: IsolationService,
        protected httpService: BackendHttpWrapper) {
        super(isolation, httpService)
    }
    // - I N D U S T R Y   A P I
    public apiv1_GetProcessedBlueprints(transformer: ResponseTransformer): Observable<ProcessedBlueprint[]> {
        const request = this.APIV1 + '/industry/manufacture/blueprints/costindex'
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                console.log('>[IndustryService.apiGetProcessedBlueprints_v1]> Transformation: ' + transformer.description)
                return transformer.transform(data) as ProcessedBlueprint[]
            }))
    }
}
