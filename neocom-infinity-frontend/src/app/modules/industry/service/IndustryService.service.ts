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
import { ResponseTransformer } from 'neocom-domain/ResponseTransformer'
import { ProcessedBlueprint } from '../domain/V1ProcessedBlueprint.domain'
import { UniverseService } from '@app/services/universe.service'
import { HALResolver } from '@app/services/HALResolver.service'
import { V1ProcessedBlueprintSummary } from '../domain/V1ProcessedBlueprintSummary.domain'

@Injectable({
    providedIn: 'root'
})
export class IndustryService extends BackendService {
    protected INDUSTRYV1: string
    constructor(
        protected httpUniverseService: UniverseHttpWrapper,
        protected halResolver: HALResolver,
        protected httpService: BackendHttpWrapper,
        protected isolation: IsolationService) {
        super(httpUniverseService, halResolver, httpService, isolation)
        this.INDUSTRYV1 = this.APIV1 + '/industry'
    }
    // - I N D U S T R Y   A P I
    public apiv1_GetProcessedBlueprints(pilotId: number, transformer: ResponseTransformer): Observable<V1ProcessedBlueprintSummary[]> {
        const request = this.INDUSTRYV1 + '/pilots/' + pilotId + '/manufacture/blueprints'
        return this.httpService.wrapHttpGETCall(request)
            .pipe(map((data: any) => {
                console.log('>[IndustryService.apiGetProcessedBlueprints_v1]> Transformation: ' + transformer.description)
                return transformer.transform(data) as V1ProcessedBlueprintSummary[]
            }))
    }
}
