import { EsiUniversepi } from "@Infra/api/esiUniverse.api";
import { Controller, Get, Param } from "@nestjs/common";
import { GetTypeInformationUseCase } from "application/use-cases/esi-universe/GetTypeInformation.usecase";
import { V1EsiTypeDto, V1MarketDataDto } from "neocom-domain";
import { MarketDataUseCaseInputConstructor } from "../constructor/MarketDataUseCaseInput.constructor";
import { GetMarketDataUseCase } from "application/use-cases/esi-universe/GetMarketData.usecase";

@Controller('/esi/v1/')
export class V1EsiUniverseController implements EsiUniversepi {
    constructor(
        private readonly getEsiGetTypeInformationUseCase: GetTypeInformationUseCase,
        private readonly getGetMarketDataUseCase: GetMarketDataUseCase
    ) { }

    @Get('universe/types/:typeId')
    public async esiGetTypeInformation(@Param() params: any): Promise<V1EsiTypeDto> {
        console.log('typeId->' + params.typeId)
        if (undefined != params.typeId)
            return this.getEsiGetTypeInformationUseCase.esiGetTypeInformation(params.typeId)
    }
    @Get('fuzzwork/marketdata/:typeId/:region')
    public async esiGetMarketData(@Param() params: any): Promise<V1MarketDataDto> {
        console.log('parameters->'+JSON.stringify(params))
        return this.getGetMarketDataUseCase.esiGetMarketData(
            new MarketDataUseCaseInputConstructor().construct(params)
        )
    }
}
