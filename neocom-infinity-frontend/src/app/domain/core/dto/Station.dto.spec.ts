// - TESTING
import { TestBed } from '@angular/core/testing'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
// - DOMAIN
import { StationDto } from './StationDto.dto'

describe('CLASS StationDto [Module: DTO]', () => {
    let isolation: SupportIsolationService

    beforeEach(() => {
        isolation = TestBed.get(SupportIsolationService)
    })
    const testData = {
        "jsonClass": "Station",
        "locationId": 60006724,
        "regionId": 10000020,
        "regionName": "Tash-Murkon",
        "constellationId": 20000248,
        "constellationName": "Mimishia",
        "systemId": 30001677,
        "systemName": "Thashkarai",
        "stationId": 60006724,
        "stationName": "Thashkarai VI - Zoar and Sons Factory"
    }
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('constructor.none: validate initial state without constructor', () => {
            const instance = new StationDto()
            expect(instance).toBeDefined()
            expect(instance.locationId).toBeUndefined()
            expect(instance.regionId).toBeUndefined()
            expect(instance.regionName).toBeUndefined()
            expect(instance.constellationId).toBeUndefined()
            expect(instance.constellationName).toBeUndefined()
            expect(instance.systemId).toBeUndefined()
            expect(instance.systemName).toBeUndefined()
            expect(instance.stationId).toBeUndefined()
            expect(instance.stationName).toBeUndefined()
        })
        it('constructor.none: validate initial state with object data', () => {
            const instance = new StationDto(testData)
            expect(instance).toBeDefined()
            expect(instance.locationId).toBe(60006724)
            expect(instance.regionId).toBe(10000020)
            expect(instance.regionName).toBe('Tash-Murkon')
            expect(instance.constellationId).toBe(20000248)
            expect(instance.constellationName).toBe('Mimishia')
            expect(instance.systemId).toBe(30001677)
            expect(instance.systemName).toBe('Thashkarai')
            expect(instance.stationId).toBe(60006724)
            expect(instance.stationName).toBe('Thashkarai VI - Zoar and Sons Factory')
        })
    })
    // - G E T T E R   P H A S E
    describe('Getter Phase', () => {
        it('Validate defined getters', () => {
            const expected = isolation.generateRandomString(32)
            const expectedUrl = isolation.generateRandomString(64)
            const instance = new StationDto({
                stationName: expected
            })
            expect(instance).toBeDefined()
            expect(instance.getStationName()).toBe(expected)
        })
    })
    // - I D T O C O M P L I A N T
    describe('IDtoCompliant Interface', () => {
        it('transform.success: convert a field into a typescript instance', () => {
            const instance = new StationDto(testData)
            expect(instance.locationId).toBeDefined()
            instance.transform
            expect(instance.locationId).toBeDefined()
        })
    })
})
