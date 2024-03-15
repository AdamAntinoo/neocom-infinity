import { EsiMining } from "@App/use-cases/mining-operation/EsiMining"
import { HttpService } from "@nestjs/axios"
import { Injectable } from "@nestjs/common"
import { firstValueFrom } from "rxjs"

@Injectable()
export class EsiMiningAdapter {
    constructor(private readonly httpService: HttpService) { }

    public async getMiningOperations(characterId: number): Promise<EsiMining[]> {
        const request = 'http://localhost:5271/characters/' + characterId + '/mining/'
        console.log('request->' + request)
        const { data } = await firstValueFrom(this.httpService.get<EsiMining[]>(request))
        return data

    }
}
