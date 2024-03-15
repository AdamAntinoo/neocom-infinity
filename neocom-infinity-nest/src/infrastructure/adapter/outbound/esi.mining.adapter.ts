import { EsiDataServicesPort } from "@App/ports/EsiDataServices.port"
import { IEsiSecureService } from "@App/ports/IEsiSecureService"
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation"
import { HttpService } from "@nestjs/axios"
import { Injectable, OnApplicationBootstrap } from "@nestjs/common"
import { firstValueFrom } from "rxjs"

@Injectable()
export class EsiMiningAdapter implements EsiDataServicesPort, OnApplicationBootstrap {
    miningOperations: IEsiSecureService<V2MiningOperation>

    constructor(private readonly httpService: HttpService) {
        this.miningOperations = new EsiMiningSecureService(this.httpService)
    }

    public onApplicationBootstrap() {
        console.log('app boostrap')
        this.miningOperations = new EsiMiningSecureService(this.httpService)
    }
}

class EsiMiningSecureService implements IEsiSecureService<V2MiningOperation>{
    constructor(private readonly httpService: HttpService) { }

    public async getMiningOperations(characterId: number): Promise<V2MiningOperation[]> {
        const request = 'http://localhost:5271/characters/' + characterId + '/mining/'
        console.log('request->' + request)
        const { data } = await firstValueFrom(this.httpService.get<V2MiningOperation[]>(request))
        return data
    }
}
