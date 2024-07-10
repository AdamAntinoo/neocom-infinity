import { V2ProcessedBlueprintDto } from "./v2.ProcessedBlueprint.dto"

describe("DTO V2ProcessedBlueprintDto [Module: neocom-domain - Version: v2]", () => {
	// - C O N S T R U C T I O N   P H A S E
	describe("Constructor Contract Phase", () => {
		test("Should be created", () => {
			console.log("><[V2ProcessedBlueprintDto]> should be created")
			expect(new V2ProcessedBlueprintDto()).toBeDefined()
		})
		test("when constructed with no data the class is defined", () => {
			const sut : V2ProcessedBlueprintDto=new V2ProcessedBlueprintDto()
			expect (sut.jsonClass).toBe('ProcessedBlueprintDto')
		})
	})
})
