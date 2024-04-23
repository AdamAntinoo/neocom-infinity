import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

import { ESISecureDataServicePort } from "@app/ports/ESISecureDataServicePort";
import { ConfigurationAdapter } from '../configuration/ConfigurationAdapter';
import { ESIMiningOperationsTypedRequest } from './ESIMiningOperationsTypedRequest';
import { ResponseTransformer } from 'neocom-domain/ResponseTransformer';
import { V1MiningOperationDto, V1StackDto } from 'neocom-domain';

@Injectable({
    providedIn: 'root'
})
export class ESISecureDataServiceAdapter implements ESISecureDataServicePort {
    private esiMiningOperationsTypedRequest: ESIMiningOperationsTypedRequest

    constructor(
        private readonly configuration: ConfigurationAdapter,
        private readonly httpService: HttpClient
    ) {
        console.log('ESISecureDataServiceAdapter>configuration->' + this.configuration.getNestBackendHost())
    }

    // - M I N I N G    O P E R A T I O N S
    public v1_apiEsiMiningOperationsData(pilotId: number): Observable<V1MiningOperationDto[]> {
        this.esiMiningOperationsTypedRequest = new ESIMiningOperationsTypedRequest(this.configuration).prepare(pilotId)
        const transformer: ResponseTransformer = new ResponseTransformer()
            .setDescription('Does nothing since the response expected is a list of HAL links.')
            .setTransformation((entrydata: any): V1MiningOperationDto[] => {
                let results: V1MiningOperationDto[] = []
                if (entrydata instanceof Array) {
                    for (let key in entrydata)
                        results.push(this.buildMiningOperation(entrydata[key]))
                } else
                    results.push(this.buildMiningOperation(entrydata))

                return results;
            })
        return this.httpService.get(this.esiMiningOperationsTypedRequest.request, this.esiMiningOperationsTypedRequest.options)
            .pipe(map((data: V1MiningOperationDto[]) => {
                console.log(">[v1_apiEsiMiningOperationsData]> Transformation: " +
                    transformer.description)
                return transformer.transform(data)
            }))
    }
    private buildMiningOperation ( entryData : any ): V1MiningOperationDto{
        const miningOperationDto : V1MiningOperationDto = new V1MiningOperationDto.Builder(entryData)
        .build()
        for ( let resourceDto of entryData['resources']){
            const stackDto : V1StackDto = new V1StackDto(resourceDto)
            console.log('02 building stackDto->'+JSON.stringify(stackDto))
            miningOperationDto.addMiningResource(stackDto)
        }
        console.log('02 building miningOperationDto->'+JSON.stringify(miningOperationDto))
        return miningOperationDto
    }
}
