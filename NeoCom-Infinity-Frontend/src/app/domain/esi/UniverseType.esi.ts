export class UniverseType {
    public capacity: number
    public description: string
    public graphic_id: number
    public group_id: number
    public icon_id: number
    public market_group_id: number
    public mass: number
    public name: string
    public packaged_volume: number
    public portion_size: number
    public radius: number
    public type_id: number
    public volume: number

    constructor(values: Object = {}) {
        Object.assign(this, values);
        this.decode()
    }

    private decode(): void { }
}
