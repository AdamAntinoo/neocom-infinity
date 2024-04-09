import { Injectable } from '@angular/core';
import { ESIMiningOperation } from '../esi-data-service/domain/ESIMiningOperation';
import { ESISecureDataServiceAdapter } from '../esi-data-service/ESISecureDataServiceAdapter';
import { HALLink } from '@domain/hal/HALLink.hal';
import { LookupSolarSystem } from '@app/modules/planetary/domain/LookupSolarSystem.domain';
import { EveItemDto } from '@domain/core/dto/EveItemDto.dto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'
import { V3MiningOperation } from '@domain/industry/V3.MiningOperation.domain';

@Injectable({
    providedIn: 'root'
})
export class V1MiningOperationsAdapterService {

    constructor(private esiSecureAdapter: ESISecureDataServiceAdapter) { }

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
                    console.log('workItem->' + JSON.stringify(workItem))
                    resolveData.push(workItem)
                })
                return resolveData
            }))
    }
    private newHalLinkForSolarSystem(link: string): HALLink<LookupSolarSystem> {
        return new HALLink<LookupSolarSystem>(LookupSolarSystem)
            .setContents({ rel: '', href: link })
    }
    private newHalLinkForType(link: string): HALLink<EveItemDto> {
        return new HALLink<EveItemDto>(EveItemDto)
            .setContents({ rel: '', href: link })
    }
}
