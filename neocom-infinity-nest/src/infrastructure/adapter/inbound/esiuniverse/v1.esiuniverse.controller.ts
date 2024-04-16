import { EsiUniversepi } from "@Infra/api/esiUniverse.api";
import { Controller, Get, Param } from "@nestjs/common";
import { GetTypeInformationUseCase } from "application/use-cases/esi-universe/GetTypeInformation.usecase";
import { V1EsiTypeDto } from "neocom-domain";

@Controller('/esi/v1/universe/')
export class V1EsiUniverseController implements EsiUniversepi {
    constructor(
        private readonly getEsiGetTypeInformationUseCase: GetTypeInformationUseCase,
    ) { }

    @Get('types/:typeId')
    public async esiGetTypeInformation(@Param() params: any): Promise<V1EsiTypeDto> {
        console.log('typeId->' + params.typeId)
        if (undefined != params.typeId)
            return this.getEsiGetTypeInformationUseCase.esiGetTypeInformation(params.typeId)
    }
}
