// - TESTING
import { TestBed } from '@angular/core/testing'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
// - DOMAIN
import { TradeHistoryData } from './TradeHistoryData.domain'

describe('CLASS TradeHistoryData [Module: LOYALTY]', () => {
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[domain/Fitting]> should be created')
            expect(new TradeHistoryData()).toBeDefined()
        })
        it('Initial state', () => {
            const history = new TradeHistoryData()
            expect(history.name).toBeUndefined()
            expect(history.value).toBeUndefined()
        })
        it('Complete construction', () => {
            const history = new TradeHistoryData({
                name: "-NAME-",
                value: 324
            })
            expect(history.name).toBe("-NAME-")
            expect(history.value).toBe(324)
        })
    })
})
