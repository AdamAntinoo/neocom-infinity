// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
// - SERVICES
import { BackendService } from '@app/services/backend.service';
// - INNOVATIVE
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';

@Component({
    selector: 'v1-start-page',
    templateUrl: './v1-start-page.component.html',
    styleUrls: ['./v1-start-page.component.scss']
})
export class V1StartPageComponent extends BackgroundEnabledComponent implements OnInit {
    public validating: boolean = true
    constructor(
        protected router: Router,
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
            .subscribe(validated => {
                if (validated) this.pageChange('/dashboard')
                else this.validating = false
            }, (error) => {
                console.log('-[V1CoilsPanelComponent.downloadCoils.exception]> Error message: ' + JSON.stringify(error.error))
                setTimeout(() => this.validating = false, 1000)
            }))
    }
    private pageChange(route: string): void {
        console.log('><[V1FeatureButtonComponent.pageChange]> Route: ' + route);
        this.router.navigate([route]);
    }
}
