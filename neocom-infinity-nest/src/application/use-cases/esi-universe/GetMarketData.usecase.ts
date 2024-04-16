import { Injectable } from "@nestjs/common";
import { ESIDataUniverseServicesPort } from "application/ports/ESIDataUniverseServices.port";
import { V1MarketDataDto } from "neocom-domain";
import { FuzzWorkMarketData } from "neocom-domain";

export interface GetMarketDataUseCaseInput {
    typeId: number
    region: number
}
declare namespace GetMarketDataUseCase {
    export type Request = GetMarketDataUseCaseInput
}

@Injectable()
export class GetMarketDataUseCase {
    constructor(private readonly esiUniverseServices: ESIDataUniverseServicesPort) { }

    public async esiGetMarketData(input: GetMarketDataUseCase.Request): Promise<V1MarketDataDto> {
        const esiMarketData: FuzzWorkMarketData = await this.esiUniverseServices.getFuzzWorkMarketData(
            input.typeId, input.region
        )
        return new Promise<V1MarketDataDto>((resolve) => {
            const data: FuzzWorkMarketData = esiMarketData[input.typeId]
            const marketData: V1MarketDataDto = new V1MarketDataDto({
                buyPrice: Number(data.buy.max),
                buyOrderCount: Number(data.buy.orderCount),
                sellPrice: Number(data.sell.min),
                sellOrderCount: Number(data.sell.orderCount)
            })
            resolve(marketData)
        })
    }
}
