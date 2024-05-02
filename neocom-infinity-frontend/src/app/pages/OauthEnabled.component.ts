import { environment } from '@env/environment'
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component'

export class OAuthEnabledComponent extends BackgroundEnabledComponent {
	public getLoginLink(): string {
		console.log('loginLinkData->' + environment.loginLinkData)
		return this.composeLoginLink()
	}
	public composeLoginLink(): string {
		let link: string = environment.loginLinkData.esi_host
		// - parameters
		link += 'response_type=' + environment.loginLinkData.parameters.response_type + '&'
		link += 'client_id=' + environment.loginLinkData.parameters.client_id + '&'
		link += 'state=' + environment.loginLinkData.parameters.state + '&'
		// - redirect
		link += 'redirect_uri=' + encodeURIComponent(environment.loginLinkData.callback_url) + '&'
		// - scope
		link += 'scope=' + encodeURIComponent(environment.loginLinkData.scope)

		return link
	}
}
