import { V1MiningOperationDto } from "../dto/V1.MiningOperation.dto"
import { V2ProcessedBlueprintDto } from "../dto/v2.ProcessedBlueprint.dto"

export interface IndustryServiceInterface {
	getMiningOperations4Pilot(token: string, sessionid: string): Promise<V1MiningOperationDto[]>
	getProcessedBlueprints4Pilot(neocomToken: string, sessionid: string): Promise<V2ProcessedBlueprintDto[]>
}
