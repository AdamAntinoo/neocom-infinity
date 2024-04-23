import { UnsecuredProxy } from "@adapter/outbound/UnsecuredProxy/V1.UnsecuredProxy.adapter";
import { Injectable } from "@angular/core";
import { V1MarketData } from "@domain/esi/V1.MarketData.domain";
import { V1Stack } from "@domain/esi/V1.Stack.domain";
import { V2UniverseType } from "@domain/esi/V2.UniverseType.domain";
import { V1SpaceLocation } from "@domain/esi/v1.SpaceLocation.domain";
import { V3MiningOperation } from "@domain/industry/V3.MiningOperation.domain";
import { Record, V1EsiTypeDto, V1MarketDataDto, V1MiningOperationDto, V1SpaceLocationDto, V1StackDto } from "neocom-domain";

@Injectable({
    providedIn: 'root'
})
export class BackendFactory {
    constructor(private readonly resolver: UnsecuredProxy) { }

    public async construct(backendData: Record): Promise<any> {
        // Use the jsonClass to determine the class to construct.
        if (undefined == backendData) {
            console.log('BackendFactory>undefined request')
            return new Promise<string>((resolve) => {
                resolve('UNDEFINED')
            })
        }
        console.log('03 Request factory with data->' + JSON.stringify(backendData))
        if (undefined == backendData['jsonClass']) {
            console.log('BackendFactory>invalid class->' + JSON.stringify(backendData))
            return new Promise<string>((resolve) => {
                resolve('INVALID')
            })
        }
        console.log('03 Request factory for->' + backendData.jsonClass)
        switch (backendData.jsonClass) {
            case 'StackDto': {
                console.log('BackendFactory>case stack->' + JSON.stringify(backendData))
                const stackDto: V1StackDto = backendData
                const typeDto: V1EsiTypeDto = await this.resolver.apiv3_GetUnsecuredLink<V1EsiTypeDto>(stackDto.typeLink)
                const type: V2UniverseType = await this.construct(typeDto)
                const stack: V1Stack = new V1Stack({
                    quantity: stackDto.quantity,
                    type: type
                })
                return stack
            }
            case 'EsiType': {
                console.log('BackendFactory>case type->' + JSON.stringify(backendData))
                const esiType: V1EsiTypeDto = backendData
                const marketDataDto: V1MarketDataDto = await this.resolver.apiv3_GetUnsecuredLink<V1MarketDataDto>(
                    esiType.marketDataLink
                )
                const marketData: V1MarketData = await this.construct(marketDataDto)
                const type: V2UniverseType = new V2UniverseType(esiType).withMarketData(
                    marketData
                )
                return type
            }
            case 'MarketData': {
                console.log('BackendFactory>marketData->' + JSON.stringify(backendData))
                return new V1MarketData(backendData)
            }
            case 'MiningOperationDto': {
                console.log('BackendFactory>case miningOperation->' + JSON.stringify(backendData))
                const operationDto: V1MiningOperationDto = backendData as V1MiningOperationDto
                const solarSystemDto: V1SpaceLocationDto = await this.resolver.apiv3_GetUnsecuredLink<V1SpaceLocationDto>(
                    operationDto.solarSystemLink
                )
                const solarSystem: V1SpaceLocation = await this.construct(solarSystemDto)
                const resources: V1Stack[] = []
                for (let resource of operationDto.resources) {
                    resources.push(await this.construct(resource))
                }
                const operation: V3MiningOperation = new V3MiningOperation.Builder(backendData)
                    .withSolarSystem(solarSystem)
                    .withResources(resources)
                    .build()
                return operation
            }
            case 'SpaceLocationDto': {
                console.log('BackendFactory>case spaceLocation->' + JSON.stringify(backendData))
                return new V1SpaceLocation(backendData)
            }
        }
    }
}
