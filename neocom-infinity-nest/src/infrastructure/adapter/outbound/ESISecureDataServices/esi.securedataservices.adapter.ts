import { HttpService } from '@nestjs/axios'
import { Injectable } from '@nestjs/common'
import { V1MiningOperationsAdapter } from './MiningOperations/V1.MiningOperations.adapter'
import { ESIDataServicesPort } from 'application/ports/EsiDataServices.port'
import { IEsiMiningSecureService } from 'application/ports/IEsiMiningSecureService.port'
import { ConfigService } from '@nestjs/config'
import { GetCharactersCharacterIdMining200Ok } from 'application/domain/esi-model/models'

@Injectable()
export class ESISecureDataServicesAdapter implements ESIDataServicesPort {
	miningOperations: IEsiMiningSecureService<GetCharactersCharacterIdMining200Ok>

	constructor(
		private readonly httpService: HttpService,
		private readonly configuration: ConfigService,
	) {
		this.miningOperations = new V1MiningOperationsAdapter(this.httpService, this.configuration)
	}
}
