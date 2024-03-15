import { CapsuleerMiningOperationsUseCase } from "@App/use-cases/mining-operation/CapsuleerMiningOperationsUseCase";
import { EsiMining } from "@App/use-cases/mining-operation/EsiMining";
import { HttpService } from "@nestjs/axios";
import { Controller, Get, Param } from "@nestjs/common";
import { firstValueFrom } from "rxjs";

@Controller('/nin/v1/character/')
export class V1MiningOperationsController {
    constructor(private readonly getCapsuleerMiningOperationsUseCase: CapsuleerMiningOperationsUseCase,
        private httpService: HttpService) { }

    // @Get(':characterId/miningoperations')
    public async getMiningOperations(@Param() params: any): Promise<any> {
        // console.log('minop.params->' + params)
        // const response = this.getCapsuleerMiningOperationsUseCase.getMining(params.characterId)
        // .subscribe(data => {
        //     console.log('data->' + JSON.stringify(data))
        // })
        // return response
        // console.log(JSON.stringify(response))
        return new Promise((resolve, reject) => {
            resolve('Reached')
        })
    }
    @Get(':characterId/miningoperations')
    public async getMiningOperationsDirect(@Param() params: any): Promise<EsiMining[]> {
        return this.getCapsuleerMiningOperationsUseCase.getMiningOperations(params.characterId)
        // const request = 'http://localhost:5271/characters/' + params.characterId + '/mining/'
        // console.log('request->' + request)
        // const { data } = await firstValueFrom(this.httpService.get<EsiMining[]>(request))
        // return data

        // const response = this.getCapsuleerMiningOperationsUseCase.getMining(params.characterId)
        // // .subscribe(data => {
        // //     console.log('data->' + JSON.stringify(data))
        // // })
        // return response
        // // console.log(JSON.stringify(response))
        // return new Promise((resolve, reject) => {
        //     resolve('Reached')
        // })
    }
}
