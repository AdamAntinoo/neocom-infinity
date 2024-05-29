import { Then } from "@cucumber/cucumber"
import { expect } from 'expect'
import { NIN01World } from "../worlds/NIN01World"
import { V1BlueprintDto } from "neocom-domain/V1.Blueprint.dto"

Then('the response body is a list of assets', function (this: NIN01World) {
	expect(this.esiAssetsResponse).toBeDefined()
	expect(this.esiAssetsResponse.length).toBeGreaterThan(0	)
	const asset = this.esiAssetsResponse[0]
	// expect(asset.jsonClass).toBe('AssetDto')
	expect(asset.id).toBeDefined()
	expect(asset.locationLink).toBeDefined()
	expect(asset.resource).toBeDefined()
})
Then('the response body contains {int} asset', function (this: NIN01World,count:number) {
	expect(this.esiAssetsResponse.length).toBe(count)
})
Then('the response body is a list of blueprints', function (this: NIN01World) {
	expect(this.esiBlueprintsResponse).toBeDefined()
	expect(this.esiBlueprintsResponse.length).toBeGreaterThan(0	)
	const blueprint : V1BlueprintDto= this.esiBlueprintsResponse[0]
	expect(blueprint.jsonClass).toBe('BlueprintDto')
	expect(blueprint.id).toBeDefined()
})
Then('the response body contains {int} blueprint', function (this: NIN01World,count:number) {
	expect(this.esiBlueprintsResponse.length).toBe(count)
})
