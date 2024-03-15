import { HttpService } from "@nestjs/axios";
import { Injectable, Logger } from "@nestjs/common";
import { AxiosError, AxiosResponse } from "axios";
import { Observable, catchError, firstValueFrom, lastValueFrom } from "rxjs";
import { EsiMining } from "./EsiMining";
import { EsiMiningAdapter } from "@Infra/adapter/outbound/esi.mining.adapter";

@Injectable()
export class CapsuleerMiningOperationsUseCase {
    private readonly logger = new Logger(CapsuleerMiningOperationsUseCase.name);

    constructor(private readonly miningAdapter: EsiMiningAdapter) { }

    public async getMiningOperations(characterId: number): Promise<EsiMining[]> {
        return this.miningAdapter.getMiningOperations(characterId)
        // const request = 'http://localhost:5271/characters/' + characterId + '/mining/'
        // console.log('request->' + request)
        // const { data } = await firstValueFrom(this.httpService.get<EsiMining[]>(request))
        // return data

    }
}
