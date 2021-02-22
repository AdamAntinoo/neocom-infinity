// - TESTING
import { TestBed } from '@angular/core/testing'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
import { NeoCom } from '@domain/NeoCom.domain'
import { ISelectable } from './interfaces/ISelectable.interface'
// - DOMAIN
import { NeoComException } from './NeoComException'
import { SingleSelectionManager } from './SingleSelectionManager'
export class TestSelectable implements ISelectable {
    private selectState: boolean = false
    public selectionContent: string
    toggleSelected(): boolean {
        this.selectState = !this.selectState
        return this.selectState
    }
    isSelected(): boolean {
        return this.selectState
    }
    select(): void {
        this.selectState = true
    }
    unselect(): void {
        this.selectState = false
    }
}
fdescribe('CLASS SingleSelectionManager [Module: INNOVATIVE-CORE]', () => {
    let isolation: SupportIsolationService

    beforeEach(() => {
        isolation = TestBed.inject<SupportIsolationService>(SupportIsolationService)
    })
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Contract', () => {
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
    describe('Getters Contract', () => {
        it('getSelection.success: validate the content of the selection', () => {
            const selectionManager = new SingleSelectionManager()
            expect(selectionManager.getSelection()).toEqual([])

            const expected = new TestSelectable()
            expected.selectionContent = isolation.generateRandomString(32)
            console.log('a expected>' + JSON.stringify(expected))
            let selected = selectionManager.updateSelection(expected)
            console.log('a selected>' + JSON.stringify(selected))
            let obtained = selectionManager.getSelection()
            console.log('a obtained>' + JSON.stringify(obtained))
            expect(obtained).toBeDefined()
            expect(obtained.length).toBe(0)
            expect(selectionManager.getSelection()).toEqual([])

            expected.select()
            selected = selectionManager.updateSelection(expected)
            console.log('b selected>' + JSON.stringify(selected))
            obtained = selectionManager.getSelection()
            console.log('b obtained>' + JSON.stringify(obtained))
            expect(obtained).toBeDefined()
            expect(obtained.length).toBe(1)
            expect(selectionManager.getSelection()).toEqual([expected])
        })
        it('getSelection.clear: validate the content of the selection', () => {
            const selectionManager = new SingleSelectionManager()
            expect(selectionManager.getSelection()).toEqual([])
            // Set the selection
            const expected = new TestSelectable()
            expected.selectionContent = isolation.generateRandomString(32)
            expected.select()
            console.log('a expected>' + JSON.stringify(expected))
            let selected = selectionManager.updateSelection(expected)
            console.log('a selected>' + JSON.stringify(selected))
            let obtained = selectionManager.getSelection()
            console.log('a obtained>' + JSON.stringify(obtained))
            expect(obtained).toBeDefined()
            expect(obtained.length).toBe(1)
            expect(selectionManager.getSelection()).toEqual([expected])
            // Clear the selection
            expected.unselect()
            selected = selectionManager.updateSelection(expected)
            console.log('b selected>' + JSON.stringify(selected))
            obtained = selectionManager.getSelection()
            console.log('b obtained>' + JSON.stringify(obtained))
            expect(obtained).toBeDefined()
            expect(obtained.length).toBe(0)
            expect(selectionManager.getSelection()).toEqual([])
        })
        it('getSelection.replace: validate the content of the selection', () => {
            const selectionManager = new SingleSelectionManager()
            expect(selectionManager.getSelection()).toEqual([])
            // Set the selection
            let expected = new TestSelectable()
            expected.selectionContent = isolation.generateRandomString(32)
            expected.select()
            console.log('a expected>' + JSON.stringify(expected))
            let selected = selectionManager.updateSelection(expected)
            console.log('a selected>' + JSON.stringify(selected))
            let obtained = selectionManager.getSelection()
            console.log('a obtained>' + JSON.stringify(obtained))
            expect(obtained).toBeDefined()
            expect(obtained.length).toBe(1)
            expect(selectionManager.getSelection()).toEqual([expected])
            // Clear the selection
            expected = new TestSelectable()
            expected.selectionContent = isolation.generateRandomString(32)
            expected.select()
            selected = selectionManager.updateSelection(expected)
            console.log('b selected>' + JSON.stringify(selected))
            obtained = selectionManager.getSelection()
            console.log('b obtained>' + JSON.stringify(obtained))
            expect(obtained).toBeDefined()
            expect(obtained.length).toBe(1)
            expect(selectionManager.getSelection()).toEqual([expected])
        })
    })
})
