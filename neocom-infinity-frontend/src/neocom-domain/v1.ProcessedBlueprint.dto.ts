import { Record, V1EsiTypeDto, V1SpaceLocationDto, V1StackDto } from 'neocom-domain'
/** @deprecated */
export class V1ProcessedBlueprintDto extends Record {
	public override jsonClass: string = 'ProcessedBlupeintDto'
	public typeId?: number
	public blueprintItem?: V1EsiTypeDto
	public blueprint?: V1EsiTypeDto
	public location?: V1SpaceLocationDto
	public materialEfficiency: number
	public timeEfficiency: number
	public outputTypeId?: number
	public outputItem?: V1EsiTypeDto
	public output?: V1EsiTypeDto
	public manufactureCost?: number
	public outputCost?: number
	public bom?: V1StackDto[]
	public index: number
}
