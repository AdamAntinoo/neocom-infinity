import { V2MiningOperation } from "./V2.MiningOperation"

describe('CLASS MiningOperation [Module: Domain - Version: v2]', () => {
    // - C O N S T R U C T I O N   P H A S E
    describe('Constructor Phase', () => {
        it('Should be created', () => {
            console.log('><[MiningOperation]> should be created')
            expect(new V2MiningOperation()).toBeDefined
        })
        it('when constructed with no data', () => {
            const sut: V2MiningOperation = new V2MiningOperation()
            expect(sut).toBeDefined
            expect(sut.jsonClass).toBe('MiningOperation')
            expect(sut.id).toBeUndefined
            expect(sut.date).toBeUndefined
            expect(sut.quantity).toBe(0)
            expect(sut.solarSystem).toBeUndefined
            expect(sut.typeId).toBeUndefined
        })
        it('when constructed with data', () => {
            const sut: V2MiningOperation = new V2MiningOperation({
                id: '-test-id-',
                date: '2024/01/01',
                quantity: 432,
                solarSystem: '-solarsystem-url-',
                typeId: '-type-url-'
            })
            expect(sut).toBeDefined
            expect(sut.jsonClass).toBe('MiningOperation')
            expect(sut.id).toBe('-test-id-')
            expect(sut.date).toBe('2024/01/01')
            expect(sut.quantity).toBe(432)
            expect(sut.solarSystem).toBe('-solarsystem-url-')
            expect(sut.typeId).toBe('-type-url-')
        })
    })
    describe('Implementation phase', () => {
        it('instance implements the required interfaces', () => {
            const sut: V2MiningOperation = new V2MiningOperation()
            expect(sut).toBeDefined
            expect(sut.transform).toBeDefined
        })
        it('when transformation contents do not change', () => {
            const instance: V2MiningOperation = new V2MiningOperation({
                id: '-test-id-',
                date: '2024/01/01',
                quantity: 432,
                solarSystem: '-solarsystem-url-',
                typeId: '-type-url-'
            })
            const sut: V2MiningOperation = instance.transform()
            expect(sut).toBeDefined
            expect(sut.jsonClass).toBe('MiningOperation')
            expect(sut.id).toBe('-test-id-')
            expect(sut.date).toBe('2024/01/01')
            expect(sut.quantity).toBe(432)
            expect(sut.solarSystem).toBe('-solarsystem-url-')
            expect(sut.typeId).toBe('-type-url-')
        })
    })
})
