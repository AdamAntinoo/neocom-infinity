import { Observable } from "rxjs";

export class HttpServiceMock {
    public get(request: string, headers?: object): any {
        console.log('HttpServiceMock>method->' + 'GET')
        console.log('HttpServiceMock>request->' + request)
        console.log('HttpServiceMock>options->' + JSON.stringify(headers))
        switch (request) {
            case '/api/v3/neocom/characters/93813310/miningoperation':
            //      {
            //     return Observable.create((observer) => {
            //         observer.next([
            //             {
            //                 "jsonClass": "MiningOperationDto",
            //                 "resources": [
            //                     {
            //                         "jsonClass": "StackDto",
            //                         "quantity": 10000,
            //                         "typeLink": "/esi/v1/universe/types/17464"
            //                     },
            //                     {
            //                         "jsonClass": "StackDto",
            //                         "quantity": 12000,
            //                         "typeLink": "/esi/v1/universe/types/1224"
            //                     }
            //                 ],
            //                 "id": "2024-02-25/30003538",
            //                 "date": "2024-02-25",
            //                 "solarSystemLink": "/esi/v1/universe/spacelocation/30003538"
            //             }]
            //         )
            //         observer.complete()
            //     })
            //     break
            // }
            case '/api/v3/neocom/characters/776665/miningoperation': {
                return Observable.create((observer) => {
                    observer.next([
                        {
                            "jsonClass": "MiningOperationDto",
                            "resources": [
                                {
                                    "jsonClass": "StackDto",
                                    "quantity": 10000,
                                    "typeLink": "/esi/v1/universe/types/17464"
                                },
                                {
                                    "jsonClass": "StackDto",
                                    "quantity": 12000,
                                    "typeLink": "/esi/v1/universe/types/1224"
                                }
                            ],
                            "id": "2024-02-25/30003538",
                            "date": "2024-02-25",
                            "solarSystemLink": "/esi/v1/universe/spacelocation/30003538"
                        },
                        {
                            "jsonClass": "MiningOperationDto",
                            "resources": [
                                {
                                    "jsonClass": "StackDto",
                                    "quantity": 210,
                                    "typeLink": "/esi/v1/universe/types/17453"
                                }
                            ],
                            "id": "2024-02-23/30003541",
                            "date": "2024-02-23",
                            "solarSystemLink": "/esi/v1/universe/spacelocation/30003541"
                        },
                        {
                            "jsonClass": "MiningOperationDto",
                            "resources": [
                                {
                                    "jsonClass": "StackDto",
                                    "quantity": 34243,
                                    "typeLink": "/esi/v1/universe/types/17464"
                                },
                                {
                                    "jsonClass": "StackDto",
                                    "quantity": 3073,
                                    "typeLink": "/esi/v1/universe/types/1224"
                                },
                                {
                                    "jsonClass": "StackDto",
                                    "quantity": 10288,
                                    "typeLink": "/esi/v1/universe/types/17459"
                                }
                            ],
                            "id": "2024-02-23/30003538",
                            "date": "2024-02-23",
                            "solarSystemLink": "/esi/v1/universe/spacelocation/30003538"
                        }
                    ])
                    observer.complete()
                })
            }
        }
    }
}
