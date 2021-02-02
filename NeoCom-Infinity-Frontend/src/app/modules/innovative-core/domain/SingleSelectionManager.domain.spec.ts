// - TESTING
import { TestBed } from '@angular/core/testing'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
// - DOMAIN
import { NeoComException } from './NeoComException'
import { SingleSelectionManager } from './SingleSelectionManager'

fdescribe('CLASS SingleSelectionManager [Module: DOMAIN]', () => {
    let isolation: SupportIsolationService

    beforeEach(() => {
        isolation = TestBed.get(SupportIsolationService)
    })
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[domain/SingleSelectionManager]> should be created')
            expect(new SingleSelectionManager()).toBeTruthy()
        })
        it('Fields should be on initial state', () => {
            const selectionManager = new SingleSelectionManager()
            const selectionManagerAsAny = selectionManager as any
            expect(selectionManagerAsAny.selection).toBeUndefined()
        })
    })
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [getters]', () => {
        it('NeoComException.getUserMessage.success: check the user message when not defined field', () => {
            const expected = isolation.generateRandomString(32)
            const exception = new NeoComException({ message: expected })
            const obtained = exception.getUserMessage()
            expect(obtained).toBe(expected)
        })
    })
})
