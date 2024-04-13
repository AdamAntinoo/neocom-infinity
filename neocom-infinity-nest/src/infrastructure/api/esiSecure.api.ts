import { V1MiningOperationDto } from "neocom-domain"

export interface EsiSecureApi {
    getMiningOperations(token: string): Promise<V1MiningOperationDto[]>
}
