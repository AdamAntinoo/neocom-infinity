import { ConfigService } from '@nestjs/config'
import { AxiosHeaders } from 'axios'
import { BaseTypedRequest } from './Base.typedrequest'

export interface AssetsTypedRequestParameters {
	characterId
	token
}
export class BlueprintsTypedRequest extends BaseTypedRequest {
	public requestAge: number = 3600

	constructor(
		private readonly parameters: AssetsTypedRequestParameters,
		private readonly configuration: ConfigService,
	) {
		super()
	}

	protected prepareRequest() {
		this.request =
			this.configuration.get<string>('ESI_BACKEND_HOST') +
			'/v3/characters/' +
			String(this.parameters.characterId) +
			'/blueprints/' +
			'?datasource=tranquility'
		this.logger.log('blueprints>request->' + this.request)

		return this
	}
	protected prepareOptions() {
		let headers: AxiosHeaders = new AxiosHeaders()
		// WARNING - Headers is a singleton object that needs to be recreated for every set.
		headers = headers.set('Content-Type', 'application/json')
		headers = headers.set('Accept', 'application/json')
		headers = headers.set('Authorization', 'Bearer ' + this.parameters.token)
		headers = headers.set('Cache-Control', 'max-age: ' + this.requestAge + ', public')
		this.options = {
			headers: headers,
		}
	}
	protected preparePayload(): void {
		this.payload = {}
	}
	public build(): BlueprintsTypedRequest {
		this.prepare()
		return this
	}
}
