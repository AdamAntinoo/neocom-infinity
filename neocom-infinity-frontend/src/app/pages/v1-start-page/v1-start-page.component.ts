// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
// - SERVICES
import { BackendService } from '@app/services/backend.service';
import { SessionStateResponse } from '@domain/dto/SessionStateResponse.dto';
import { platformConstants } from '@env/platform-constants';
// - INNOVATIVE
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
import { IsolationService } from '@innovative/services/isolation.service';

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
        this.backendConnections.push(this.backendService.apiv1_ValidateAuthenticatedSession()
            .subscribe((response: SessionStateResponse) => {
                if (response.state == platformConstants.VALID_SESSION_STATE) this.pageChange('/dashboard')
                else this.validating = false
            }, (error) => {
                console.log('-[V1StartPageComponent.ngOnInit.exception]> Error message: ' + JSON.stringify(error.error))
                setTimeout(() => this.validating = false, 1000)
            }))
    }
    public getLoginLink(): string{
        return environment.loginLink
    }
    private pageChange(route: string): void {
        console.log('><[ngOnInit.pageChange]> Route: ' + route);
        this.router.navigate([route]);
    }
}
