// - TESTING
import { TestBed } from '@angular/core/testing'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
// - DOMAIN
import { EveItemDto } from './EveItemDto.dto'

describe('CLASS EveItemDto [Module: DTO]', () => {
    let isolation: SupportIsolationService

    beforeEach(() => {
        isolation = TestBed.get(SupportIsolationService)
    })
    const testData = {
        "industryGroup": "HULL",
        "item": {
            "typeId": 32880,
            "name": "Venture",
            "description": "Recognizing the dire need for a ship capable of fast operation in unsafe territories, <a href=showinfo:30//500014>ORE</a> created the Venture. It was conceived as a vessel primed and ready for any capsuleer, no matter how new to the dangers of New Eden they might be, who wishes to engage in the respectable trade of mining.\r\n\r\nThe Venture has amazing abilities to quickly drill through to the ores and gases it's after, harvesting them at the speed necessary for mining in hostile space, and getting out relatively unscathed.",
            "groupId": 25,
            "marketGroupId": 1616,
            "capacity": 50.0,
            "mass": 1200000.0,
            "packagedVolume": 2500.0,
            "volume": 29500.0
        },
        "group": {
            "groupId": 25,
            "groupName": "Frigate",
            "groupCategoryId": 6
        },
        "category": {
            "categoryId": 6,
            "categoryName": "Ship"
        },
        "name": "Venture",
        "typeId": 32880,
        "categoryName": "Ship",
        "urlforItem": "https://image.eveonline.com/Type/32880_64.png",
        "hullGroup": "frigate",
        "groupName": "Frigate"
    }
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('constructor.none: validate initial state without constructor', () => {
            const instance = new EveItemDto()
            expect(instance).toBeDefined()
            expect(instance.industryGroup).toBeUndefined()
            expect(instance.item).toBeUndefined()
            expect(instance.group).toBeUndefined()
            expect(instance.category).toBeUndefined()
            expect(instance.name).toBeUndefined()
            expect(instance.typeId).toBeUndefined()
            expect(instance.categoryName).toBeUndefined()
            expect(instance.urlforItem).toBeUndefined()
            expect(instance.hullGroup).toBeUndefined()
            expect(instance.groupName).toBeUndefined()
        })
        it('constructor.none: validate initial state with object data', () => {
            const instance = new EveItemDto(testData)
            expect(instance).toBeDefined()
            expect(instance.industryGroup).toBe('HULL')
            expect(instance.item).toBeDefined()
            expect(instance.group).toBeDefined()
            expect(instance.category).toBeDefined()
            expect(instance.name).toBe('Venture')
            expect(instance.typeId).toBe(32880)
            expect(instance.categoryName).toBe('Ship')
            expect(instance.urlforItem).toBe('https://image.eveonline.com/Type/32880_64.png')
            expect(instance.hullGroup).toBe('frigate')
            expect(instance.groupName).toBe('Frigate')
        })
    })
    // - G E T T E R   P H A S E
    describe('Getter Phase', () => {
        it('Validate defined getters', () => {
            const expected = isolation.generateRandomString(32)
            const expectedUrl = isolation.generateRandomString(64)
            const instance = new EveItemDto({
                item: {
                    name: expected
                },
                urlforItem: expectedUrl
            })
            expect(instance).toBeDefined()
            expect(instance.getName()).toBe(expected)
            expect(instance.getURLIcon()).toBe(expectedUrl)
        })
    })
    // - I D T O C O M P L I A N T
    describe('IDtoCompliant Interface', () => {
        it('transform.success: convert a field into a typescript instance', () => {
            const instance = new EveItemDto(testData)
            expect(instance.group).toBeDefined()
            instance.transform
            expect(instance.group).toBeDefined()
        })
    })
})
