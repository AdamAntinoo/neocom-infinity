export class UniverseType {
    public typeId: number
    public name: string
    public description: string
    public groupId: number
    public marketGroupId: number
    public capacity: number
    public mass: number
    public packagedVolume: number
    public volume: number

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}
