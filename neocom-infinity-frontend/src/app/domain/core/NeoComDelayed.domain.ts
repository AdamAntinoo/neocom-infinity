import { NeoCom } from "@domain/NeoCom.domain";
/** @deprecated */
export abstract class NeoComDelayed extends NeoCom {
    public ready: boolean = false

    public isReady(): boolean {
        return this.ready
    }
}
