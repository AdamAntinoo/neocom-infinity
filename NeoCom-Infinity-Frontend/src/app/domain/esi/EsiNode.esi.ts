export class EsiNode {
    constructor(values: Object = {}) {
        Object.assign(this, values);
        this.decode()
    }

    private decode(): void { }
}
