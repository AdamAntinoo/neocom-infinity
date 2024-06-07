import { HttpHeaders } from '@angular/common/http'

import { BACKEND_LINKS } from '@env/PlatformConstants'
import { AngularBaseTypedRequest } from '@adapter/network/AngularBase.typedrequest'
import { ESI_CONSTANTS } from 'neocom-domain'
import { SecureUseCaseInput } from '@app/use-cases/ISecureUseCaseInput.interface'

export interface ProcessedBlueprintsTypedRequestParameters {
	token: string
    pilotId:number
}
export class ApiProcessedBlueprintsTypedRequest extends AngularBaseTypedRequest {
    public requestAge:number = 3600
	constructor(
		private readonly parameters: ProcessedBlueprintsTypedRequestParameters,
	) {
		super()
	}

    protected prepareRequest(): void {
        const pilotId : number =3
		this.request = BACKEND_LINKS.BACKEND_NEOCOM_V1 +BACKEND_LINKS.BACKEND_PROCESSED_BLUEPRINTS_LINK_S1+this.parameters.pilotId+BACKEND_LINKS.BACKEND_PROCESSED_BLUEPRINTS_LINK_S2
		console.log('>>prepare->request->' + this.request)
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
		return undefined
	}
    public build(): ApiProcessedBlueprintsTypedRequest {
		this.prepare()
		return this
	}
}
