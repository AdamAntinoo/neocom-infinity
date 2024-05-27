import { Observable } from "rxjs"
import { V1MiningOperationDto } from "../dto/V1.MiningOperation.dto"

export interface IndustryServiceInterface {
	getMiningOperations(token: string): Observable<V1MiningOperationDto[]>
}
