// - INNOVATIVE
import { IDtoCompliant } from '@innovative/domain/interfaces/IDtoCompliant.interface'

export class EveItemDto implements IDtoCompliant{
    public industryGroup: string
    public item: object
    public group: object
    public category: object
    public name: string
    public typeId: number
    public categoryName: string
    public urlforItem: string
    public hullGroup: string
    public groupName: string

    constructor(values: Object = {}) {
        Object.assign(this, values)
        this.transform()
    }
    // - I D T O C O M P L I A N T
    public transform(): void { }

    public getName(): string {
        return this.item['name']
    }
    public getURLIcon(): string {
        return this.urlforItem
    }
}
