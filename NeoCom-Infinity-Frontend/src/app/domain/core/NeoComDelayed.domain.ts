import { NeoCom } from "@domain/NeoCom.domain";

export class NeoComDelayed extends NeoCom {
    public ready: boolean = false

    constructor(values: Object = {}) {
        super(values)
    }

    public isReady(): boolean {
        return this.ready
    }
}
