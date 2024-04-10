// - CORE
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';
// - ENVIRONMENT
import { environment } from '@env/environment';
// - ROUTER
import { Router } from '@angular/router';
// - SERVICES
import { BackendService } from '@app/services/backend.service';
import { ActiveCacheWrapper } from '@app/modules/shared/support/ActiveCacheWrapper';
// - DOMAIN
import { Credential } from '../domain/core/Credential.domain';
import { IsolationService } from '@innovative/services/isolation.service';
import { NeoComException } from '@innovative/domain/NeoComException';
import { ExceptionCatalog } from '@app/platform/ExceptionCatalog';
import { Corporation } from '@app/domain/Corporation.domain';
import { CorporationDataResponse } from '@app/domain/dto/CorporationDataResponse.dto';
import { Pilot } from '@app/domain/Pilot.domain';
import { Fitting } from '@app/domain/Fitting.domain';
import { AppCoreStoreService } from '@innovative/services/AppCoreStoreService.service';
import { BackendHttpWrapper } from './backend.httpwrapper';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
import { PlatformConstants } from '@env/PlatformConstants';
import { ExceptionCodes } from '@app/platform/ExceptionCodes';
import { NeoComCredential } from '@domain/NeoComCredential.domain';

@Injectable({
    providedIn: 'root'
})
export class AppStoreService extends AppCoreStoreService {
    // - S T O R E   D A T A   S E C T I O N
    private corporationActiveCache: ActiveCacheWrapper<Corporation>;
    private pilotActiveCache: ActiveCacheWrapper<Pilot>;

    private lastInterceptedException: NeoComException;

    constructor(
        protected router: Router,
        protected isolationService: IsolationService,
        protected backendService: BackendService,
        protected httpService: BackendHttpWrapper) {
        super()
    }
    // - P I L O T
    /**
    * @throws NeoComException(ExceptionCodes.AUTHENTICATION_EXCEPTION)
    */
    public getPilotId(): number {
        return this.getCredential().getAccountId()
    }
    /**
    * @throws NeoComException(ExceptionCodes.AUTHENTICATION_EXCEPTION)
    */
    private getCredential(): NeoComCredential {
        const credentialJson = this.isolationService.getFromSession(PlatformConstants.CREDENTIAL_KEY)
        console.log('Dashboard.getCredential>Credential at session: ' + credentialJson)
        if (null == credentialJson)
            throw new NeoComException()
                .withCode(ExceptionCodes.AUTHENTICATION_EXCEPTION)
                .withTitle('Rendering Dashboard Page. No Credential Found.')
                .withMessage('Unable to display Pilot data. There is no credential available to access data.')
                .withCause('Unexpected Exception. At this point then should exist a local session valid credential.')
        else {
            const credential = new NeoComCredential(JSON.parse(credentialJson))
            return credential
        }
    }



    // - R O U T I N G
    /** @deprecated */
    public route2Destination(page: string): void {
        this.router.navigate([page]);
    }

    // - S T O R E   D A T A   D O W N L O A D E R S
    // private downloadCorporation(corporationId: number): Observable<Corporation> {
    //     console.log('-[AppStoreService.downloadCorporation]> Starting to download corporation id: ' + corporationId);
    //     return this.backendService.apiGetCorporationPublicData_v1(corporationId,
    //         new ResponseTransformer().setDescription('Do response transformation to "Corporation".')
    //             .setTransformation((data: any): Corporation => {
    //                 return new Corporation(data);
    //             }))
    //         .pipe(map((corporation: Corporation) => {
    //             return corporation;
    //         }));
    // }
    // private downloadPilot(pilotId: number): Observable<Pilot> {
    //     return this.backendService.apiGetPilotPublicData_v1(pilotId)
    //         .pipe(map((response: Pilot) => {
    //             let pilot = response;
    //             return pilot;
    //         }));
    // }

    // - G L O B A L   S T O R E
    // /** @deprecated */
    // public accessCredential(): Credential {
    //     return this.getCredential();
    // }
    /** @deprecated */
    public getCorporationIdentifier(): number {
        return this.getCredential().getCorporationId();
    }
    /** @deprecated */
    public getPilotIdentifier(): number {
        return this.getCredential().getAccountId();
    }
    /** @deprecated */
    public getLastInterceptedException(): NeoComException {
        return this.lastInterceptedException;
    }
    /** @deprecated */
    public setLastInterceptedException(exception: NeoComException): void {
        this.lastInterceptedException = exception;
    }

    // - S T O R E   A C C E S S   S E C T I O N
    /**
     * Resets and clears the cached stored contents so on next login we should reload all data.
     */
    /** @deprecated */
    public clearStore(): void {
        // Clear dynamic caches.
        this.corporationActiveCache.clear();
    }
    // - C O R P O R A T I O N
    /** @deprecated */
    public accessCorporation(): Observable<Corporation | Corporation[]> {
        return this.corporationActiveCache.accessData();
    }
    // - P I L O T
    /** @deprecated */
    public accessPilot(): Observable<Pilot | Pilot[]> {
        return this.pilotActiveCache.accessData();
    }
    /** @deprecated */
    public accessPilotFittings(transformer: ResponseTransformer): Observable<Fitting | Fitting[]> {
        let pilotId = this.getCredential().getAccountId();
        return this.backendService.apiGetPilotFittings_v1(pilotId, transformer);
    }

    // - G L O B A L   S U P P O R T   M E T H O D S
    public isEmpty(target?: any): boolean {
        if (null == target) return true;
        if (Object.keys(target).length == 0) return true;
        if (target.length == 0) return true;
        return false;
    }
    public accessProperties(propertyName: string): Observable<any> {
        console.log("><[AppStoreServiceDefinitions.propertiesTreatments]");
        // Construct the request to call the backend.
        let request = '/assets/properties/' + propertyName + '.json';
        return this.httpService.wrapHttpRESOURCECall(request)
            .pipe(data => {
                return data as any;
            });
    }


    // - J W T   D E C O D E
    public JWTDecode2AccountName(codedToken: string): string {
        const token = this.isolationService.JWTDecode(codedToken);
        return token["accountName"];
    }
    public JWTDecode2UniqueId(codedToken: string): string {
        const token = this.isolationService.JWTDecode(codedToken);
        return token["uniqueId"];
    }
}
