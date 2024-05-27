import { Record } from "../interfaces/Record.interface"
import { V1StackDto } from "./V1.Stack.dto"

export class V1AssetDto extends Record {
    public override jsonClass: string = 'Asset'
	public id?: number
    public resource?: V1StackDto
}
