import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'

import { ESISecureDataServiceAdapter } from '../esi-data-service/ESISecureDataServiceAdapter';
import { LookupSolarSystem } from '@app/modules/planetary/domain/LookupSolarSystem.domain';
import { V3MiningOperation } from '@domain/industry/V3.MiningOperation.domain';
import { UniverseType } from '@domain/esi/UniverseType.esi';
import { HALResolverService } from '@innovative/domain/HALResolver.service';
import { HALLink } from '@innovative/domain/HALLink.domain';
import { V1MiningOperationDto } from 'neocom-domain';
import { V1Stack } from '@domain/V1Stack.domain';

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
            .pipe(map((miningOperationList: V1MiningOperationDto[]) => {
                const resolveData: V3MiningOperation[] = []
                miningOperationList.forEach((element: V1MiningOperationDto) => {
                    const workItem = new V3MiningOperation.Builder({
                        id: element.id,
                        date: element.date,
                        solarSystem: this.newHalLinkForSolarSystem(element.solarSystemId),
                    })
                    for (var resource of element.getResources())
                        workItem.addResource(new V1Stack(resource))
                    resolveData.push(workItem.build())
                })
                return resolveData
            }))
    }
    private newHalLinkForSolarSystem(solarSystemId: number): HALLink<LookupSolarSystem> {
        return new HALLink.Builder(LookupSolarSystem)
            .withReference(this.getSystemLink(solarSystemId))
            .withResolver(this.resolver)
            .build()
    }
    public getSystemLink(solarSystemId: number): string {
        return 'https://esi.evetech.net/latest/universe/systems/' + solarSystemId + '/?datasource=tranquility&language=en'
    }
    private newHalLinkForType(link: string): HALLink<UniverseType> {
        return new HALLink.Builder(UniverseType)
            .withReference(link)
            .withResolver(this.resolver)
            .build()
    }
}
