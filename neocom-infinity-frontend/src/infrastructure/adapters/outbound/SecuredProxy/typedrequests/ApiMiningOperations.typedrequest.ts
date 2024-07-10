import { HttpHeaders } from '@angular/common/http'

import { BACKEND_LINKS } from '@env/PlatformConstants'
import { AngularBaseTypedRequest } from '@adapter/network/AngularBase.typedrequest'

export interface ApiMiningOperationsTypedRequestInput {
	token: string
}

export class ApiMiningOperationsTypedRequest extends AngularBaseTypedRequest {
	constructor(private readonly parameters: ApiMiningOperationsTypedRequestInput) {
		super()
	}

	protected prepareRequest(): void {
		this.request = BACKEND_LINKS.BACKEND_MINING_OPERATIONS_LINK
		console.log('prepare->request->' + this.request)
	}
	protected prepareOptions(): void {
		let headers: HttpHeaders = new HttpHeaders()
		headers = headers.append('Content-Type', 'application/json')
		headers = headers.append('Accept', 'application/json')
		headers = headers.append('Cache-Control', 'no-cache')
		headers = headers.append('X-NeoCom-Token', this.parameters.token)
		headers = headers.append('Cookie', 'ESI-DATA-SERVICES=' + this.parameters.token)
		this.options = {
			headers: headers,
			observe: 'body',
		}
	}
	protected prepareBody(): void {
		return undefined
	}
	public build(): ApiMiningOperationsTypedRequest {
		this.prepare()
		return this
	}
}
