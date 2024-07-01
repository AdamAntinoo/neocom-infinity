# Check identical blleprints
/**
 * The use case starts with a list of the character blueprints as a raw data array where the items are instances of V1BlueprintDto. This has the ESI data representation that for the initial
 * view of an Blueprint should be enough. Then to simplify backedn requests and UI presentation we should pack all identical blueprints that at the ESI representation
 * are always singletons with a 1 quantity into Stacks of identical blueprints. This is a simplification of the ESI data representation of a blueprint.
 *
 * The packiing should create a new V1BlueprintItem for each unique blueprint and set the identicalQuantity property to the number of identical blueprints.
 *
 * It then transforms this array into a list of V1BlueprintCategoryContainer objects, where each category is a container for a list of V1BlueprintItem objects.
 */
 
	private processBlueprints(blueprints: V1BlueprintDto[]): V1BlueprintItem[] {
		const blueprints: V1BlueprintItem[] = this.packBlueprints(blueprints)

		for (let blueprintDto of blueprints) {
			const blueprint: V1BlueprintItem = await this.resolvePromise(blueprintDto)
			this.addBlueprint(blueprint, resolveData)
		}
	}
	private packBlueprints(blueprints: V1BlueprintDto[]): V1BlueprintItem[] {
		const blueprints: V1BlueprintItem[] = []
		for (let blueprintDto of blueprints) {
			// Search for identical blueprints
			const target: V1BlueprintItem = this.searchIdentical(blueprintDto, blueprints)

			const blueprint: V1BlueprintItem = await this.resolvePromise(blueprintDto)
			this.addBlueprint(blueprint, resolveData)
		}
	}
	private searchIdentical(blueprint: V1BlueprintDto, blueprints: V1BlueprintDto[]): V1BlueprintItem {
		for (let blueprintItem of blueprints) {
			if (this.isIdentical(blueprint, blueprintItem)) return blueprintItem
		}
	}
	private isIdentical(blueprint: V1BlueprintDto, blueprintTarget: V1BlueprintDto): boolean {
		return (
			blueprintTarget.storageLocation?.locationLink === blueprint.storageLocation?.locationLink &&
			blueprintTarget.materialEfficiency === blueprint.materialEfficiency &&
			blueprintTarget.timeEfficiency === blueprint.timeEfficiency &&
			blueprintTarget.runs === blueprint.runs &&
			blueprintTarget.typeLink === blueprint.typeLink
		)
	}
