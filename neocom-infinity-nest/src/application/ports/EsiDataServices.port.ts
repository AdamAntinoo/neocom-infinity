import { IEsiMiningSecureService } from './IEsiMiningSecureService.port'
import { GetCharactersCharacterIdMining200Ok } from 'application/domain/esi-model/models'

export abstract class ESIDataServicesPort {
	abstract miningOperations: IEsiMiningSecureService<GetCharactersCharacterIdMining200Ok>
}
