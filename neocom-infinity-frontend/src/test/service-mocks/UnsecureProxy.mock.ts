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
                        "constellation_id": 20000516,
                        "name": "Merz",
                        "planets": [
                            {
                                "asteroid_belts": [
                                    40224608
                                ],
                                "planet_id": 40224607
                            },
                            {
                                "asteroid_belts": [
                                    40224610,
                                    40224611
                                ],
                                "planet_id": 40224609
                            },
                            {
                                "moons": [
                                    40224613
                                ],
                                "planet_id": 40224612
                            },
                            {
                                "planet_id": 40224614
                            },
                            {
                                "moons": [
                                    40224616
                                ],
                                "planet_id": 40224615
                            },
                            {
                                "asteroid_belts": [
                                    40224623,
                                    40224635,
                                    40224637
                                ],
                                "moons": [
                                    40224618,
                                    40224619,
                                    40224620,
                                    40224621,
                                    40224622,
                                    40224624,
                                    40224625,
                                    40224626,
                                    40224627,
                                    40224628,
                                    40224629,
                                    40224630,
                                    40224631,
                                    40224632,
                                    40224633,
                                    40224634,
                                    40224636
                                ],
                                "planet_id": 40224617
                            },
                            {
                                "asteroid_belts": [
                                    40224641
                                ],
                                "moons": [
                                    40224639,
                                    40224640
                                ],
                                "planet_id": 40224638
                            },
                            {
                                "asteroid_belts": [
                                    40224643
                                ],
                                "moons": [
                                    40224644,
                                    40224645
                                ],
                                "planet_id": 40224642
                            }
                        ],
                        "position": {
                            "x": -152138598390024900,
                            "y": 88708866628344240,
                            "z": -97916358241993840
                        },
                        "security_class": "B1",
                        "security_status": 0.6561367511749268,
                        "star_id": 40224606,
                        "stargates": [
                            50005259,
                            50005260,
                            50005261,
                            50005262
                        ],
                        "system_id": 30003538
                    }))
                 })
            }
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
        }
    }

}
