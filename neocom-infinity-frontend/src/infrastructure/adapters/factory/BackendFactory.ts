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
        console.log('03 Request factory for->' + backendData.jsonClass)
        console.log('03 Request factory with data->' + JSON.stringify(backendData))
        if (undefined == backendData.jsonClass)
            console.log('invaud class->' + JSON.stringify(backendData))
        switch (backendData.jsonClass) {
            case 'StackDto': {
                console.log('stack->' + JSON.stringify(backendData))
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
                console.log('type->' + JSON.stringify(backendData))
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
                console.log('marketData->' + JSON.stringify(backendData))
                return new V1MarketData(backendData)
            }
            case 'MiningOperationDto': {
                // console.log('case MiningOperationDto')
                const operationDto: V1MiningOperationDto = backendData as V1MiningOperationDto
                console.log('case miningOperation>backendData->' + JSON.stringify(operationDto))
                const solarSystemDto: V1SpaceLocationDto = await this.resolver.apiv3_GetUnsecuredLink<V1SpaceLocationDto>(
                    operationDto.solarSystemLink
                )
                const solarSystem: V1SpaceLocation = await this.construct(solarSystemDto)
                const resources: V1Stack[] = []
                for (let resource of operationDto.getResources()) {
                    resources.push(await this.construct(resource))
                }
                console.log('resources->' + resources.length)
                const operation: V3MiningOperation = new V3MiningOperation.Builder(backendData)
                    .withSolarSystem(solarSystem)
                    .withResources(resources)
                    .build()
                return operation
            }
            case 'SpaceLocationDto': {
                console.log('case spaceLocation->' + JSON.stringify(backendData))
                return new V1SpaceLocation(backendData)
            }
        }
    }
}
