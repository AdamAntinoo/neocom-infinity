import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, mergeMap } from 'rxjs/operators'

import { V3MiningOperation } from '@domain/industry/V3.MiningOperation.domain';
import { V1MiningOperationDto } from 'neocom-domain';
import { BackendFactory } from '@adapter/factory/BackendFactory';
import { SecuredServicesPort } from 'src/infrastructure/ports/SecuredServices.port';
import { V1SecuredProxyAdapter } from '../SecuredProxy/v1.SecuredProxy.adapter';

/** @deprecated */
@Injectable({
    providedIn: 'root'
})
/**
 *  WARNING: The downloader should know the classes for the HAL links that are defined on the target type. That should be hidden inside the transformer or at the target type on the transform() method
 */
export class V1MiningOperationsAdapterService {

    constructor(
        private readonly esiSecureAdapter: V1SecuredProxyAdapter,
        private readonly factory: BackendFactory
    ) { }

    public downloadMiningOperationsForCharacter(characterId: number): Observable<V3MiningOperation[]> {
        return this.esiSecureAdapter.v3_apiNeocomMiningOperationsData(characterId)
            .pipe(mergeMap(async (miningOperationList: V1MiningOperationDto[]) => {
                const resolveData: V3MiningOperation[] = []
                for (let miningOperation of miningOperationList) {
                    const operation: V3MiningOperation = await this.resolvePromise(miningOperation)
                    resolveData.push(operation)
                }
                return resolveData
            }))
    }
    private async resolvePromise(operation: V1MiningOperationDto): Promise<V3MiningOperation> {
        return await this.factory.construct(operation)
    }
}
