import { ConfigService } from '@nestjs/config'
import { AxiosHeaders } from 'axios'

import { BaseTypedRequest } from './BaseTypedRequest'

export interface AssetsTypedRequestParameters {
	characterId
	token
}
export class AssetsTypedRequest extends BaseTypedRequest {
	public age: number = 3600

	constructor(
		private readonly parameters: AssetsTypedRequestParameters,
		private readonly configuration: ConfigService,
	) {
		super()
	}

	protected prepareRequest(): AssetsTypedRequest {
		this.request =
			this.configuration.get<string>('ESI_BACKEND_HOST') +
			'/characters/' +
			String(this.parameters.characterId) +
			'/assets/' +
			'?datasource=tranquility'
		this.logger.log('assets>request->' + this.request)

		return this
	}
	protected prepareOptions() {
		let headers: AxiosHeaders = new AxiosHeaders()
		// WARNING - Headers is a singleton object that needs to be recreated for every set.
		headers = headers.set('content-type', 'application/json')
		headers = headers.set('accept', 'application/json')
		headers = headers.set('authorization', 'Bearer ' + this.parameters.token)
		headers = headers.set('cache-control', 'max-age: ' + this.requestAge + ', public')
		this.options = {
			headers: headers,
		}
	}
	public build(): AssetsTypedRequest {
		this.prepare()
		return this
	}
}
