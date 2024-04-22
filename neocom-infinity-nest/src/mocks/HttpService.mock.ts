import { HttpService } from "@nestjs/axios"
import { Injectable } from "@nestjs/common"
import { AxiosInstance, AxiosRequestConfig, AxiosResponse, AxiosPromise } from "axios"
import { Observable } from "rxjs"

@Injectable()
export class HttpServiceMock {
    // protected instance: AxiosInstance
    // request<T = any>(config: AxiosRequestConfig<any>): Observable<AxiosResponse<T, any>> {
    //     throw new Error("Method not implemented.")
    // }
    // delete<T = any>(url: string, config?: AxiosRequestConfig<any>): Observable<AxiosResponse<T, any>> {
    //     throw new Error("Method not implemented.")
    // }
    // head<T = any>(url: string, config?: AxiosRequestConfig<any>): Observable<AxiosResponse<T, any>> {
    //     throw new Error("Method not implemented.")
    // }
    // post<T = any>(url: string, data?: any, config?: AxiosRequestConfig<any>): Observable<AxiosResponse<T, any>> {
    //     throw new Error("Method not implemented.")
    // }
    // put<T = any>(url: string, data?: any, config?: AxiosRequestConfig<any>): Observable<AxiosResponse<T, any>> {
    //     throw new Error("Method not implemented.")
    // }
    // patch<T = any>(url: string, data?: any, config?: AxiosRequestConfig<any>): Observable<AxiosResponse<T, any>> {
    //     throw new Error("Method not implemented.")
    // }
    // get axiosRef(): AxiosInstance {
    //     throw new Error("Method not implemented.")
    // }
    // protected makeObservable<T>(axios: (...args: any[]) => AxiosPromise<T>, ...args: any[]): Observable<AxiosResponse<T, any>> {
    //     throw new Error("Method not implemented.")
    // }

