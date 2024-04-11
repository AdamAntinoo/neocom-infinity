import { Record } from "neocom-domain/interfaces/Record.interface"

export class V1MiningResourceDto extends Record {
    jsonClass: string = 'MiningResourceDto'
    id: string
    date: string
    quantity: number
    solarSystem: string
    typeId: string
}
