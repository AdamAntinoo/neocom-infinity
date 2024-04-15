import { Record } from "../interfaces/Record.interface"

export class V1EsiTypeDto extends Record{
    public override jsonClass:string ='EsiType'
    public typeId: number = 0 - 1
    public name: string = ''
    public description: string = ''
    public iconId: number = -1
    public groupId: number = -1
    public groupName: string = ''
    public categoryId: number = -1
    public categoryName: string = ''
    public volume: number = 0.01
    public marketDataLink: string = 'link'
}
