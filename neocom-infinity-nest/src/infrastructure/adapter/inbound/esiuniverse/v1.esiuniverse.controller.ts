import { EsiUniverseApi } from "@Infra/api/esiUniverse.api";
import { Controller, Get, Param } from "@nestjs/common";
import { GetTypeInformationUseCase } from "application/use-cases/esi-universe/GetTypeInformation.usecase";
import { LOCATION_INVALID, NeoComError, TYPE_INVALID, V1EsiTypeDto, V1MarketDataDto, V1SpaceLocationDto } from "neocom-domain";
import { MarketDataUseCaseInputConstructor } from "../constructor/MarketDataUseCaseInput.constructor";
import { GetMarketDataUseCase } from "application/use-cases/esi-universe/GetMarketData.usecase";
import { GetSpaceLocationUseCase } from "application/use-cases/esi-universe/GetSpaceLocation.usecase";

@Controller('/esi/v1/')
export class V1EsiUniverseController implements EsiUniverseApi {
    constructor(
        private readonly getGetTypeInformationUseCase: GetTypeInformationUseCase,
        private readonly getGetMarketDataUseCase: GetMarketDataUseCase,
        private readonly getSpaceLocationUseCase: GetSpaceLocationUseCase
    ) { }

    @Get('universe/types/:typeId')
    public async esiGetTypeInformation(@Param() params: any): Promise<V1EsiTypeDto> {
        console.log('typeId->' + params.typeId)
        if (undefined == params.typeId) throw new NeoComError.Builder(TYPE_INVALID).build()
        return this.getGetTypeInformationUseCase.esiGetTypeInformation(params.typeId as number)
    }
    @Get('fuzzworks/marketdata/:typeId/:region')
    public async esiGetMarketData(@Param() params: any): Promise<V1MarketDataDto> {
        console.log('parameters->' + JSON.stringify(params))
        return this.getGetMarketDataUseCase.esiGetMarketData(
            new MarketDataUseCaseInputConstructor().construct(params)
        )
    }
    @Get('universe/spacelocation/:locationId')
    public async esiGetLocation(@Param() params: any): Promise<V1SpaceLocationDto> {
        console.log('locationId->' + params.locationId)
        if (undefined == params.locationId) throw new NeoComError.Builder(LOCATION_INVALID).build()
        return this.getSpaceLocationUseCase.esiGetSpaceLocation(params.locationId as number)
    }

}
