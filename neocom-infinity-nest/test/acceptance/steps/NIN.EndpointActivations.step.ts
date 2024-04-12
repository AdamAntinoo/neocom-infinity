import { expect } from 'expect';
import { When } from "@cucumber/cucumber"
import { NIN01World } from "../worlds/NIN01World"
import { V2MiningOperation } from "neocom-domain"
import { GetCharactersCharacterIdMining200Ok } from 'application/domain/esi-model/getCharactersCharacterIdMining200Ok';
import { V1MiningOperationDto } from 'neocom-domain/dto/V1MiningOperationDto.dto';

When('the endpoint {string} is activated', async function (this: NIN01World, endpoint: string) {
    expect(endpoint).toBeDefined()
    switch (endpoint) {
        case 'esi/miningoperations': {
            // Request the output form the direct ESI service
            expect(this.esiMiningSecureService).toBeDefined()
            expect(this.characterId).toBeDefined()
            expect(this.token).toBeDefined()
            const sut: Promise<GetCharactersCharacterIdMining200Ok[]> = this.esiMiningSecureService.getMiningOperations(this.characterId, this.token)
            expect(sut).toBeDefined()
            await sut.then((esiResponse: GetCharactersCharacterIdMining200Ok[]) => {
                // Store the response at the world
                this.miningActionsResponse = esiResponse
            })
            break
        }
        case 'capsuleer/miningoperations': {
            expect(this.controller).toBeDefined()
            expect(this.characterId).toBeDefined()
            expect(this.token).toBeDefined()
             const sut: Promise<V1MiningOperationDto[]> = this.controller.getMiningOperations(this.token)
            expect(sut).toBeDefined
            // console.log(sut)
            await sut.then((data : V1MiningOperationDto[]) => {
                // console.log('then.data->' + data)
                this.miningOperationsResponse = data
            })
        }
    }
})
