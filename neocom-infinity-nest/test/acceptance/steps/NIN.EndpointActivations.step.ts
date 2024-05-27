import { expect } from 'expect'
import { Then, When } from "@cucumber/cucumber"
import { NIN01World } from "../worlds/NIN01World"
import { GetCharactersCharacterIdMining200Ok } from 'application/domain/esi-model/getCharactersCharacterIdMining200Ok'
import { V1EsiTypeDto, V1MarketDataDto, V1MiningOperationDto } from 'neocom-domain'
import { V1AssetDto } from 'neocom-domain'

When('the endpoint {string} is activated', async function (this: NIN01World, endpoint: string) {
    expect(endpoint).toBeDefined()
    switch (endpoint) {
        case 'esi/miningoperations': {
            // Request the output form the direct ESI service
            // expect(this.esiMiningSecureService).toBeDefined()
            expect(this.characterId).toBeDefined()
            expect(this.token).toBeDefined()
            const sut: Promise<GetCharactersCharacterIdMining200Ok[]> = this.esiSecureDataService.getMiningOperations(this.characterId, this.token)
            expect(sut).toBeDefined()
            await sut.then((esiResponse: GetCharactersCharacterIdMining200Ok[]) => {
                // Store the response at the world
                this.miningActionsResponse = esiResponse
            })
            break
        }
        case 'capsuleer/miningoperations': {
            expect(this.miningOperationsController).toBeDefined()
            expect(this.characterId).toBeDefined()
            expect(this.token).toBeDefined()
            const sut: Promise<V1MiningOperationDto[]> = this.miningOperationsController.getMiningOperations(this.token)
            expect(sut).toBeDefined
            await sut.then((data: V1MiningOperationDto[]) => {
                this.miningOperationsResponse = data
            })
            break
       }
        case 'esi/esitype': {
            expect(this.universeController).toBeDefined()
            expect(this.esiTypeId).toBeDefined()
            const sut: Promise<V1EsiTypeDto> = this.universeController.esiGetTypeInformation({typeId:this.esiTypeId})
            expect(sut).toBeDefined
            await sut.then((data: V1EsiTypeDto) => {
                this.esiTypeInformationResponse = data
            })
            break
        }
        case 'esi/marketdata': {
            expect(this.universeController).toBeDefined()
            expect(this.esiTypeId).toBeDefined()
            expect(this.region).toBeDefined()
            const sut: Promise<V1MarketDataDto> = this.universeController.esiGetMarketData({typeId:this.esiTypeId, region: this.region})
            expect(sut).toBeDefined
            await sut.then((data: V1MarketDataDto) => {
                this.esiMarketDataResponse = data
            })
            break
        }
        case 'esi/Assets': {
            expect(this.characterController).toBeDefined()
            expect(this.characterId).toBeDefined()
            expect(this.token).toBeDefined()
            const sut: Promise<V1AssetDto[]> = this.characterController.getCharactersCharacterIdAssets(this.token)
            expect(sut).toBeDefined()
            this.responseResultCode = '200 OK'
            await sut.then((data: V1AssetDto[]) => {
                this.esiAssetsResponse = data
            })
            break
        }
    }
})
Then('the response is {string}', function (this: NIN01World, responseCode:string) {
    expect( this.responseResultCode).toBeDefined()
    expect(this.responseResultCode).toBe(responseCode)
})
