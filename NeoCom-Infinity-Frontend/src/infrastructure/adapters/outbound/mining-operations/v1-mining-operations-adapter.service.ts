import { Injectable } from '@angular/core';
import { ESISecureDataServicePort } from '@app/ports/ESISecureDataServicePort';
import { V1MiningOperation } from '@domain/industry/V1.MiningOperation.domain';
import { ESIMiningOperation } from '../esi-data-service/domain/ESIMiningOperation';
import { ESISecureDataServiceAdapter } from '../esi-data-service/ESISecureDataServiceAdapter';
import { HALLink } from '@domain/hal/HALLink.hal';
import { LookupSolarSystem } from '@app/modules/planetary/domain/LookupSolarSystem.domain';
import { EveItemDto } from '@domain/core/dto/EveItemDto.dto';

@Injectable({
    providedIn: 'root'
})
export class V1MiningOperationsAdapterService {

    constructor(private esiSecureAdapter: ESISecureDataServiceAdapter) { }

    public downloadMiningOperationsForCharacter(characterId: number): Promise<V1MiningOperation[]> {
        return new Promise<V1MiningOperation[]>((resolve, reject) => {
            this.esiSecureAdapter.v1_apiEsiMiningOperationsData(characterId)
                .subscribe((miningOperationList: ESIMiningOperation[]) => {
                    return new Promise<V1MiningOperation[]>((resolve, reject) => {
                        const resolveData: V1MiningOperation[] = []
                        miningOperationList.forEach((element: ESIMiningOperation) => {
                            const workItem: V1MiningOperation = new V1MiningOperation({
                                date: element.date,
                                quantity: element.quantity,
                                solarSystem: this.newHalLinkForSolarSystem(element.solar_system_id),
                                type: this.newHalLinkForType(element.type_id)
                            })
                            resolveData.push(workItem)
                        })
                        resolve(resolveData)
                    })
                })
        })
    }
    private newHalLinkForSolarSystem(solar_system_id: number): HALLink<LookupSolarSystem> {
        return new HALLink<LookupSolarSystem>(LookupSolarSystem)
            .setContents({ rel: '', href: this.generateUrlForSolarSystem(solar_system_id) })
    }
    private newHalLinkForType(type_id: number): HALLink<EveItemDto> {
        return new HALLink<EveItemDto>(EveItemDto)
            .setContents({ rel: '', href: this.generateUrlForEveItem(type_id) })
    }
    private generateUrlForSolarSystem(solar_system_id: number): string {
        return 'https://esi.evetech.net/latest/universe/systems/' + solar_system_id + '/?datasource=tranquility&language=en'
    }
    private generateUrlForEveItem(type_id: number): string {
        return 'https://esi.evetech.net/latest/universe/types/' + type_id + '/?datasource=tranquility&language=en'
    }
}
