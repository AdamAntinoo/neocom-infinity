import { firstValueFrom, map } from 'rxjs'
import { HttpService } from '@nestjs/axios'
import { AxiosResponse } from 'axios'
import { ConfigService } from '@nestjs/config'
import { Injectable, Logger } from '@nestjs/common'

import { ESIDataServicesPort } from '@App/ports/ESIDataServices.port'
import {
	GetCharactersCharacterIdAssets200Ok,
	GetCharactersCharacterIdBlueprints200Ok,
	GetCharactersCharacterIdMining200Ok,
	NeoComError,
	NeoComErrorDefinition,
	NeoComSharedErrorDefinition,
} from 'neocom-domain'
import { MiningOperationsTypedRequest } from './typedrequests/MiningOperations.typedrequest'
import { AssetsTypedRequest } from './typedrequests/Assets.typedrequest'
import { BlueprintsTypedRequest } from './TypedRequests/Blueprints.typedreques'
import { BaseTypedRequest } from './TypedRequests/Base.typedrequest'

export interface EsiSecureAdapterInput {
	characterId: number
	token: string
}
export const BACKEND_RESPONSE_INVALID: NeoComErrorDefinition = new NeoComSharedErrorDefinition({
	httpCode: 502,
	code: 'BACKEND-RESPONSE-INVALID',
	messagePattern: 'The response from the next backend at ESI or other serfives has returned a response with an invalid format.',
})

@Injectable()
export class V1ESISecureDataAdapter implements ESIDataServicesPort {
	private readonly logger = new Logger('MiningOperationsTypedRequest')

	constructor(
		private readonly httpService: HttpService,
		private readonly configuration: ConfigService,
	) {}

	public async getMiningOperations(characterId: number, token: string): Promise<GetCharactersCharacterIdMining200Ok[]> {
		const request: MiningOperationsTypedRequest = new MiningOperationsTypedRequest(
			{
				characterId: characterId,
				token: token,
			},
			this.configuration,
		).build()
		if (process.env.LOGLEVEL == 'debug')
			this.httpService.axiosRef.interceptors.request.use(request => {
				this.logger.log('>>> Adapter Request->', JSON.stringify(request, null, 2))
				return request
			})
		return await firstValueFrom(
			this.httpService.get<GetCharactersCharacterIdMining200Ok[]>(request.request, request.options).pipe(
				map((axiosResponse: AxiosResponse) => {
					return axiosResponse.data as GetCharactersCharacterIdMining200Ok[]
				}),
			),
		)
	}

	public async getCharactersCharacterIdAssets(characterId: number, token: string): Promise<GetCharactersCharacterIdAssets200Ok[]> {
		const request: AssetsTypedRequest = new AssetsTypedRequest(
			{
				characterId: characterId,
				token: token,
			},
			this.configuration,
		).build()
		if (process.env.LOGLEVEL == 'debug')
			this.httpService.axiosRef.interceptors.request.use(request => {
				this.logger.log('>>> Adapter Request->', JSON.stringify(request, null, 2))
				return request
			})
		return await firstValueFrom(
			this.httpService.get<GetCharactersCharacterIdAssets200Ok[]>(request.request, request.options).pipe(
				map((axiosResponse: AxiosResponse) => {
					return axiosResponse.data as GetCharactersCharacterIdAssets200Ok[]
				}),
			),
		)
	}
	public async getCharactersCharacterIdBlueprints(characterId: number, token: string): Promise<GetCharactersCharacterIdBlueprints200Ok[]> {
		return this.axiosRequest<GetCharactersCharacterIdBlueprints200Ok>(
			new BlueprintsTypedRequest(
				{
					characterId: characterId,
					token: token,
				},
				this.configuration,
			).build(),
		)
	}
	private async axiosRequest<T>(request: BaseTypedRequest): Promise<T[]> {
		if (process.env.LOGLEVEL === 'debug') {
			this.httpService.axiosRef.interceptors.request.use(request => {
				this.logger.log('>>> Adapter Request->', JSON.stringify(request, null, 2))
				return request
			})
		}
		switch (request.method) {
			case 'GET': {
				return await firstValueFrom(
					this.httpService.get<T[]>(request.request, request.options).pipe(
						map((axiosResponse: AxiosResponse) => {
							if (Array.isArray(axiosResponse.data)) return axiosResponse.data as T[]
							if (typeof axiosResponse.data === 'string') throw new NeoComError.Builder(BACKEND_RESPONSE_INVALID).build()
							return axiosResponse.data as T[]
						}),
					),
				)
			}
		}
	}
}
