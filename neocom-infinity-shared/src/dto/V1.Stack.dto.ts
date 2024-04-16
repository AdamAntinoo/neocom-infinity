import { Record } from "../interfaces/Record.interface"

export class V1StackDto extends Record {
    public override jsonClass: string = 'StackDto'
    public quantity?: number
    public typeLink?: string
}
