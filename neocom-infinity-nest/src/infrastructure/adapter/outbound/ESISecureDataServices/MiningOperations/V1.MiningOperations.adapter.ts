import { ConfigService } from '@nestjs/config'
import { HttpService } from '@nestjs/axios'
import { AxiosResponse } from 'axios'
import { firstValueFrom, map } from 'rxjs'
import { IEsiMiningSecureService } from 'application/ports/IEsiMiningSecureService.port'
import { GetCharactersCharacterIdMining200Ok } from 'application/domain/esi-model/models'
import { V1MiningOperationDto } from 'neocom-domain'
import { MiningOperationsTypedRequest } from './MiningOperations.typedrequest'
import { Logger } from '@nestjs/common'

export class V1MiningOperationsAdapter implements IEsiMiningSecureService<GetCharactersCharacterIdMining200Ok> {
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
			this.httpService.get<V1MiningOperationDto[]>(request.request, request.options).pipe(
				map((axiosResponse: AxiosResponse) => {
					return axiosResponse.data as GetCharactersCharacterIdMining200Ok[]
				}),
			),
		)
	}
}
