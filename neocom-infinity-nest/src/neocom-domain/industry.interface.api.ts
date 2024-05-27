import { V1MiningOperationDto } from 'neocom-domain'

export interface IndustryServiceInterface {
	getMiningOperations(token: string): Promise<V1MiningOperationDto[]>
}
