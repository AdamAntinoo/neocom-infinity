import { Record, V1EsiTypeDto, V1SpaceLocationDto, V1StackDto } from 'neocom-domain'

export class V1ProcessedBlueprintDto extends Record {
	public override jsonClass: string = "ProcessedBlupeintDto"
	public typeId?: number
	public blueprintItem?: V1EsiTypeDto
	public location?: V1SpaceLocationDto
	public materialEfficiency: number = 0
	public timeEfficiency: number = 0
	public outputTypeId?: number
	public outputItem?: V1EsiTypeDto
	public manufactureCost?: number
	public outputCost?: number
	public bom?: V1StackDto[]
	public index: number = 0.0
}