    public get<T = any>(request: string, config?: AxiosRequestConfig<any>): Observable<AxiosResponse<T, any>> {
        switch (request) {
            case 'https://esi.evetech.net/latest/universe/types/17464': {
                return new Observable((observer) => {
                    observer.next({
                        data: {
                            "capacity": 0,
                            "description": "Massive Scordite was the stuff of legend in the early days of space exploration, due to a 10% higher mineral yield than standard scordite. Though it has long since been taken over in value by other ores it still has a special place in the hearts of veteran miners.\r\n\r\nScordite is amongst the most common ore types in the central regions of known universe. It has a fair bit of <a href=showinfo:34>Tritanium</a> and <a href=showinfo:35>Pyerite</a>. Good choice for those starting their mining careers.\r\n\r\nAvailable primarily in high security status star systems.",
                            "group_id": 460,
                            "icon_id": 1356,
                            "market_group_id": 519,
                            "mass": 1e+35,
                            "name": "Massive Scordite",
                            "packaged_volume": 0.15,
                            "portion_size": 100,
                            "published": true,
                            "radius": 1,
                            "type_id": 17464,
                            "volume": 0.15
                        }
                    } as AxiosResponse)
                })
            }
            case 'https://esi.evetech.net/latest/universe/groups/17464': {
                return new Observable((observer) => {
                    observer.next({
                        data: {
                            "category_id": 25,
                            "group_id": 460,
                            "name": "Scordite",
                            "published": true,
                            "types": [
                                1228,
                                17463,
                                17464,
                                28427,
                                28428,
                                28429,
                                46687,
                                46703,
                                62520,
                                62521,
                                62522,
                                62523
                            ]
                        }
                    } as AxiosResponse)
                })
            }
            case 'https://esi.evetech.net/latest/universe/categories/17464': {
                return new Observable((observer) => {
                    observer.next({
                        data: {
                            "category_id": 25,
                            "name": "Asteroid",
                            "published": true
                        }
                    } as AxiosResponse)
                })
            }
            case 'http://localhost:5271/aggregates/?region=30000142&types=17464': {
                return new Observable((observer) => {
                    observer.next({
                        data: {
                            "17464": {
                                "buy": {
                                    "weightedAverage": "4.02878502065",
                                    "max": "5.95",
                                    "min": "0.01",
                                    "stddev": "1.62036217159",
                                    "median": "5.0",
                                    "volume": "10024734026.0",
                                    "orderCount": "52",
                                    "percentile": "5.50168617928"
                                },
                                "sell": {
                                    "weightedAverage": "6.60015441538",
                                    "max": "2201571.0",
                                    "min": "5.01",
                                    "stddev": "177420.733866",
                                    "median": "6.38",
                                    "volume": "25573930856.0",
                                    "orderCount": "179",
                                    "percentile": "5.92257900667"
                                }
                            }
                        }
                    } as AxiosResponse)
                })
            }
            case 'https://esi.evetech.net/latest/universe/regions/10000002/?datasource=tranquility&language=en': {
                return new Observable((observer) => {
                    observer.next({
                        data: {
                            "constellations": [
                                20000017,
                                20000018,
                                20000019,
                                20000020,
                                20000021,
                                20000022,
                                20000023,
                                20000024,
                                20000025,
                                20000026,
                                20000027,
                                20000028,
                                20000029
                            ],
                            "description": "\"The greater the State becomes, the greater humanity under it flourishes.\"",
                            "name": "The Forge",
                            "region_id": 10000002
                        }
                    } as AxiosResponse)
                })
            }
            case "https://esi.evetech.net/latest/universe/constellations/20000020/?datasource=tranquility&language=en": {
                return new Observable((observer) => {
                    observer.next({
                        data: {
                            "constellation_id": 20000020,
                            "name": "Kimotoro",
                            "position": {
                                "x": -134996400468185440,
                                "y": 64999452632293260,
                                "z": 103325617317521340
                            },
                            "region_id": 10000002,
                            "systems": [
                                30000139,
                                30000140,
                                30000141,
                                30000142,
                                30000143,
                                30000144,
                                30000145
                            ]
                        }
                    } as AxiosResponse)
                })
            }
            case "https://esi.evetech.net/latest/universe/systems/30000142/?datasource=tranquility&language=en": {
                return new Observable((observer) => {
                    observer.next({
                        data: {
                            "constellation_id": 20000020,
                            "name": "Jita",
                            "planets": [
                                {
                                    "planet_id": 40009077
                                },
                                {
                                    "planet_id": 40009078
                                },
                                {
                                    "moons": [
                                        40009081
                                    ],
                                    "planet_id": 40009080
                                },
                                {
                                    "moons": [
                                        40009083,
                                        40009084,
                                        40009085,
                                        40009087,
                                        40009088,
                                        40009089,
                                        40009090,
                                        40009091,
                                        40009092,
                                        40009093,
                                        40009094,
                                        40009097
                                    ],
                                    "planet_id": 40009082
                                },
                                {
                                    "moons": [
                                        40009099,
                                        40009100,
                                        40009101,
                                        40009102,
                                        40009103,
                                        40009104,
                                        40009105,
                                        40009106,
                                        40009107,
                                        40009108,
                                        40009109,
                                        40009110,
                                        40009111,
                                        40009112,
                                        40009113,
                                        40009114,
                                        40009115
                                    ],
                                    "planet_id": 40009098
                                },
                                {
                                    "moons": [
                                        40009118
                                    ],
                                    "planet_id": 40009116
                                },
                                {
                                    "moons": [
                                        40009121,
                                        40009122
                                    ],
                                    "planet_id": 40009119
                                },
                                {
                                    "planet_id": 40009123
                                }
                            ],
                            "position": {
                                "x": -129064861735000000,
                                "y": 60755306910000000,
                                "z": 117469227060000000
                            },
                            "security_class": "B",
                            "security_status": 0.9459131360054016,
                            "star_id": 40009076,
                            "stargates": [
                                50001248,
                                50001249,
                                50001250,
                                50013876,
                                50013913,
                                50013921,
                                50013928
                            ],
                            "stations": [
                                60000361,
                                60000364,
                                60000451,
                                60000463,
                                60002953,
                                60002959,
                                60003055,
                                60003460,
                                60003463,
                                60003466,
                                60003469,
                                60003757,
                                60003760,
                                60004423,
                                60015169
                            ],
                            "system_id": 30000142
                        }

                    } as AxiosResponse)
                })
            }
        }
    }
}
