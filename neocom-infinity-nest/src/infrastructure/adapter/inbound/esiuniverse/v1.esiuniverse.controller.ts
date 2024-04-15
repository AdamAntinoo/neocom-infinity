import { EsiUniversepi } from "@Infra/api/esiUniverse.api";
import { Controller, Get } from "@nestjs/common";
import { GetTypeInformationUseCase } from "application/use-cases/esi-universe/GetTypeInformation.usecase";
import { V1EsiTypeDto } from "neocom-domain";

@Controller('/nin/v1/character/')
export class V1EsiUniverseController implements EsiUniversepi {
    constructor(
        private readonly getEsiGetTypeInformationUseCase: GetTypeInformationUseCase,
    ) { }

    @Get('miningoperations')
    public async esiGetTypeInformation(typeId: number): Promise<V1EsiTypeDto> {
        if (undefined != typeId)
            return this.getEsiGetTypeInformationUseCase.esiGetTypeInformation(typeId)
    }
}
