import {
	GetUniverseCategoriesCategoryIdOk,
	GetUniverseGroupsGroupIdOk,
	GetUniverseTypesTypeIdOk,
	IConverter,
	IndustryGroup,
	V1EsiTypeDto,
} from 'neocom-domain'

export class GetUniverseTypesTypeIdOkToV1EsiTypeDtoConverter implements IConverter<GetUniverseTypesTypeIdOk, V1EsiTypeDto> {
	public constructor(
		private esiGroup: GetUniverseGroupsGroupIdOk,
		private esiCategory: GetUniverseCategoriesCategoryIdOk,
	) {}

	public convert(source: GetUniverseTypesTypeIdOk): V1EsiTypeDto {
		return new V1EsiTypeDto({
			typeILd: source.type_id,
			name: source.name,
			description: source.description,
			marketGroupId: 34,
			groupId: this.esiGroup.group_id,
			groupName: this.esiGroup.name,
			categoryId: this.esiCategory.category_id,
			categoryName: this.esiCategory.name,
			capacity: source.capacity,
			packagedVolume: source.packaged_volume,
			volume: source.volume,
			industryGroup: this.classifyIndustryGroup(),
		})
	}
	private getGroupName(): string {
		// Implementation needed
		return ''
	}

	private getCategoryName(): string {
		// Implementation needed
		return ''
	}

	protected classifyIndustryGroup(): IndustryGroup {
		if (this.esiGroup.name.toLowerCase() == 'Composite'.toLowerCase() && this.esiCategory.name.toLowerCase() == 'Material'.toLowerCase()) {
			return IndustryGroup.REACTIONMATERIALS
		}
		if (this.esiCategory.name.toLowerCase() == 'Asteroid'.toLowerCase()) {
			return IndustryGroup.OREMATERIALS
		}
		if (this.esiGroup.name.toLowerCase() == 'Mining Crystal'.toLowerCase() && this.esiCategory.name.toLowerCase() == 'Charge'.toLowerCase()) {
			return IndustryGroup.ITEMS
		}
		if (this.esiCategory.name.toLowerCase() == 'Charge'.toLowerCase()) {
			return IndustryGroup.CHARGE
		}
		if (this.esiGroup.name.toLowerCase() == 'Tool'.toLowerCase()) {
			return IndustryGroup.ITEMS
		}
		if (this.esiCategory.name.toLowerCase() == 'Commodity'.toLowerCase()) {
			return IndustryGroup.COMMODITY
		}
		if (this.esiCategory.name.toLowerCase() == 'Blueprint'.toLowerCase()) {
			return IndustryGroup.BLUEPRINT
		}
		if (this.esiCategory.name.toLowerCase() == 'Skill'.toLowerCase()) {
			return IndustryGroup.SKILL
		}
		if (this.esiGroup.name.toLowerCase() == 'Mineral'.toLowerCase()) {
			return IndustryGroup.REFINEDMATERIAL
		}
		if (this.esiCategory.name.toLowerCase() == 'Module'.toLowerCase()) {
			return IndustryGroup.COMPONENTS
		}
		if (this.esiCategory.name.toLowerCase() == 'Drone'.toLowerCase()) {
			return IndustryGroup.ITEMS
		}
		if (this.esiCategory.name.toLowerCase() == 'Planetary Commodities'.toLowerCase()) {
			return IndustryGroup.PLANETARYMATERIALS
		}
		if (this.esiGroup.name.toLowerCase() == 'Datacores'.toLowerCase()) {
			return IndustryGroup.DATACORES
		}
		if (this.esiGroup.name.toLowerCase() == 'Salvaged Materials'.toLowerCase()) {
			return IndustryGroup.SALVAGEDMATERIAL
		}
		if (this.esiCategory.name.toLowerCase() == 'Ship'.toLowerCase()) {
			return IndustryGroup.HULL
		}
		return IndustryGroup.UNDEFINED
	}
}
