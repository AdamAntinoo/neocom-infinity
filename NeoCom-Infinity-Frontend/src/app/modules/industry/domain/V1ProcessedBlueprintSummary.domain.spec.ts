// - DOMAIN
import { V1ProcessedBlueprintSummary } from './V1ProcessedBlueprintSummary.domain'

describe('CLASS V1ProcessedBlueprintSummary [Module: DOMAIN]', () => {
    let testBlueprint: V1ProcessedBlueprintSummary

    beforeEach(() => {
        testBlueprint = new V1ProcessedBlueprintSummary({
            "uid": "BCI:93813310:31118",
            "blueprintTypeId": 31118,
            "blueprintTypeName": "Small Cargohold Optimization I Blueprint",
            "blueprintTypeIconURL": "https://image.eveonline.com/Type/31118_64.png",
            "outputTypeId": 31117,
            "outputTypeName": "Small Cargohold Optimization I",
            "outputTypeIconURL": "https://image.eveonline.com/Type/31117_64.png",
            "outputPrice": 74990.0,
            "tradeStation": {
                "locationType": "STATION",
                "locationId": 60003760,
                "stationId": 60003760,
                "stationName": "Jita IV - Moon 4 - Caldari Navy Assembly Plant",
                "solarSystemId": 30000142,
                "solarSystemName": "Jita",
                "securityClass": "B",
                "securityStatus": 0.94591314,
                "constellationId": 20000020,
                "constellationName": "Kimotoro",
                "regionId": 10000002,
                "regionName": "The Forge"
            },
            "manufactureMaterialCost": 35315.0,
            "costIndex": 1.9111142573977065
        })
    })

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            expect(new V1ProcessedBlueprintSummary()).toBeTruthy()
        })
        it('Initial State', () => {
            const blueprint: V1ProcessedBlueprintSummary = new V1ProcessedBlueprintSummary()
            expect(blueprint).toBeTruthy()
            expect(blueprint.uid).toBeUndefined()
            expect(blueprint.blueprintTypeId).toBeUndefined()
            expect(blueprint.blueprintTypeName).toBeUndefined()
            expect(blueprint.blueprintTypeIconURL).toBeUndefined()
            expect(blueprint.outputTypeId).toBeUndefined()
            expect(blueprint.outputTypeName).toBeUndefined()
            expect(blueprint.outputTypeIconURL).toBeUndefined()
            expect(blueprint.outputPrice).toBeUndefined()
            expect(blueprint.tradeStation).toBeUndefined()
            expect(blueprint.manufactureMaterialCost).toBeUndefined()
            expect(blueprint.costIndex).toBeUndefined()
        })
        it('Complete construction', () => {
            expect(testBlueprint.uid).toBe("BCI:93813310:31118")
            expect(testBlueprint.blueprintTypeId).toBe(31118)
            expect(testBlueprint.blueprintTypeName).toBe("Small Cargohold Optimization I Blueprint")
            expect(testBlueprint.blueprintTypeIconURL).toBe("https://image.eveonline.com/Type/31118_64.png")
            expect(testBlueprint.outputTypeId).toBe(31117)
            expect(testBlueprint.outputTypeName).toBe("Small Cargohold Optimization I")
            expect(testBlueprint.outputTypeIconURL).toBe("https://image.eveonline.com/Type/31117_64.png")
            expect(testBlueprint.outputPrice).toBe(74990.0)
            expect(testBlueprint.tradeStation).toBeDefined()
            expect(testBlueprint.manufactureMaterialCost).toBe(35315.0)
            expect(testBlueprint.costIndex).toBe(1.9111142573977065)
        })
    })
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [getters]', () => {
        it('getUniqueId: validate the content for the unique identifier', () => {
            expect(testBlueprint.getUniqueId()).toBe("BCI:93813310:31118")
        })
        it('getBlueprintName: validate the content for the blueprint type name', () => {
            expect(testBlueprint.getBlueprintName()).toBe("Small Cargohold Optimization I Blueprint")
        })
        it('getOutputName: validate the content for the output type name', () => {
            expect(testBlueprint.getOutputName()).toBe("Small Cargohold Optimization I")
        })
    })
})
