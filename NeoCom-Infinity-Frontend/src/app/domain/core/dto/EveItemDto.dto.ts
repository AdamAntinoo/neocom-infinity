export class EveItemDto {
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
    private transform(): void { }

    public getName(): string {
        return this.item['name']
    }
    public getURLIcon(): string {
        return this.urlforItem
    }
}
