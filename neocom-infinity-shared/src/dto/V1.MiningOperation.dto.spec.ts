import { V1MiningOperationDto } from "./V1.MiningOperation.dto"
import { V1MiningResourceDto } from "./V1.MiningResource.dto"

describe('DTO V1MiningOperationDto [Module: neocom-domain - Version: v3]', () => {
    // - C O N S T R U C T I O N   P H A S E
    describe('Constructor Contract Phase', () => {
        test('Should be created', () => {
            console.log('><[V1MiningOperationDto]> should be created')
            expect(new V1MiningOperationDto()).toBeDefined()
        })
        test('when constructed with no data', () => {
            try {
                new V1MiningOperationDto.Builder().build()
            } catch (e: any) {
                expect(e.message).toEqual('The mandatory field on a Builder is missing.')
            }
        })
        test('when constructed with builder', () => {
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
            expect(sut.getResources().length).toBe(0)
        })
        test('when constructed with builder resources', () => {
            const sut: V1MiningOperationDto = new V1MiningOperationDto.Builder()
                .withId('-test-id-')
                .withDate('2024/01/01')
                .withSolarSystem(432)
                .addMiningResource(new V1MiningResourceDto({
                    id: '-id-'
                }))
                .addMiningResource(new V1MiningResourceDto({
                    id: '-id-'
                }))
                .build()
            expect(sut).toBeDefined
            expect(sut.jsonClass).toBe('MiningOperationDto')
            expect(sut.id).toBe('-test-id-')
            expect(sut.date).toBe('2024/01/01')
            expect(sut.solarSystemId).toBe(432)
            expect(sut.getResources().length).toBe(2)
        })
        test('when constructed with partial data', () => {
            const sut: V1MiningOperationDto = new V1MiningOperationDto.Builder({
                solarSystemId: 145
            })
                .withId('-test-id-')
                .withDate('2024/01/01')
                .build()
            expect(sut).toBeDefined
            expect(sut.jsonClass).toBe('MiningOperationDto')
            expect(sut.id).toBe('-test-id-')
            expect(sut.date).toBe('2024/01/01')
            expect(sut.solarSystemId).toBe(145)
            expect(sut.getResources().length).toBe(0)
        })
    })
})
