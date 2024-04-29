import { ConfigService } from '@nestjs/config'
import { AxiosHeaders, AxiosRequestConfig } from 'axios'
import { TypedRequest } from 'neocom-domain'

export interface MiningOperationsTypedRequestParameters {
	characterId
	token
}
export class MiningOperationsTypedRequest implements TypedRequest {
	public method: string = 'GET'
	public requestAge: number = 600
	public request: string
	public options: AxiosRequestConfig

	constructor(
		private readonly parameters: MiningOperationsTypedRequestParameters,
		private readonly configuration: ConfigService,
	) {
		this.method = 'GET'
		this.prepare(parameters)
	}
	public prepare(parameters: MiningOperationsTypedRequestParameters): TypedRequest {
		this.request = this.configuration.get<string>('ESI_BACKEND_HOST') + '/characters/' + parameters.characterId + '/mining/'
		console.log('miningOperations>request->' + this.request)
		this.options = this.prepareOptions()

		return this
	}
	public prepareOptions(): AxiosRequestConfig {
		const headers: AxiosHeaders = new AxiosHeaders()
		headers.set('Content-Type', 'application/json')
		headers.set('x-neocom-check', 'check header')
		headers.set('authorization', 'Bearer ' + this.parameters.token)
		headers.set('Cache-Control', 'max-age: ' + this.requestAge + ', public')
		const config: AxiosRequestConfig = {
			headers: headers,
		}
		return config
	}
}
