import { HttpHeaders } from '@angular/common/http'

import { BACKEND_LINKS } from '@env/PlatformConstants'
import { AngularBaseTypedRequest } from '@adapter/network/AngularBase.typedrequest'

export interface BlueprintsTypedRequestParameters {
	token
}
export class ApiBlueprintsTypedRequest extends AngularBaseTypedRequest {
	constructor(
		private readonly parameters: BlueprintsTypedRequestParameters,
	) {
		super()
	}

    protected prepareRequest(): void {
		this.request = BACKEND_LINKS.BACKEND_BLUEPRINTS_LINK + '-session-id'
		console.log('prepare->request->' + this.request)
	}
	protected prepareOptions(): void {
		let headers: HttpHeaders = new HttpHeaders()
		headers = headers.append('Content-Type', 'application/json')
		headers = headers.append('Accept', 'application/json')
		headers = headers.append('Cache-Control', 'no-cache')
		headers = headers.append('X-NeoCom-Token', this.parameters.token)
		this.options = {
			headers: headers,
            observe: 'body'
		}
	}
	protected prepareBody(): void {
		throw new Error('Method not implemented.')
	}
    public build(): ApiBlueprintsTypedRequest {
		this.prepare()
		return this
	}
}
