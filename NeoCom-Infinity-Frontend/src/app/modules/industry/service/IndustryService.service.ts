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
import { IsolationService } from '@innovative/services/isolation.service';
import { BackendHttpWrapper } from '@app/services/backend.httpwrapper'
import { UniverseHttpWrapper } from '@app/services/universe.httpwrapper'
// - DOMAIN
import { ValidateAuthorizationTokenResponse } from '@app/domain/dto/ValidateAuthorizationTokenResponse'
import { Pilot } from '@app/domain/Pilot.domain'
import { Corporation } from '@app/domain/Corporation.domain'
import { ServerStatus } from '@app/domain/ServerStatus.domain'
import { Fitting } from '@app/domain/Fitting.domain'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer'
import { ProcessedBlueprint } from '../domain/V1ProcessedBlueprint.domain'
import { UniverseService } from '@app/services/universe.service'

@Injectable({
    providedIn: 'root'
})
export class IndustryService extends BackendService {
    constructor(
        protected httpUniverseService: UniverseHttpWrapper,
        protected httpService: BackendHttpWrapper,
        protected isolation: IsolationService) {
        super(httpUniverseService, httpService, isolation)
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
