import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'

import { ESISecureDataServiceAdapter } from '../esi-data-service/ESISecureDataServiceAdapter';
import { V3MiningOperation } from '@domain/industry/V3.MiningOperation.domain';
import { V1MiningOperationDto } from 'neocom-domain';
import { BackendFactory } from '@adapter/factory/BackendFactory';

@Injectable({
    providedIn: 'root'
})
/**
 *  WARNING: The downloader should know the classes for the HAL links that are defined on the target type. That should be hidden inside the transformer or at the target type on the transform() method
 */
export class V1MiningOperationsAdapterService {

    constructor(
        private readonly esiSecureAdapter: ESISecureDataServiceAdapter,
        private readonly factory: BackendFactory
    ) { }

    public downloadMiningOperationsForCharacter(characterId: number): Observable<V3MiningOperation[]> {
        // console.log('01 downloadMiningOperationsForCharacter->' + characterId)
        return this.esiSecureAdapter.v1_apiEsiMiningOperationsData(characterId)
            .pipe(map((miningOperationList: V1MiningOperationDto[]) => {
                // console.log('subscription output for mapping->',JSON.stringify(miningOperationList))
                const resolveData: V3MiningOperation[] = []
                // let index:number =0
                miningOperationList.forEach((element: V1MiningOperationDto) => {
                    // index++
                    // console.log('element->' + JSON.stringify(element))
                    // console.log(this.factory)
                  /*  const operation: Promise<V3MiningOperation> =*/ this.factory.construct(element)
                    .then((operation: V3MiningOperation) => {
                        // console.log('RESULTING OPERATION->' + JSON.stringify(operation))
                        resolveData.push(operation)
                    })
                })
                return resolveData
            }))
    }
}
