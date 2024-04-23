import { GetMarketDataUseCaseInput } from "application/use-cases/esi-universe/GetMarketData.usecase";
import { NeoComError, REQUEST_INVALID } from "neocom-domain";

export class MarketDataUseCaseInputConstructor {
    public construct(parameters: any): GetMarketDataUseCaseInput {
        let region = 30000142 // Jita
        if (undefined != parameters.region) region = parameters.region
        if (undefined == parameters.typeId) throw new NeoComError.Builder(REQUEST_INVALID).build()
        const input: GetMarketDataUseCaseInput = {
            typeId: parameters.typeId,
            systemId: region
        }
        return input
    }
}
