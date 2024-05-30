import { Then } from "@cucumber/cucumber"
import { expect } from 'expect'
import { NIN01World } from "../worlds/NIN01World"
import { V1AssetDto, V1BlueprintDto } from "neocom-domain"

Then('the response body is a list of assets', function (this: NIN01World) {
	expect(this.esiAssetsResponse).toBeDefined()
	expect(this.esiAssetsResponse.length).toBeGreaterThan(0)
	const asset: V1AssetDto = this.esiAssetsResponse[0]
	// expect(asset.jsonClass).toBe('AssetDto')
	expect(asset.id).toBeDefined()
	expect(asset.locationLink).toBeDefined()
	expect(asset.resource).toBeDefined()
})
Then('the response body contains {int} asset', function (this: NIN01World, count: number) {
	expect(this.esiAssetsResponse.length).toBe(count)
})
Then('the response body is a list of blueprints', function (this: NIN01World) {
	expect(this.esiBlueprintsResponse).toBeDefined()
	expect(this.esiBlueprintsResponse.length).toBeGreaterThan(0)
	const blueprint: V1BlueprintDto = this.esiBlueprintsResponse[0]
	expect(blueprint.jsonClass).toBe('BlueprintDto')
	expect(blueprint.id).toBeDefined()
})
Then('the response body contains {int} blueprint', function (this: NIN01World, count: number) {
	expect(this.esiBlueprintsResponse.length).toBe(count)
})
Then('the blueprint at position {int} has next contents', function (this: NIN01World, position: number, dataTable) {
	expect(this.esiBlueprintsResponse).toBeDefined()
	expect(this.esiBlueprintsResponse.length).toBeGreaterThan(position)
	const blueprint: V1BlueprintDto = this.esiBlueprintsResponse[position]
	expect(blueprint).toBeDefined()
	const row = dataTable.hashes()[0]
	expect(validateBlueprint(row, blueprint)).toBeTruthy()
})
function validateBlueprint(row: any, blueprint: V1BlueprintDto): boolean {
	expect(row['jsonClass']).toBe(blueprint.jsonClass)
	expect(Number(row['id'])).toBe(blueprint.id)
	expect(Number(row['materialEfficiency'])).toBe(blueprint.materialEfficiency)
	expect(Number(row['timeEfficiency'])).toBe(blueprint.timeEfficiency)
	expect(Number(row['runs'])).toBe(blueprint.runs)
	expect(row['original']).toBe(JSON.stringify(blueprint.original))
	expect(row['typeLink']).toBe(blueprint.typeLink)
	const location = blueprint.storageLocation
	expect(row['storageLocation.locationType']).toBe(location.locationType)
	expect(row['storageLocation.locationLink']).toBe(location.locationLink)
	return true
}
