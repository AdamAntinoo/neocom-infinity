import { Then } from "@cucumber/cucumber"
import { expect } from 'expect'
import { NIN01World } from "../worlds/NIN01World"
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
