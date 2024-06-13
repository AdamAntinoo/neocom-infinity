import { Then } from '@cucumber/cucumber'
import { NIN01World } from '../worlds/NIN01World'
import { V2ProcessedBlueprintDto } from 'neocom-domain'

Then('the response body is a list of processed blueprints', function () {
	expect(this.processedBlueprintsResponse).toBeDefined()
	expect(this.processedBlueprintsResponse.length).toBeGreaterThan(0)
	const asset: V2ProcessedBlueprintDto = this.processedBlueprintsResponse[0]
	expect(asset.jsonClass).toBe('ProcessedBlueprintDto')
	expect(asset.typeId).toBeDefined()
	expect(asset.blueprintItem).toBeDefined()
	expect(asset.outputItem).toBeDefined()
})

Then('the response body contains {int} items', function (world: NIN01World, itemCount: number) {
	expect(world.processedBlueprintsResponse).toBeDefined()
	expect(world.processedBlueprintsResponse.length).toBe(itemCount)
})
