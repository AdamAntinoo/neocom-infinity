import { LookupSolarSystem } from "@app/modules/planetary/domain/LookupSolarSystem.domain";
import { UnsecuredProxyPort } from "@app/ports/UnsecuredProxy.port";
import { V1EsiTypeDto, V1MarketDataDto } from "neocom-domain";

export class UnsecureProxyMock implements UnsecuredProxyPort {
    public apiv3_GetUnsecuredLink(link: string): Promise<any> {
        console.log('proxy>link->' + link)

        switch (link) {
            case '/link/solarSystem/3000': {
                return new Promise<LookupSolarSystem>((resolve) => {
                    resolve(new LookupSolarSystem({
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
            case '/link/esiType/45': {
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
        }
    }

}
