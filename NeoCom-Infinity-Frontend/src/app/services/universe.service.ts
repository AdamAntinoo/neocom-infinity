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
import { UniverseHttpWrapper } from './universe.httpwrapper'
// - DOMAIN
import { ValidateAuthorizationTokenResponse } from '@app/domain/dto/ValidateAuthorizationTokenResponse'
import { Pilot } from '@app/domain/Pilot.domain'
import { Corporation } from '@app/domain/Corporation.domain'
import { ServerStatus } from '@app/domain/ServerStatus.domain'
import { Fitting } from '@app/domain/Fitting.domain'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer'
import { EsiType } from '@domain/esi/EsiType.esi'

@Injectable({
    providedIn: 'root'
})
export class UniverseService {
    protected UNIVERSEV1: string

    constructor(protected httpUniverseService: UniverseHttpWrapper) {
        this.UNIVERSEV1 = environment.serverName + environment.apiVersion1 + '/universe'
    }

    // - U N I V E R S E   A P I
    public apiv1_GetUniverseType(typeId: number): Observable<EsiType> {
        console.log(">[UniverseService.apiv1_GetUniverseType]> typeId: " + typeId)
        const request = this.UNIVERSEV1 + "/types/" + typeId
        return this.httpUniverseService.wrapHttpGETCall(request, headers)
            .pipe(map((data: any) => {
                return new EsiType(data)
            }))
    }
}
