// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { OnDestroy } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import { Router } from '@angular/router'
import { environment } from '@env/environment'
import { Subscription } from 'rxjs'
import { throwError } from 'rxjs'
import addMinutes from 'date-fns/addMinutes'
// - SERVICES
import { AppStoreService } from '@app/services/appstore.service'
import { BackendService } from '@app/services/backend.service'
import { NeoComException } from '@innovative/domain/NeoComException'
// - DOMAIN
import { Credential } from '../../domain/Credential.domain'
import { IsolationService } from '@innovative/services/isolation.service'
import { neocom_constants } from '@app/platform/neocom-constants.platform'
import { ValidateAuthorizationTokenResponse } from '@app/domain/dto/ValidateAuthorizationTokenResponse'
import { ExceptionCatalog } from '@app/platform/ExceptionCatalog'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer'
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component'

@Component({
    selector: 'app-login-validation-page',
    templateUrl: './v1-login-validation-page.component.html',
    styleUrls: ['./v1-login-validation-page.component.scss']
})
export class V1LoginValidationPageComponent extends BackgroundEnabledComponent implements OnInit, OnDestroy {
    public validationException: any
    public paramCode: string
    private paramState: string
    private validateAuthorizationTokenSubscription: Subscription

    constructor(
        protected isolationService: IsolationService,
        protected appModelStore: AppStoreService,
        protected backendService: BackendService,
        protected route: ActivatedRoute,
        protected router: Router) {
            super()
         }

    public ngOnInit() {
        console.log('>[V1LoginValidationPageComponent.ngOnInit]')
        this.route.queryParams.subscribe(params => {
            this.paramCode = params['code']
            this.paramState = params['state']
            console.log('-[V1LoginValidationPageComponent.<ngOnInit>]> Query Parameter: ' + 'code=' + this.paramCode)
            console.log('-[V1LoginValidationPageComponent.<ngOnInit>]> Query Parameter: ' + 'state=' + this.paramState)
            if (this.parameterValidation()) {
                console.log('-[V1LoginValidationPageComponent.<ngOnInit>] Validation should be true')
                // Pass the authentication token to the backend to register the Credential and generate the JWT token.
                this.backendConnections.push(this.backendService.apiValidateAuthorizationToken_v1(
                    this.paramCode, this.paramState,
                    new ResponseTransformer().setDescription('Do response transformation to "ValidateAuthorizationTokenResponse"')
                        .setTransformation((data: any): ValidateAuthorizationTokenResponse => {
                            return new ValidateAuthorizationTokenResponse(data)
                        }))
                    .subscribe((response: ValidateAuthorizationTokenResponse) => {
                        const credential: Credential = new Credential(response.getCredential())
                        console.log('-[V1LoginValidationPageComponent.<ngOnInit>.apiValidateAuthorizationToken]> Credential: ' +
                            JSON.stringify(credential))
                            console.log('-[V1LoginValidationPageComponent.<ngOnInit>.apiValidateAuthorizationToken]> JWT: ' +
                            response.getJwtToken())
                        if (this.validateJWT(response.getJwtToken(), credential)) {
                            this.storeJWT(response.getJwtToken())
                            this.storeCredential(response.getCredential())
                            console.log('-[V1LoginValidationPageComponent.<ngOnInit>.apiValidateAuthorizationToken]> Routing to: /dashbaord')
                            this.router.navigate(['dashboard'])   // Jump to the Dashboard page if all complete.
                        } else {
                            // Jump to the repeat login page with some information.
                            console.log('-[V1LoginValidationPageComponent.<ngOnInit>.]> Repeat login')
                            this.appModelStore.setLastInterceptedException(ExceptionCatalog.JWT_TOKEN_INVALID)
                            this.router.navigate(['exceptionInfo']) // Jump to the exception information page to login again.
                        }
                    })
                )
            } else
                console.log('-[V1LoginValidationPageComponent.<ngOnInit>] Validation should be false')
        })
    }

    // - F U N C T I O N A L I T I E S
    /**
     * Validates that the page has received the correct paramters. Checks the content for the 'state' and that the
     * 'code' exists before allowing the page to continue to the backend endpoint call.
     */
    private parameterValidation(): boolean {
        this.validateState(this.paramState)
        if (this.appModelStore.isEmpty(this.paramCode))
            this.validationException = new NeoComException(
                { code: 400, message: 'The request does not have a mandatory query parameter.' }
            )
        if (null == this.validationException) return true
        else return false
    }
    private validateState(state2Check: string): void {
        console.log('-LoginValidationPageComponent.validateState')
        if (state2Check === environment.VALID_STATE) return
        this.validationException = new NeoComException(
            { code: 400, message: 'The request state does not match. Caller not verified.' }
        )
    }
    private validateJWT(jwtToken: string, credential: Credential): boolean {
        // Decode the JWT.
        const accountName = this.appModelStore.JWTDecode2AccountName(jwtToken)
        console.log('-[LoginValidationPageComponent.validateJWT]> accountName=' + accountName)
        const uniqueId = this.appModelStore.JWTDecode2UniqueId(jwtToken)
        console.log('-[LoginValidationPageComponent.validateJWT]> uniqueId=' + uniqueId)
        if (accountName != credential.getAccountName()) return false
        if (uniqueId != credential.getUniqueId()) return false
        return true
    }
    private storeJWT(jwtToken: string): boolean {
        this.isolationService.setToSession(neocom_constants.JWTTOKEN_KEY, jwtToken)
        this.isolationService.setToSession(neocom_constants.JWTTOKEN_EXPIRATION_TIME_KEY,
            addMinutes(Date.now(), 120))
        return true
    }
    private storeCredential(credential: Credential): void {
        this.isolationService.setToSession(neocom_constants.CREDENTIAL_KEY, JSON.stringify(credential))
    }
}
