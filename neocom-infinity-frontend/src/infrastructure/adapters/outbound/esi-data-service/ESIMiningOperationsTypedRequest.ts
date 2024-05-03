import { HttpHeaders } from '@angular/common/http'

import { TypedRequest } from 'neocom-domain'
import { IsolationService } from '@innovative/services/isolation.service'
import { BACKEND_LINKS, STORAGE_LINKS } from '@env/PlatformConstants'

declare namespace ESIMiningOperationsTypedRequest {
	export type Request = number
}

export class ESIMiningOperationsTypedRequest implements TypedRequest {
	public method: string = 'GET'
	public request: string
	public options: {}

	constructor(private readonly isolationService: IsolationService) {
		let headers: HttpHeaders = new HttpHeaders()
		this.options = {
			headers: headers,
		}
	}

	public prepare(requestInput: ESIMiningOperationsTypedRequest.Request) {
		console.log('prepare->parameters->' + requestInput)
		this.request = BACKEND_LINKS.BACKEND_MINING_OPERATIONS_LINK
		console.log('prepare->request->' + this.request)
		return this
	}
}
