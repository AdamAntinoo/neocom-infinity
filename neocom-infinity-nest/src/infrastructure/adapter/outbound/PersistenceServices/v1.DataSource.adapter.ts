import { ProcessedBlueprintsUseCase } from '@App/use-cases/esi-secure/ProcessedBlueprints.usecase'
import { createClient } from 'redis'

export class V1DataSourceAdapter {
	private readonly COST_INDEX_BLUEPRINTS_CACHE_NAME: string = 'BCI'
	private readonly REDIS_SEPARATOR: string = ':'
	private redisClient = createClient()

	public accessProcessedBlueprints(pilotId: number): ProcessedBlueprintsUseCase.Output {
		const uniqueLSOKey: string = this.generateBlueprintCostIndexUniqueId(pilotId)
		const BCIMap = this.redisClient.hGetAll(uniqueLSOKey)
		return BCIMap
	}

	private async connect(): void {
		this.redisClient.on('error', err => console.log('Redis Client Error', err))
		await this.redisClient.connect()
	}
	public generateBlueprintCostIndexUniqueId(pilotId: number): string {
		return this.COST_INDEX_BLUEPRINTS_CACHE_NAME + this.REDIS_SEPARATOR + pilotId
	}
}
