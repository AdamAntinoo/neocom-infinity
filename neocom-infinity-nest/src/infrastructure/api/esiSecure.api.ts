import { V2MiningOperation } from "neocom-domain"

export interface EsiSecureApi {
    getMiningOperations(token: string): Promise<V2MiningOperation[]>
}
