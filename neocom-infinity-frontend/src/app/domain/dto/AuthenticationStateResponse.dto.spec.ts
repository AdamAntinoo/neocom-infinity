// - APP
import { NeoComCredential } from "@domain/NeoComCredential.domain"
import { AuthenticationStateResponse } from "./AuthenticationStateResponse.dto"

describe('CLASS AuthenticationStateResponse [Module: DOMAIN]', () => {
    beforeEach(() => {
    })

    // - C O V E R A G E   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            expect(new AuthenticationStateResponse()).toBeTruthy()
        })
        it('initial state', () => {
            const testState = new AuthenticationStateResponse()
            expect(testState.state).toBeDefined()
            expect(testState.state).toBe('NOT_VALID')
            expect(testState.getJwtToken()).toBeUndefined()
            expect(testState.getCredential()).toBeUndefined()
        })
        it('complete construction', () => {
            const testState = new AuthenticationStateResponse({
                state: 'valid',
                jwtToken: "-JWT-TOKEN-",
                credential: new NeoComCredential()
            })
            expect(testState.state).toBeDefined()
            expect(testState.state).toBe('valid')
            expect(testState.getJwtToken()).toBeDefined()
            expect(testState.getCredential()).toBeDefined()
        })
    })
})
