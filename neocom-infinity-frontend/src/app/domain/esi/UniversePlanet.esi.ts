export class UniversePlanet {
    public name: string
    public planet_id: number
    public system_id: number
    public type_id: number

    constructor(values: Object = {}) {
        Object.assign(this, values);
        this.decode()
    }

    private decode(): void { }
}
