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
import { IsolationService } from '@innovative/services/isolation.service'
import { NeoComConstants } from '@app/platform/NeocomConstants.platform'
import { ExceptionCatalog } from '@app/platform/ExceptionCatalog'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer'
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component'
import { AuthenticationStateResponse } from '@domain/dto/AuthenticationStateResponse.dto'
import { NeoComCredential } from '@domain/NeoComCredential.domain'
import { PlatformConstants } from '@env/PlatformConstants'

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
                this.backendConnections.push(this.backendService.apiv1_ValidateAuthorizationToken(
                    this.paramCode, this.paramState,
                    new ResponseTransformer().setDescription('Do response transformation to "ValidateAuthorizationTokenResponse"')
                        .setTransformation((data: any): AuthenticationStateResponse => {
                            return new AuthenticationStateResponse(data)
                        }))
                    .subscribe((response: AuthenticationStateResponse) => {
                        const credential: NeoComCredential = new NeoComCredential(response.getCredential())
                        console.log('-[V1LoginValidationPageComponent.<ngOnInit>.apiValidateAuthorizationToken]> Credential: ' +
                            JSON.stringify(credential))
                        console.log('-[V1LoginValidationPageComponent.<ngOnInit>.apiValidateAuthorizationToken]> JWT: ' +
                            response.getJwtToken())
                        if (this.validateJWT(response.getJwtToken(), credential)) {
                            // Store the new tocken and credential
                            this.isolationService.setToSession(PlatformConstants.JWTTOKEN_KEY, response.getJwtToken())
                            this.isolationService.setToSession(PlatformConstants.CREDENTIAL_KEY, JSON.stringify(response.getCredential()))
                            console.log('-[V1LoginValidationPageComponent.<ngOnInit>.apiValidateAuthorizationToken]> Routing to: /dashbaord')
                            this.router.navigate(['dashboard'])   // Jump to the Dashboard page if all complete.
                        } else {
                            this.isolationService.deleteAllCookies()
                              this.router.navigate(['/start']) // Jump to the exception information page to login again.
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
        if (state2Check === NeoComConstants.VALID_STATE) return
        this.validationException = new NeoComException(
            { code: 400, message: 'The request state does not match. Caller not verified.' }
        )
    }
    private validateJWT(jwtToken: string, credential: NeoComCredential): boolean {
        // Decode the JWT.
        const accountName = this.appModelStore.JWTDecode2AccountName(jwtToken)
        console.log('-[LoginValidationPageComponent.validateJWT]> accountName=' + accountName)
        const uniqueId = this.appModelStore.JWTDecode2UniqueId(jwtToken)
        console.log('-[LoginValidationPageComponent.validateJWT]> uniqueId=' + uniqueId)
        if (accountName != credential.getAccountName()) return false
        if (uniqueId != credential.getUniqueId()) return false
        return true
    }
    // private storeJWT(jwtToken: string): boolean {
    //     this.isolationService.setToSession(PlatformConstants.JWTTOKEN_KEY, jwtToken)
    //     this.isolationService.setToSession(PlatformConstants.JWTTOKEN_EXPIRATION_TIME_KEY,
    //         addMinutes(Date.now(), 120))
    //     return true
    // }
    // private storeCredential(credential: Credential): void {
    //     this.isolationService.setToSession(PlatformConstants.CREDENTIAL_KEY, JSON.stringify(credential))
    // }
}
