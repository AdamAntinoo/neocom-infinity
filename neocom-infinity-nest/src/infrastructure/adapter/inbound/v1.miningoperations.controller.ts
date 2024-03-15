import { Controller } from "@nestjs/common";

@Controller('MiningOperationsController')
export class V1MiningOperationsController{
    constructor(private readonly getCapsuleerMiningOperationsUseCase: CapsuleerMiningOperationsUseCase) {}

}
