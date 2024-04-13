import { Record } from "../interfaces/Record.interface"

export class V1MiningResourceDto extends Record {
    jsonClass: string = 'MiningResourceDto'
    id?: string
    date?: string
    quantity?: number
    solarSystemLink?: string
    typeLink?: number
}
