import { firstValueFrom, map } from 'rxjs'
import { HttpService } from '@nestjs/axios'
import { AxiosResponse } from 'axios'
import { ConfigService } from '@nestjs/config'
import { Logger } from '@nestjs/common'

import { ESIDataServicesPort } from '@Application/ports/EsiDataServices.port'
import { GetCharactersCharacterIdAssets200Ok, GetCharactersCharacterIdMining200Ok, V1MiningOperationDto } from 'neocom-domain'
import { MiningOperationsTypedRequest } from './typedrequests/MiningOperations.typedrequest'
import { AssetsTypedRequest } from './typedrequests/Assets.typedrequest'

export class V1ESISecureDataAdapter implements ESIDataServicesPort {
	private readonly logger = new Logger('MiningOperationsTypedRequest')
	private basePath = 'https://esi.evetech.net/latest'

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
			this.httpService.get<V1MiningOperationDto[]>(request.request, request.options).pipe(
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
}
