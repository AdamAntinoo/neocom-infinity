import { LookupSolarSystem } from "@app/modules/planetary/domain/LookupSolarSystem.domain";
import { UnsecuredProxyPort } from "@app/ports/UnsecuredProxy.port";
import { V1SpaceLocation } from "@domain/esi/v1.SpaceLocation.domain";
import { V1EsiTypeDto, V1MarketDataDto, V1SpaceLocationDto } from "neocom-domain";

export class UnsecureProxyMock implements UnsecuredProxyPort {
    public apiv3_GetUnsecuredLink(link: string): Promise<any> {
        console.log('proxy>link->' + link)

        switch (link) {
            case '/esi/v1/universe/spacelocation/30003538': {
                return new Promise<V1SpaceLocationDto>((resolve) => {
                    resolve(new V1SpaceLocationDto({
                        jsonClass: 'SpaceLocationDto',
                        refenceType: 'Region',
                        regionId: 10000002,
                        regionName: 'The Forge',
                        constellationId: 20000020,
                        constellationName: 'Kimotoro',
                        systemId: 30000142,
                        systemName: 'Jita'
                    }))
                })
            }
            case'/esi/v1/universe/types/17459':
            case '/esi/v1/universe/types/1224':
            case '/esi/v1/universe/types/17464': {
                return new Promise<V1EsiTypeDto>((resolve) => {
                    resolve(new V1EsiTypeDto({
                        jsonClass: 'EsiType',
                        typeId: 17464,
                        name: 'Scordite',
                        description: '-scrdite-description-',
                        iconId: 34,
                        groupId: 200,
                        groupName: '-gorup-name-',
                        categoryId: 2,
                        categoryName: '-category-name-',
                        volume: 0.1,
                        marketDataLink: '/link/marketData/45'
                    }))
                })
            }
            case '/link/marketData/45': {
                return new Promise<V1MarketDataDto>((resolve) => {
                    console.log('resolving market data')
                    resolve(new V1MarketDataDto({
                        jsonClass: 'MarketData',
                        buyPrice: 14,
                        buyOrderCount: 7,
                        sellPrice: 18.9,
                        sellOrderCount: 8
                    }))
                })
            }
            case '/link/stack/45': {
                return new Promise<V1EsiTypeDto>((resolve) => {
                    resolve(new V1EsiTypeDto({
                        jsonClass: 'EsiType',
                        typeId: 17464,
                        name: 'Scordite',
                        description: '-scrdite-description-',
                        iconId: 34,
                        groupId: 200,
                        groupName: '-gorup-name-',
                        categoryId: 2,
                        categoryName: '-category-name-',
                        volume: 0.1,
                        marketDataLink: '/marketData/link'
                    }))
                })
            }
            default:
                console.log('LINK not found->'+link)
        }
    }

}
