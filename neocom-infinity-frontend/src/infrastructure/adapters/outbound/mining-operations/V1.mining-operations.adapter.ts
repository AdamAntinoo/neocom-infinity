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
        console.log('downloadMiningOperationsForCharacter->' + characterId)
        return this.esiSecureAdapter.v1_apiEsiMiningOperationsData(characterId)
            .pipe(map((miningOperationList: V1MiningOperationDto[]) => {
                const resolveData: V3MiningOperation[] = []
                miningOperationList.forEach((element: V1MiningOperationDto) => {
                    console.log('operation->' + JSON.stringify(element))
                    this.factory.construct(element).then((operation: V3MiningOperation) => {
                        console.log('RESULTING OPERATION->'+JSON.stringify(operation))
                        resolveData.push(operation)
                    })
                    // new MiningOperationConstructor(this.resolver).construct(element)
                    //     .then((operation: V3MiningOperation) => {
                    //         resolveData.push(operation)
                    //     })
                })
                return resolveData
            }))
    }
}
// this.factory.construct(element).then((operation: V3MiningOperation) => {
//     resolveData.push(operation)
// })
// miningOperationList.forEach((element: V1MiningOperationDto) => {
//     const operation : V3MiningOperation =  this.factory.construct(element)
//     resolveData.push(operation)
//     // new MiningOperationConstructor(this.resolver).construct(element)
//     //     .then((operation: V3MiningOperation) => {
//     //         resolveData.push(operation)
//     //     })
// })
