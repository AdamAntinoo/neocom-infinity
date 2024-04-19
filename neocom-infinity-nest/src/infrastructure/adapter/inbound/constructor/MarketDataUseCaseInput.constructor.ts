import { GetMarketDataUseCaseInput } from "application/use-cases/esi-universe/GetMarketData.usecase";
import { NeoComError, NeoComErrorDefinition, NeoComSharedErrorDefinition } from "neocom-domain";

export const REQUEST_INVALID: NeoComErrorDefinition = new NeoComSharedErrorDefinition({
    httpCode: 400,
    code: "REQUEST-INVALID",
    messagePattern: "Missing required parameter."
})
export class MarketDataUseCaseInputConstructor {
    public construct(parameters: any): GetMarketDataUseCaseInput {
        let region = 30000142 // Jita
        if (undefined != parameters.region) region = parameters.region
        if (undefined == parameters.typeId) throw new NeoComError.Builder(REQUEST_INVALID).build()
        const input: GetMarketDataUseCaseInput = {
            typeId: parameters.typeId,
            region: region
        }
        return input
    }
}
