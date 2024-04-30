import { Logger } from '@nestjs/common'
import { ConfigService } from '@nestjs/config'
import { AxiosHeaders, AxiosRequestConfig } from 'axios'

export interface MiningOperationsTypedRequestParameters {
	characterId
	token
}
export class MiningOperationsTypedRequest {
	private readonly logger = new Logger('MiningOperationsTypedRequest')
	public method: string = 'GET'
	public requestAge: number = 600
	public request: string
	public options: AxiosRequestConfig

	constructor(
		private readonly parameters: MiningOperationsTypedRequestParameters,
		private readonly configuration: ConfigService,
	) {}

	private prepare(parameters: MiningOperationsTypedRequestParameters): MiningOperationsTypedRequest {
		this.request = this.configuration.get<string>('ESI_BACKEND_HOST') + '/characters/' + parameters.characterId + '/mining/'
		this.logger.log('miningOperations>request->' + this.request)

		return this
	}
	// eslint-disable-next-line @typescript-eslint/no-unused-vars
	private prepareOptions(parameters: MiningOperationsTypedRequestParameters): AxiosRequestConfig {
		let headers: AxiosHeaders = new AxiosHeaders()
		// WARNING - Headers is a singleton object that needs to be recreated for every set.
		headers = headers.set('Content-Type', 'application/json')
		headers = headers.set('x-neocom-check', 'check header')
		headers = headers.set('authorization', 'Bearer ' + this.parameters.token)
		headers = headers.set('Cache-Control', 'max-age: ' + this.requestAge + ', public')
		const config: AxiosRequestConfig = {
			headers: headers,
		}
		return config
	}
	public build(): MiningOperationsTypedRequest {
		this.method = 'GET'
		this.prepare(this.parameters)
		this.prepareOptions(this.parameters)
		return this
	}
}
