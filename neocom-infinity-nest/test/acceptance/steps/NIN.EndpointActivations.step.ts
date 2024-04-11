import { expect } from 'expect';
import { When } from "@cucumber/cucumber"
import { NIN01World } from "../worlds/NIN01World"
import { V2MiningOperation } from "neocom-domain"

When('the endpoint {string} is activated', async function (this: NIN01World, endpoint: string) {
    expect(endpoint).toBeDefined()
    switch (endpoint) {
        case 'esi/miningoperations': {
            // Request the output form the direct ESI service
            expect(this.esiMiningSecureService).toBeDefined()
            expect(this.characterId).toBeDefined()
            expect(this.token).toBeDefined()
            const sut: Promise<V2MiningOperation[]> = this.esiMiningSecureService.getMiningOperations(this.characterId, this.token)
            expect(sut).toBeDefined()
          await  sut.then((esiResponse: V2MiningOperation[]) => {
                // Store the response at the world
                this.miningActionsResponse = esiResponse
            })
            break
        }
        case 'ff': {
            expect(this.controller).toBeDefined()
            const pilotId: number = 423
            const sut: Promise<V2MiningOperation[]> = this.controller.getMiningOperations(this.token)
            expect(sut).toBeDefined
            // console.log(sut)
            await sut.then(data => {
                // console.log('then.data->' + data)
                this.miningActionsResponse = data
            })
        }
    }
})
