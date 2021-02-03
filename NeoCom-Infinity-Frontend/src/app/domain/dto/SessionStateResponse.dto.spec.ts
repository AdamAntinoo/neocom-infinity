// - APP
import { SessionStateResponse } from "./SessionStateResponse.dto"

describe('CLASS SessionStateResponse [Module: DOMAIN]', () => {
    beforeEach(() => {
    })

    // - C O V E R A G E   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            expect(new SessionStateResponse()).toBeTruthy()
        })
        it('initial state', () => {
            let state = new SessionStateResponse()
            expect(state.state).toBeUndefined()
            state = new SessionStateResponse({ state: 'valid' })
            expect(state.state).toBeDefined()
            expect(state.state).toBe('valid')
        })
    })
})
