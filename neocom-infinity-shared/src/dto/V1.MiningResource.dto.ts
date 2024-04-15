import { Record } from "../interfaces/Record.interface"

export class V1MiningResourceDto extends Record {
    public override jsonClass: string = 'MiningResourceDto'
    public id?: string
    public date?: string
    public quantity?: number
    public solarSystemLink?: string
    public typeLink?: number
}
