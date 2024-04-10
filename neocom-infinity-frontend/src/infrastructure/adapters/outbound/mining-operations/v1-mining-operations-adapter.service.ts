import { Injectable } from '@angular/core';
import { ESIMiningOperation } from '../esi-data-service/domain/ESIMiningOperation';
import { ESISecureDataServiceAdapter } from '../esi-data-service/ESISecureDataServiceAdapter';
import { LookupSolarSystem } from '@app/modules/planetary/domain/LookupSolarSystem.domain';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'
import { V3MiningOperation } from '@domain/industry/V3.MiningOperation.domain';
import { UniverseType } from '@domain/esi/UniverseType.esi';
import { HALResolverService } from '@innovative/domain/HALResolver.service';
import { HALLink } from '@innovative/domain/HALLink.domain';

@Injectable({
    providedIn: 'root'
})
/**
 *  WARNING: The downloader should know the classes for the HAL links that are defined on the target type. That should be hidden inside the transformer or at the target type on the transform() method
 */
export class V1MiningOperationsAdapterService {

    constructor(
        private readonly esiSecureAdapter: ESISecureDataServiceAdapter,
        private readonly resolver: HALResolverService
    ) { }

    public downloadMiningOperationsForCharacter(characterId: number): Observable<V3MiningOperation[]> {
        return this.esiSecureAdapter.v1_apiEsiMiningOperationsData(characterId)
            .pipe(map((miningOperationList: ESIMiningOperation[]) => {
                const resolveData: V3MiningOperation[] = []
                miningOperationList.forEach((element: ESIMiningOperation) => {
                    const workItem: V3MiningOperation = new V3MiningOperation({
                        id: element.id,
                        date: element.date,
                        quantity: element.quantity,
                        solarSystem: this.newHalLinkForSolarSystem(element.solarSystem),
                        type: this.newHalLinkForType(element.typeId)
                    })
                    // console.log('workItem->' + JSON.stringify(workItem))
                    resolveData.push(workItem)
                })
                return resolveData
            }))
    }
    private newHalLinkForSolarSystem(link: string): HALLink<LookupSolarSystem> {
        return new HALLink.Builder(LookupSolarSystem)
            .withReference(link)
            .withResolver(this.resolver)
            .build()
    }
    private newHalLinkForType(link: string): HALLink<UniverseType> {
        return new HALLink.Builder(UniverseType)
            .withReference(link)
            .withResolver(this.resolver)
            .build()
    }
}
