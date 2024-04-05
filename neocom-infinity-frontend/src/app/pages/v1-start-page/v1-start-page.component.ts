// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
// - SERVICES
import { BackendService } from '@app/services/backend.service';
import { AuthenticationStateResponse } from '@domain/dto/AuthenticationStateResponse.dto';
import { environment } from '@env/environment';
import { PlatformConstants } from '@env/PlatformConstants';
// - INNOVATIVE
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
import { IsolationService } from '@innovative/services/isolation.service';
import { env } from 'process';

@Component({
    selector: 'v1-start-page',
    templateUrl: './v1-start-page.component.html',
    styleUrls: ['./v1-start-page.component.scss']
})
export class V1StartPageComponent extends BackgroundEnabledComponent implements OnInit {
    public validating: boolean = true

    constructor(
        protected router: Router,
        protected isolationService: IsolationService,
        protected backendService: BackendService) {
        super()
    }
    /**
     * Called at page start.
     * Used to check if there is a valid session cookie. The validation is a call to a backend endpoint to validate session expiration and validation.
     * If the session is valid we move to the start dashboard.
     * If the session is not vald then we change the page to display the login button.
     */
    public ngOnInit(): void {
        this.backendConnections.push(this.backendService.apiv1_ValidateAuthtenticationState()
            .subscribe((response: AuthenticationStateResponse) => {
                if (response.state == PlatformConstants.VALID_SESSION_STATE) {
                    console.log('-[V1StartPageComponent.ngOnInit]>Valid session')
                    // Store the new tocken and credential
                    this.isolationService.setToSession(PlatformConstants.JWTTOKEN_KEY, response.getJwtToken())
                    this.isolationService.setToSession(PlatformConstants.CREDENTIAL_KEY, JSON.stringify(response.getCredential()))
                    this.validating = false
                    this.pageChange('/dashboard')
                } else {
                    console.log('-[V1StartPageComponent.ngOnInit]>Not Valid session')
                    this.validating = false
                }
            }, (error) => {
                console.log('-[V1StartPageComponent.ngOnInit.exception]> Error message: ' + JSON.stringify(error.error))
                setTimeout(() => this.validating = false, 1000)
            }))
    }
    public getLoginLink(): string {
        console.log('loginlink->' + environment.loginLink)
        return environment.loginLink
    }
    // public v2getLoginLink(): string {
    //     const link: string = environment.loginLinkData
    // }
    private pageChange(route: string): void {
        console.log('><[ngOnInit.pageChange]> Route: ' + route);
        this.router.navigate([route]);
    }
}
