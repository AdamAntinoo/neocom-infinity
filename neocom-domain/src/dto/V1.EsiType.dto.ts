import { ESI_CONSTANTS } from "../conf/GlobalConstants"
import { IndustryGroup } from "../enums/IndustryGroup.enumerated"
import { Record } from "../interfaces/Record.interface"

export class V1EsiTypeDto extends Record {
	public override jsonClass: string = "EsiTypeDto"
	public typeId?: number
	public name?: string
	public description?: string
	public marketGroupId?: number
	public groupId?: number
	public groupName?: string
	public categoryId?: number
	public categoryName?: string
	public capacity?: number
	public packagedVolume?: number
	public volume?: number
	public industryGroup?: IndustryGroup.UNDEFINED

	public getName(): string {
		if (undefined === this.name) return ""
		else return this.name
	}
	public getCategoryName(): string {
		if (undefined === this.categoryName) return ""
		else return this.categoryName
	}
	public getTech(): string {
		if (this.getName().endsWith("III")) return "Tech III"
		if (this.getName().endsWith("II")) return "Tech II"
		return "Tech I"
	}
	public isBlueprint(): boolean {
		return this.getCategoryName().toLowerCase() === ESI_CONSTANTS.BLUEPRINT
	}
}
