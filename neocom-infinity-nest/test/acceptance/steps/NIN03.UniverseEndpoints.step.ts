import { Before, Given, Then, When, setWorldConstructor } from '@cucumber/cucumber';
import { expect } from 'expect';
import { NIN01World } from '../worlds/NIN01World';
import { GetCharactersCharacterIdMining200Ok } from 'application/domain/esi-model/getCharactersCharacterIdMining200Ok';
import { V1EsiTypeDto, V1MarketDataDto, V1MiningOperationDto } from 'neocom-domain';
import { V1MiningResourceDto } from 'neocom-domain';

Given('a request for EsiType with type {int}', function (this: NIN01World, typeId: number) {
    expect(typeId).toBeDefined()
    this.esiTypeId = typeId
})
Given('a request for Region {int}', function (this: NIN01World, region: number) {
    expect(region).toBeDefined()
    this.region=region
    })
Then('the esi response has a EsiType with the next fields', function (this: NIN01World,dataTable) {
    const row = dataTable.hashes()[0]
    expect(row).toBeDefined
    expect(this.esiTypeInformationResponse).toBeDefined()
    const type: V1EsiTypeDto = this.esiTypeInformationResponse
    expect(type).toBeDefined()
    expect(validatEsiTypeInformation(row, type)).toBeTruthy()
  })
  Then('the esi response has a MarketData with the next fields', function (this: NIN01World,dataTable) {
    const row = dataTable.hashes()[0]
    expect(row).toBeDefined
    expect(this.esiMarketDataResponse).toBeDefined()
    const marketData: V1MarketDataDto = this.esiMarketDataResponse
    expect(marketData).toBeDefined()
    expect(validateMarketData(row, marketData)).toBeTruthy()
  })
//-----

function validatEsiTypeInformation(row: any, esiType: V1EsiTypeDto): boolean {
    expect(Number(row['typeId'])).toBe(esiType.typeId)
    expect(row['typeName']).toBe(esiType.name)
    expect(Number(row['iconId'])).toBe(esiType.iconId)
    expect(Number(row['groupId'])).toBe(esiType.groupId)
    expect(row['groupName']).toBe(esiType.groupName)
    expect(Number(row['categoryId'])).toBe(esiType.categoryId)
    expect(row['categoryName']).toBe(esiType.categoryName)
    expect(Number(row['volume'])).toBe(esiType.volume)
    expect(row['marketDataLink']).toBe(esiType.marketDataLink)
    return true
}
function validateMarketData(row: any, marketData: V1MarketDataDto): boolean {
    expect(Number(row['buy.price'])).toBe(marketData.buyPrice)
    expect(Number(row['buy.orders'])).toBe(marketData.buyOrderCount)
    expect(Number(row['sell.price'])).toBe(marketData.sellPrice)
    expect(Number(row['sell.orders'])).toBe(marketData.sellOrderCount)
    return true
}
