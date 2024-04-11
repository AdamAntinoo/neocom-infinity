import { V1MiningOperationDto } from "./V1MiningOperationDto.dto"
import { V1MiningResourceDto } from "./V1MiningResourceDto.dto"

describe('DTO V1MiningOperationDto [Module: neocom-domain - Version: v3]', () => {
    // - C O N S T R U C T I O N   P H A S E
    describe('Constructor Contract Phase', () => {
        test('Should be created', () => {
            console.log('><[V1MiningOperationDto]> should be created')
            expect(new V1MiningOperationDto()).toBeDefined()
        })
        xtest('when constructed with no data', () => {
            const sut: V1MiningOperationDto = new V1MiningOperationDto.Builder().build()
            expect(() => {
                new V1MiningOperationDto.Builder().build()
            }).toThrow('The mandatory field on a Builder is missing.')
        })
        test('when constructed with data', () => {
            const sut: V1MiningOperationDto = new V1MiningOperationDto.Builder()
                .withId('-test-id-')
                .withDate('2024/01/01')
                .withSolarSystem(432)
                .build()
            expect(sut).toBeDefined
            expect(sut.jsonClass).toBe('MiningOperationDto')
            expect(sut.id).toBe('-test-id-')
            expect(sut.date).toBe('2024/01/01')
            expect(sut.solarSystemId).toBe(432)
        })
    })
    describe('Implementation phase', () => {
        test('add a new MiningResource', () => {
            const operation: V1MiningOperationDto = new V1MiningOperationDto.Builder()
                .withId('-test-id-')
                .withDate('2024/01/01')
                .withSolarSystem(432)
                .build()
            expect(operation).toBeDefined()
            expect(operation.getResources()).toBeDefined()
            expect(operation.getResources().length).toBe(0)
            const sut: V1MiningOperationDto = operation.addMiningResource(new V1MiningResourceDto({
                jsonClass: 'MiningResource',
                id: 'resource-id',
                date: '2024-01-01',
                quantity: 100,
                solarSystem: 'link-to-solar-system',
                typeId: 'link-to-type'
            }
            ))
            expect(sut.getResources().length).toBe(1)

            // expect(sut.transform).toBeDefined
        })
        // it('when transformation contents do not change', () => {
        //     const instance: V2MiningOperation = new V2MiningOperation({
        //         id: '-test-id-',
        //         date: '2024/01/01',
        //         quantity: 432,
        //         solarSystem: '-solarsystem-url-',
        //         typeId: '-type-url-'
        //     })
        //     const sut: V2MiningOperation = instance.transform()
        //     expect(sut).toBeDefined
        //     expect(sut.jsonClass).toBe('MiningOperation')
        //     expect(sut.id).toBe('-test-id-')
        //     expect(sut.date).toBe('2024/01/01')
        //     expect(sut.quantity).toBe(432)
        //     expect(sut.solarSystem).toBe('-solarsystem-url-')
        //     expect(sut.typeId).toBe('-type-url-')
        // })
    })
})
