// - APP
import { EsiType } from "./EsiType.esi"

describe('CLASS EsiType [Module: DOMAIN.ESI]', () => {
    let testType: EsiType
    beforeEach(() => {
        testType = new EsiType({
            "typeId": 11535,
            "name": "Magnetometric Sensor Cluster",
            "description": "Description",
            "group": {
                "groupId": 334,
                "groupName": "Construction Components",
                "groupCategoryId": 17
            },
            "category": {
                "categoryId": 17,
                "categoryName": "Commodity"
            },
            "type": {
                "typeId": 11535,
                "name": "Magnetometric Sensor Cluster",
                "description": "Sensor Component used primarily in Gallente ships. A component in various other technology as well.  ",
                "groupId": 334,
                "marketGroupId": 1888,
                "capacity": 0.0,
                "mass": 1.0,
                "packagedVolume": 1.0,
                "volume": 1.0
            },
            "tech": "Tech I",
            "volume": 1.0,
            "isBlueprint": false,
            "typeIconURL": "https://image.eveonline.com/Type/11535_64.png",
            "marketData": {
                "rel": "marketData",
                "href": "http://localhost:5242/api/v1/universe/market/consolidated/byregion/10000043/11535"
            }
        })
    })

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            expect(new EsiType()).toBeTruthy()
        })
        it('Initial state', () => {
            const type = new EsiType()
            const typeAsAny = type as any
            expect(typeAsAny.typeId).toBeUndefined()
            expect(type.name).toBeUndefined()
            expect(type.description).toBeUndefined()
            expect(type.group).toBeUndefined()
            expect(type.category).toBeUndefined()
            expect(type.type).toBeUndefined()
            expect(type.tech).toBeUndefined()
            expect(type.volume).toBeUndefined()
            expect(type.isBlueprint).toBeFalse()
            expect(type.typeIconURL).toBeUndefined()
            expect(type.marketData).toBeUndefined()
        })
        it('Complete construction', () => {
            const typeAsAny = testType as any
            expect(typeAsAny.typeId).toBe(11535)
            expect(testType.name).toBe("Magnetometric Sensor Cluster")
            expect(testType.description).toBe("Description")
            expect(testType.group).toBeDefined()
            expect(testType.category).toBeDefined()
            expect(testType.type).toBeDefined()
            expect(testType.tech).toBe("Tech I")
            expect(testType.volume).toBe(1.0)
            expect(testType.isBlueprint).toBeFalse()
            expect(testType.typeIconURL).toBe("https://image.eveonline.com/Type/11535_64.png")
            expect(testType.marketData).toBeUndefined()
        })
    })
    // - G E T T E R   C O N T R A C T
    describe('Getter Contract', () => {
        it('getName: check the name field', () => {
            expect(testType.getName()).toBe("Magnetometric Sensor Cluster")
        })
        it('getTypeId: check the type if for the instance', () => {
            expect(testType.getTypeId()).toBe(11535)
        })
        it('getTypeIconURL: check the type icon url', () => {
            expect(testType.getTypeIconURL()).toBe("https://image.eveonline.com/Type/11535_64.png")
        })
    })
})
