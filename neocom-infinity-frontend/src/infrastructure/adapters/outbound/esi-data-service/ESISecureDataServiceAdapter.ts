import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

import { ESISecureDataServicePort } from "@app/ports/ESISecureDataServicePort";
import { HttpDataService } from '../../network/HttpDataService';
import { ConfigurationAdapter } from '../../inbound/configuration/ConfigurationAdapter';
import { ESIMiningOperationsTypedRequest } from './ESIMiningOperationsTypedRequest';
import { ESIMiningOperation } from './domain/ESIMiningOperation';
import { map } from 'rxjs/operators';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';

@Injectable({
    providedIn: 'root'
})
export class ESISecureDataServiceAdapter implements ESISecureDataServicePort {
    private esiMiningOperationsTypedRequest: ESIMiningOperationsTypedRequest

    constructor(private configuration: ConfigurationAdapter, private httpService: HttpClient) { }

    // - M I N I N G    O P E R A T I O N S
    public v1_apiEsiMiningOperationsData(pilotId: number): Observable<ESIMiningOperation[]> {
        this.esiMiningOperationsTypedRequest = new ESIMiningOperationsTypedRequest(this.configuration).prepare(pilotId)
        const transformer: ResponseTransformer = new ResponseTransformer()
            .setDescription('Does nothing since the response expected is a list of HAL links.')
            .setTransformation((entrydata: any): ESIMiningOperation[] => {
                let results: ESIMiningOperation[] = [];
                if (entrydata instanceof Array) {
                    for (let key in entrydata)
                        results.push(new ESIMiningOperation(entrydata[key]));
                } else
                    results.push(new ESIMiningOperation(entrydata));

                return results;
            })
        return this.httpService.get(this.esiMiningOperationsTypedRequest.request, this.esiMiningOperationsTypedRequest.options)
            .pipe(map((data: any) => {
                console.log(">[v1_apiEsiMiningOperationsData]> Transformation: " +
                    transformer.description)
                const response = transformer.transform(data) as ESIMiningOperation[]
                return response
            }))
    }
}
