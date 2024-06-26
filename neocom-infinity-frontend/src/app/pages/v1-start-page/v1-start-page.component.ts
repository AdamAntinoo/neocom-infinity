// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { Router } from '@angular/router'
// - SERVICES
import { BackendService } from '@app/services/backend.service'
import { AuthenticationStateResponse } from '@domain/dto/AuthenticationStateResponse.dto'
import { PlatformConstants, STORAGE_LINKS } from '@env/PlatformConstants'
// - INNOVATIVE
import { BackgroundOauthEnabledComponent } from '@innovative/components/background-enabled/background-oauth-enabled.component'
import { IsolationService } from '@innovative/services/isolation.service'

@Component({
	selector: 'v1-start-page',
	templateUrl: './v1-start-page.component.html',
	styleUrls: ['./v1-start-page.component.scss'],
})
export class V1StartPageComponent extends BackgroundOauthEnabledComponent implements OnInit {
	public validating: boolean = true

	constructor(protected router: Router, protected isolationService: IsolationService, protected backendService: BackendService) {
		super()
	}
	/**
	 * Called at page start.
	 * Used to check if there is a valid session cookie. The validation is a call to a backend endpoint to validate session expiration and validation.
	 * If the session is valid we move to the start dashboard.
	 * If the session is not vald then we change the page to display the login button.
	 */
	public ngOnInit(): void {
		this.backendConnections.push(
			this.backendService.apiv1_ValidateAuthtenticationState().subscribe(
				(response: AuthenticationStateResponse) => {
					if (response.state == PlatformConstants.VALID_SESSION_STATE) {
						console.log('-[V1StartPageComponent.ngOnInit]>Valid session')
						// Store the new tocken and credential
						this.isolationService.setToSession(STORAGE_LINKS.JWTTOKEN_KEY, response.getJwtToken())
						this.isolationService.setToSession(STORAGE_LINKS.CREDENTIAL_KEY, JSON.stringify(response.getCredential()))
						this.validating = false
						this.pageChange('/dashboard')
					} else {
						console.log('-[V1StartPageComponent.ngOnInit]>Not Valid session')
						this.validating = false
					}
				},
				error => {
					console.log('-[V1StartPageComponent.ngOnInit.exception]> Error message: ' + JSON.stringify(error.error))
					setTimeout(() => (this.validating = false), 1000)
				},
			),
		)
	}
	private pageChange(route: string): void {
		console.log('><[ngOnInit.pageChange]> Route: ' + route)
		this.router.navigate([route])
	}
}
