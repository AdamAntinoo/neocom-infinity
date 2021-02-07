// - APP
import { AuthenticationStateResponse } from "./AuthenticationStateResponse.dto"

describe('CLASS SessionStateResponse [Module: DOMAIN]', () => {
    beforeEach(() => {
    })

    // - C O V E R A G E   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            expect(new AuthenticationStateResponse()).toBeTruthy()
        })
        it('initial state', () => {
            let state = new AuthenticationStateResponse()
            expect(state.state).toBeUndefined()
            state = new AuthenticationStateResponse({ state: 'valid' })
            expect(state.state).toBeDefined()
            expect(state.state).toBe('valid')
        })
    })
})
