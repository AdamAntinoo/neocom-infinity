import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

import { ESISecureDataServicePort } from "@app/ports/ESISecureDataServicePort";
import { HttpDataService } from '../../network/HttpDataService';
import { ConfigurationAdapter } from '../../inbound/configuration/ConfigurationAdapter';
import { ESIMiningOperationsTypedRequest } from './ESIMiningOperationsTypedRequest';
import { ESIMiningOperation } from './domain/ESIMiningOperation';

@Injectable({
    providedIn: 'root'
})
export class ESISecureDataServiceAdapter extends HttpDataService implements ESISecureDataServicePort {
    private esiMiningOperationsTypedRequest: ESIMiningOperationsTypedRequest

    constructor(private configuration: ConfigurationAdapter, httpService: HttpClient) {
        super(httpService)
    }

    // - M I N I N G    O P E R A T I O N S
    public v1_apiEsiMiningOperationsData(pilotId: number): Observable<ESIMiningOperation[]> {
        this.esiMiningOperationsTypedRequest = new ESIMiningOperationsTypedRequest(this.configuration)
        return this.httpCall<ESIMiningOperation[]>(
            this.esiMiningOperationsTypedRequest.prepare(pilotId)
        )
    }
}
