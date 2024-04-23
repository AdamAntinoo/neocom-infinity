import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

import { ESISecureDataServicePort } from "@app/ports/ESISecureDataServicePort";
import { ESIMiningOperationsTypedRequest } from './ESIMiningOperationsTypedRequest';
import { V1MiningOperationDto } from 'neocom-domain';

@Injectable({
    providedIn: 'root'
})
export class ESISecureDataServiceAdapter implements ESISecureDataServicePort {
    private esiMiningOperationsTypedRequest: ESIMiningOperationsTypedRequest

    constructor(
        private readonly httpService: HttpClient
    ) { }

    // - M I N I N G    O P E R A T I O N S
    public v1_apiEsiMiningOperationsData(pilotId: number): Observable<V1MiningOperationDto[]> {
        this.esiMiningOperationsTypedRequest = new ESIMiningOperationsTypedRequest().prepare(pilotId)
        return this.httpService.get<V1MiningOperationDto[]>(
            this.esiMiningOperationsTypedRequest.request, this.esiMiningOperationsTypedRequest.options
        )
    }
}
