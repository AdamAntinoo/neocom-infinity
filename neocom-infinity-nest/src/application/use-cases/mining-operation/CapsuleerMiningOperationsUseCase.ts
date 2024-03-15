import { HttpService } from "@nestjs/axios";
import { Injectable, Logger } from "@nestjs/common";
import { AxiosError, AxiosResponse } from "axios";
import { Observable, catchError, firstValueFrom, lastValueFrom } from "rxjs";
import { EsiMining } from "./EsiMining";
import { EsiMiningAdapter } from "@Infra/adapter/outbound/esi.mining.adapter";
import { EsiDataServicesPort } from "@App/ports/EsiDataServices.port";
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";

@Injectable()
export class CapsuleerMiningOperationsUseCase {
    constructor(private readonly dataServices: EsiDataServicesPort) { }

    public async getMiningOperations(characterId: number): Promise<V2MiningOperation[]> {
        console.log('dataServices->',this.dataServices)
        console.log('dataServices.miningOperations->',this.dataServices.miningOperations)
        return this.dataServices.miningOperations.getMiningOperations(characterId)
    }
}
